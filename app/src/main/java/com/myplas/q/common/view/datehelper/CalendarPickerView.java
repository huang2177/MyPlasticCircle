// Copyright 2012 Square, Inc.
package com.myplas.q.common.view.datehelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.myplas.q.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

public class CalendarPickerView extends ListView {
    public enum SelectionMode {
        SINGLE,
        MULTIPLE,
        RANGE
    }

    private final MonthAdapter adapter;
    private final List<List<List<MonthCellDescriptor>>> cells =
            new ArrayList<List<List<MonthCellDescriptor>>>();
    final MonthView.Listener listener = new CellClickedListener();
    final List<MonthDescriptor> months = new ArrayList<MonthDescriptor>();
    final List<MonthCellDescriptor> selectedCells = new ArrayList<MonthCellDescriptor>();
    final List<MonthCellDescriptor> highlightedCells = new ArrayList<MonthCellDescriptor>();
    final List<Calendar> selectedCals = new ArrayList<Calendar>();
    final List<Calendar> highlightedCals = new ArrayList<Calendar>();
    private Map<String, Boolean> map;
    private List<String> list_unselected;
    private Locale locale;
    private DateFormat monthNameFormat;
    private DateFormat weekdayNameFormat;
    private DateFormat fullDateFormat;
    private Calendar minCal;
    private Calendar maxCal;
    private Calendar monthCounter;
    private boolean displayOnly, isclickable;
    SelectionMode selectionMode;
    Calendar today;

    private OnDateSelectedListener dateListener;
    private DateSelectableFilter dateConfiguredListener;
    private OnInvalidDateSelectedListener invalidDateListener =
            new DefaultOnInvalidDateSelectedListener();

    public CalendarPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        adapter = new MonthAdapter();
        setDivider(null);
        setDividerHeight(0);
        final int bg = getResources().getColor(R.color.calendar_bg);
        setBackgroundColor(bg);
        setCacheColorHint(bg);
        list_unselected = new ArrayList<>();
        map = new HashMap<>();
        locale = Locale.getDefault();
        today = Calendar.getInstance(locale);
        minCal = Calendar.getInstance(locale);
        maxCal = Calendar.getInstance(locale);
        monthCounter = Calendar.getInstance(locale);
        monthNameFormat = new SimpleDateFormat(context.getString(R.string.month_name_format), locale);
        weekdayNameFormat = new SimpleDateFormat(context.getString(R.string.day_name_format), locale);
        fullDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        if (isInEditMode()) {
            Calendar nextYear = Calendar.getInstance(locale);
            nextYear.add(Calendar.YEAR, 1);

            init(new Date(), nextYear.getTime(), null) //
                    .withSelectedDate(new Date());
        }
    }

    public FluentInitializer init(Date minDate, Date maxDate, Locale locale, List<String> list) {
        if (minDate == null || maxDate == null) {
            throw new IllegalArgumentException(
                    "minDate and maxDate must be non-null.  " + dbg(minDate, maxDate));
        }
        if (minDate.after(maxDate)) {
            throw new IllegalArgumentException(
                    "minDate must be before maxDate.  " + dbg(minDate, maxDate));
        }
        if (minDate.getTime() == 0 || maxDate.getTime() == 0) {
            throw new IllegalArgumentException(
                    "minDate and maxDate must be non-zero.  " + dbg(minDate, maxDate));
        }
        if (locale == null) {
            throw new IllegalArgumentException("Locale is null.");
        }
        this.list_unselected = list;
        // Make sure that all calendar instances use the same locale.
        this.locale = locale;
        today = Calendar.getInstance(locale);
        minCal = Calendar.getInstance(locale);
        maxCal = Calendar.getInstance(locale);
        monthCounter = Calendar.getInstance(locale);
        monthNameFormat =
                new SimpleDateFormat(getContext().getString(R.string.month_name_format), locale);
        for (MonthDescriptor month : months) {
            month.setLabel(monthNameFormat.format(month.getDate()));
        }
        weekdayNameFormat =
                new SimpleDateFormat(getContext().getString(R.string.day_name_format), locale);
        fullDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

        this.selectionMode = SelectionMode.SINGLE;
        // Clear out any previously-selected dates/cells.
        selectedCals.clear();
        selectedCells.clear();
        highlightedCells.clear();

        // Clear previous state.
        cells.clear();
        months.clear();
        minCal.setTime(minDate);
        maxCal.setTime(maxDate);
        setMidnight(minCal);
        setMidnight(maxCal);
        displayOnly = false;

        // maxDate is exclusive: bump back to the previous day so if maxDate is the first of a month,
        // we don't accidentally include that month in the view.
        maxCal.add(MINUTE, -1);

        // Now iterate between minCal and maxCal and build up our list of months to show.
        monthCounter.setTime(minCal.getTime());
        final int maxMonth = maxCal.get(MONTH);
        final int maxYear = maxCal.get(YEAR);
        while ((monthCounter.get(MONTH) <= maxMonth // Up to, including the month.
                || monthCounter.get(YEAR) < maxYear) // Up to the year.
                && monthCounter.get(YEAR) < maxYear + 1) { // But not > next yr.
            Date date = monthCounter.getTime();
            MonthDescriptor month =
                    new MonthDescriptor(monthCounter.get(MONTH), monthCounter.get(YEAR), date,
                            monthNameFormat.format(date));
            cells.add(getMonthCells(month, monthCounter));

            months.add(month);
            monthCounter.add(MONTH, 1);
        }

        validateAndUpdate();
        return new FluentInitializer();
    }

    public FluentInitializer init(Date minDate, Date maxDate, List<String> list) {
        return init(minDate, maxDate, Locale.getDefault(), list);
    }

    public class FluentInitializer {
        /**
         * Override the {@link SelectionMode} from the default ({@link SelectionMode#SINGLE}).
         */
        public FluentInitializer inMode(SelectionMode mode) {
            selectionMode = mode;
            validateAndUpdate();
            return this;
        }

        /**
         * Set an initially-selected date.  The calendar will scroll to that date if it's not already
         * visible.
         */
        public FluentInitializer withSelectedDate(Date selectedDates) {
            return withSelectedDates(Arrays.asList(selectedDates));
        }

        /**
         * Set multiple selected dates.  This will throw an {@link IllegalArgumentException} if you
         * pass in multiple dates and haven't already called {@link #inMode(SelectionMode)}.
         */
        public FluentInitializer withSelectedDates(Collection<Date> selectedDates) {
            if (selectionMode == SelectionMode.SINGLE && selectedDates.size() > 1) {
                throw new IllegalArgumentException("SINGLE mode can't be used with multiple selectedDates");
            }
            if (selectedDates != null) {
                for (Date date : selectedDates) {
                    selectDate(date);
                }
            }
            scrollToSelectedDates();

            validateAndUpdate();
            return this;
        }

        public FluentInitializer withHighlightedDates(Collection<Date> dates) {
            highlightDates(dates);
            return this;
        }

        public FluentInitializer withHighlightedDate(Date date) {
            return withHighlightedDates(Arrays.asList(date));
        }

        public FluentInitializer displayOnly() {
            displayOnly = true;
            return this;
        }
    }

    private void validateAndUpdate() {
        if (getAdapter() == null) {
            setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    private void scrollToSelectedMonth(final int selectedIndex) {
        post(new Runnable() {
            @Override
            public void run() {
                setSelection(selectedIndex);
            }
        });
    }

    private void scrollToSelectedDates() {
        Integer selectedIndex = null;
        Integer todayIndex = null;
        Calendar today = Calendar.getInstance(locale);
        for (int c = 0; c < months.size(); c++) {
            MonthDescriptor month = months.get(c);
            if (selectedIndex == null) {
                for (Calendar selectedCal : selectedCals) {
                    if (sameMonth(selectedCal, month)) {
                        selectedIndex = c;
                        break;
                    }
                }
                if (selectedIndex == null && todayIndex == null && sameMonth(today, month)) {
                    todayIndex = c;
                }
            }
        }
        if (selectedIndex != null) {
            scrollToSelectedMonth(selectedIndex);
        } else if (todayIndex != null) {
            scrollToSelectedMonth(todayIndex);
        }
    }

    /**
     * This method should only be called if the calendar is contained in a dialog, and it should only
     * be called once, right after the dialog is shown (using
     * {@link android.content.DialogInterface.OnShowListener} or
     * {@link android.app.DialogFragment#onStart()}).
     */
    public void fixDialogDimens() {
        // Fix the layout height/width after the dialog has been shown.
        getLayoutParams().height = getMeasuredHeight();
        getLayoutParams().width = getMeasuredWidth();
        // Post this runnable so it runs _after_ the dimen changes have been applied/re-measured.
        post(new Runnable() {
            @Override
            public void run() {
                scrollToSelectedDates();
            }
        });
    }

    /**
     * This method should only be called if the calendar is contained in a dialog, and it should only
     * be called when the screen has been rotated and the dialog should be re-measured.
     */
    public void unfixDialogDimens() {
        // Fix the layout height/width after the dialog has been shown.
        getLayoutParams().height = LayoutParams.MATCH_PARENT;
        getLayoutParams().width = LayoutParams.MATCH_PARENT;
        requestLayout();
    }


    public Date getSelectedDate() {
        return (selectedCals.size() > 0 ? selectedCals.get(0).getTime() : null);
    }

    public List<Date> getSelectedDates() {
        List<Date> selectedDates = new ArrayList<Date>();
        for (MonthCellDescriptor cal : selectedCells) {
            selectedDates.add(cal.getDate());
        }
        Collections.sort(selectedDates);
        return selectedDates;
    }

    /**
     * Returns a string summarizing what the client sent us for init() params.
     */
    private static String dbg(Date minDate, Date maxDate) {
        return "minDate: " + minDate + "\nmaxDate: " + maxDate;
    }

    /**
     * Clears out the hours/minutes/seconds/millis of a Calendar.
     */
    static void setMidnight(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    //日期选择监听
    private class CellClickedListener implements MonthView.Listener {
        @Override
        public void handleClick(MonthCellDescriptor cell) {
            Date clickedDate = cell.getDate();
            if (!betweenDates(clickedDate, minCal, maxCal) || !isDateSelectable(clickedDate)) {
                if (invalidDateListener != null) {
                    invalidDateListener.onInvalidDateSelected(clickedDate);
                }
            } else {
                boolean wasSelected = doSelectDate(clickedDate, cell);
                if (dateListener != null) {
                    if (wasSelected) {
                        dateListener.onDateSelected(clickedDate);
                    } else {
                        dateListener.onDateUnselected(clickedDate);
                    }
                }
            }
        }
    }

    public boolean selectDate(Date date) {
        validateDate(date);

        MonthCellWithMonthIndex monthCellWithMonthIndex = getMonthCellWithIndexByDate(date);
        if (monthCellWithMonthIndex == null || !isDateSelectable(date)) {
            return false;
        }
        boolean wasSelected = doSelectDate(date, monthCellWithMonthIndex.cell);
        if (wasSelected) {
            scrollToSelectedMonth(monthCellWithMonthIndex.monthIndex);
        }
        return wasSelected;
    }

    private void validateDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Selected date must be non-null.");
        }
        if (date.getTime() == 0) {
            throw new IllegalArgumentException("Selected date must be non-zero.  " + date);
        }
        if (date.before(minCal.getTime()) || date.after(maxCal.getTime())) {
            throw new IllegalArgumentException(
                    "selectedDate must be between minDate and maxDate.  " + date);
        }
    }

    private boolean doSelectDate(Date date, MonthCellDescriptor cell) {
        Calendar newlySelectedCal = Calendar.getInstance(locale);
        newlySelectedCal.setTime(date);
        // Sanitize input: clear out the hours/minutes/seconds/millis.
        setMidnight(newlySelectedCal);

        // Clear any remaining range state.
        for (MonthCellDescriptor selectedCell : selectedCells) {
            selectedCell.setRangeState(MonthCellDescriptor.RangeState.NONE);
        }

        switch (selectionMode) {
            case RANGE:
                if (selectedCals.size() > 1) {
                    // We've already got a range selected: clear the old one.
                    clearOldSelections();
                } else if (selectedCals.size() == 1 && newlySelectedCal.before(selectedCals.get(0))) {
                    // We're moving the start of the range back in time: clear the old start date.
                    clearOldSelections();
                }
                break;

            case MULTIPLE:
                date = applyMultiSelect(date, newlySelectedCal);
                break;

            case SINGLE:
                clearOldSelections();
                break;
            default:
                throw new IllegalStateException("Unknown selectionMode " + selectionMode);
        }

        if (date != null) {
            // Select a new cell.
            if (selectedCells.size() == 0 || !selectedCells.get(0).equals(cell)) {
                selectedCells.add(cell);
                cell.setSelected(true);
            }
            selectedCals.add(newlySelectedCal);

            if (selectionMode == SelectionMode.RANGE && selectedCells.size() > 1) {
                // Select all days in between start and end.
                Date start = selectedCells.get(0).getDate();
                Date end = selectedCells.get(1).getDate();
                selectedCells.get(0).setRangeState(MonthCellDescriptor.RangeState.FIRST);
                selectedCells.get(1).setRangeState(MonthCellDescriptor.RangeState.LAST);

                for (List<List<MonthCellDescriptor>> month : cells) {
                    for (List<MonthCellDescriptor> week : month) {
                        for (MonthCellDescriptor singleCell : week) {
                            if (singleCell.getDate().after(start)
                                    && singleCell.getDate().before(end)
                                    && singleCell.isSelectable()) {
                                singleCell.setSelected(true);
                                singleCell.setRangeState(MonthCellDescriptor.RangeState.MIDDLE);
                                selectedCells.add(singleCell);
                            }
                        }
                    }
                }
            }
        }

        // Update the adapter.
        validateAndUpdate();
        return date != null;
    }

    private void clearOldSelections() {
        for (MonthCellDescriptor selectedCell : selectedCells) {
            // De-select the currently-selected cell.
            selectedCell.setSelected(false);
        }
        selectedCells.clear();
        selectedCals.clear();
    }

    private Date applyMultiSelect(Date date, Calendar selectedCal) {
        for (MonthCellDescriptor selectedCell : selectedCells) {
            if (selectedCell.getDate().equals(date)) {
                // De-select the currently-selected cell.
                selectedCell.setSelected(false);
                selectedCells.remove(selectedCell);
                date = null;
                break;
            }
        }
        for (Calendar cal : selectedCals) {
            if (sameDate(cal, selectedCal)) {
                selectedCals.remove(cal);
                break;
            }
        }
        return date;
    }

    public void highlightDates(Collection<Date> dates) {
        for (Date date : dates) {
            validateDate(date);

            MonthCellWithMonthIndex monthCellWithMonthIndex = getMonthCellWithIndexByDate(date);
            if (monthCellWithMonthIndex != null) {
                Calendar newlyHighlightedCal = Calendar.getInstance();
                newlyHighlightedCal.setTime(date);
                MonthCellDescriptor cell = monthCellWithMonthIndex.cell;

                highlightedCells.add(cell);
                highlightedCals.add(newlyHighlightedCal);
                cell.setHighlighted(true);
            }
        }

        adapter.notifyDataSetChanged();
        setAdapter(adapter);
    }

    /**
     * Hold a cell with a month-index.
     */
    private static class MonthCellWithMonthIndex {
        public MonthCellDescriptor cell;
        public int monthIndex;

        public MonthCellWithMonthIndex(MonthCellDescriptor cell, int monthIndex) {
            this.cell = cell;
            this.monthIndex = monthIndex;
        }
    }

    /**
     * Return cell and month-index (for scrolling) for a given Date.
     */
    private MonthCellWithMonthIndex getMonthCellWithIndexByDate(Date date) {
        int index = 0;
        Calendar searchCal = Calendar.getInstance(locale);
        searchCal.setTime(date);
        Calendar actCal = Calendar.getInstance(locale);

        for (List<List<MonthCellDescriptor>> monthCells : cells) {
            for (List<MonthCellDescriptor> weekCells : monthCells) {
                for (MonthCellDescriptor actCell : weekCells) {
                    actCal.setTime(actCell.getDate());
                    if (sameDate(actCal, searchCal) && actCell.isSelectable()) {
                        return new MonthCellWithMonthIndex(actCell, index);
                    }
                }
            }
            index++;
        }
        return null;
    }

    private class MonthAdapter extends BaseAdapter {
        private final LayoutInflater inflater;

        private MonthAdapter() {
            inflater = LayoutInflater.from(getContext());
        }

        @Override
        public boolean isEnabled(int position) {
            // Disable selectability: each cell will handle that itself.
            return false;
        }

        @Override
        public int getCount() {
            return months.size();
        }

        @Override
        public Object getItem(int position) {
            return months.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MonthView monthView = (MonthView) convertView;
            if (monthView == null) {
                monthView = MonthView.create(parent, inflater, weekdayNameFormat, listener, today);
            }
            monthView.init(months.get(position), cells.get(position), displayOnly);
            return monthView;
        }
    }

    List<List<MonthCellDescriptor>> getMonthCells(MonthDescriptor month, Calendar startCal) {
        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(startCal.getTime());
        List<List<MonthCellDescriptor>> cells = new ArrayList<List<MonthCellDescriptor>>();
        cal.set(DAY_OF_MONTH, 1);
        int firstDayOfWeek = cal.get(DAY_OF_WEEK);
        int offset = cal.getFirstDayOfWeek() - firstDayOfWeek;
        if (offset > 0) {
            offset -= 7;
        }
        cal.add(Calendar.DATE, offset);

        Calendar minSelectedCal = minDate(selectedCals);
        Calendar maxSelectedCal = maxDate(selectedCals);

        while ((cal.get(MONTH) < month.getMonth() + 1 || cal.get(YEAR) < month.getYear()) //
                && cal.get(YEAR) <= month.getYear()) {
            List<MonthCellDescriptor> weekCells = new ArrayList<MonthCellDescriptor>();
            cells.add(weekCells);
            for (int c = 0; c < 7; c++) {
                Date date = cal.getTime();
                boolean isCurrentMonth = (cal.get(MONTH) == month.getMonth());
                boolean isSelected = isCurrentMonth && containsDate(selectedCals, cal);
//                boolean isSelectable =
//                        isCurrentMonth && betweenDates(cal, minCal, maxCal) && isDateSelectable(date);
                boolean isToday = sameDate(cal, today);
                boolean isHighlighted = containsDate(highlightedCals, cal);
                int value = cal.get(DAY_OF_MONTH);
                String s = cal.get(YEAR) + "-" + (month.getMonth() + 1) + "-" + value;
                if (isCurrentMonth && betweenDates(cal, minCal, maxCal) && isDateSelectable(date)) {
                    if (list_unselected.contains(parseDate(s))) {
                        map.put(s, false);
                    } else {
                        map.put(s, true);
                    }
                } else {
                    map.put(s, false);
                }
                MonthCellDescriptor.RangeState rangeState = MonthCellDescriptor.RangeState.NONE;
                if (selectedCals.size() > 1) {
                    if (sameDate(minSelectedCal, cal)) {
                        rangeState = MonthCellDescriptor.RangeState.FIRST;
                    } else if (sameDate(maxDate(selectedCals), cal)) {
                        rangeState = MonthCellDescriptor.RangeState.LAST;
                    } else if (betweenDates(cal, minSelectedCal, maxSelectedCal)) {
                        rangeState = MonthCellDescriptor.RangeState.MIDDLE;
                    }
                }
                weekCells.add(
                        new MonthCellDescriptor(date, isCurrentMonth, map.get(s), isSelected, isToday,
                                isHighlighted, value, rangeState));
                cal.add(DATE, 1);
            }
        }
        return cells;
    }

    private static boolean containsDate(List<Calendar> selectedCals, Calendar cal) {
        for (Calendar selectedCal : selectedCals) {
            if (sameDate(cal, selectedCal)) {
                return true;
            }
        }
        return false;
    }

    private static Calendar minDate(List<Calendar> selectedCals) {
        if (selectedCals == null || selectedCals.size() == 0) {
            return null;
        }
        Collections.sort(selectedCals);
        return selectedCals.get(0);
    }

    private static Calendar maxDate(List<Calendar> selectedCals) {
        if (selectedCals == null || selectedCals.size() == 0) {
            return null;
        }
        Collections.sort(selectedCals);
        return selectedCals.get(selectedCals.size() - 1);
    }

    private static boolean sameDate(Calendar cal, Calendar selectedDate) {
        return cal.get(MONTH) == selectedDate.get(MONTH)
                && cal.get(YEAR) == selectedDate.get(YEAR)
                && cal.get(DAY_OF_MONTH) == selectedDate.get(DAY_OF_MONTH);
    }

    private static boolean betweenDates(Calendar cal, Calendar minCal, Calendar maxCal) {
        final Date date = cal.getTime();
        return betweenDates(date, minCal, maxCal);
    }

    static boolean betweenDates(Date date, Calendar minCal, Calendar maxCal) {
        final Date min = minCal.getTime();
        return (date.equals(min) || date.after(min)) // >= minCal
                && date.before(maxCal.getTime()); // && < maxCal
    }

    private static boolean sameMonth(Calendar cal, MonthDescriptor month) {
        return (cal.get(MONTH) == month.getMonth() && cal.get(YEAR) == month.getYear());
    }

    private boolean isDateSelectable(Date date) {
        return dateConfiguredListener == null || dateConfiguredListener.isDateSelectable(date);
    }

    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        dateListener = listener;
    }

    /**
     * Set a listener to react to user selection of a disabled date.
     *
     * @param listener the listener to set, or null for no reaction
     */
    public void setOnInvalidDateSelectedListener(OnInvalidDateSelectedListener listener) {
        invalidDateListener = listener;
    }


    public void setDateSelectableFilter(DateSelectableFilter listener) {
        dateConfiguredListener = listener;
    }

    /**
     * Interface to be notified when a new date is selected or unselected. This will only be called
     * when the user initiates the date selection.  If you call {@link #selectDate(Date)} this
     * listener will not be notified.
     *
     * @see #setOnDateSelectedListener(OnDateSelectedListener)
     */
    public interface OnDateSelectedListener {
        void onDateSelected(Date date);

        void onDateUnselected(Date date);
    }

    /**
     * Interface to be notified when an invalid date is selected by the user. This will only be
     * called when the user initiates the date selection. If you call {@link #selectDate(Date)} this
     * listener will not be notified.
     *
     * @see #setOnInvalidDateSelectedListener(OnInvalidDateSelectedListener)
     */
    public interface OnInvalidDateSelectedListener {
        void onInvalidDateSelected(Date date);
    }

    /**
     * Interface used for determining the selectability of a date cell when it is configured for
     * display on the calendar.
     *
     * @see #setDateSelectableFilter(DateSelectableFilter)
     */
    public interface DateSelectableFilter {
        boolean isDateSelectable(Date date);
    }

    private class DefaultOnInvalidDateSelectedListener implements OnInvalidDateSelectedListener {
        @Override
        public void onInvalidDateSelected(Date date) {
            String errMessage =
                    getResources().getString(R.string.invalid_date, fullDateFormat.format(minCal.getTime()),
                            fullDateFormat.format(maxCal.getTime()));
            // Toast.makeText(getContext(), errMessage, Toast.LENGTH_SHORT).show();
        }
    }

    public int cmpareDate(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.compareTo(d2);
        } catch (Exception e) {
            return 10;
        }
    }

    public String parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String LgTime = sdf.format(sdf.parse(dateString));
            return LgTime;
        } catch (Exception e) {
            return null;
        }
    }
}
