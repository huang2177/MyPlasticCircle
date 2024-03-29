package com.myplas.q.homepage.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.ProgressImageView;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class BrokeNewsAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<String> list;
    private SparseArray<View> sparseArray;

    private int color;

    public BrokeNewsAdapter(Activity context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
        sparseArray = new SparseArray<>();
        color = ContextCompat.getColor(context, R.color.color_red);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (sparseArray.get(position) == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brokenews_layout, parent, false);
            viewHolder.imageView = (ProgressImageView) convertView.findViewById(R.id.item_img);

            convertView.setTag(viewHolder);
            sparseArray.put(position, convertView);
        } else {
            convertView = sparseArray.get(position);
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == list.size()) {
            viewHolder.imageView.setImageResource(R.drawable.upload);
        } else {
            viewHolder.imageView.setUseProgress(true);
            Glide.with(context).load(list.get(position)).into(viewHolder.imageView);
        }
        viewHolder.imageView.setOnClickListener(new MyOnClickListner(position));
        return convertView;
    }

    /**
     * 改变上传进度
     *
     * @param type
     * @param value
     */
    public void setProgress(int type, int value) {
        if (sparseArray.get(type) != null) {
            ViewHolder viewHolder = (ViewHolder) sparseArray.get(type).getTag();
            viewHolder.imageView.setProgress(value);
        }
    }


    public class MyOnClickListner implements View.OnClickListener {
        private int position;

        public MyOnClickListner(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (position == list.size()) {
                pickPicture();
            } else {
                galleryAlbum();
            }
        }

        /**
         * 大图预览
         */
        private void galleryAlbum() {
            Album.gallery(context)
                    .statusBarColor(color)
                    .checkedList(list)
                    .currentPosition(position)
                    .checkFunction(false)
                    .toolBarColor(Color.TRANSPARENT)
                    .start(200);
        }

        /**
         * 选择图片
         */
        private void pickPicture() {
            if (list.size() < 10) {
                Album.album(context)
                        .toolBarColor(color)
                        .statusBarColor(color)
                        .title("选取图片")
                        .columnCount(3)
                        .camera(true)
                        .selectCount(10 - list.size())
                        .start(100);
            } else {
                TextUtils.toast(context, "最多只能上传10张图片！");
            }
        }
    }

    class ViewHolder {
        ProgressImageView imageView;
    }

    /**
     * 裁剪
     *
     * @param pathList
     * @param i
     */
    public void cutPhoto(ArrayList<String> pathList) {
        Durban.with(context)
                // 裁剪界面的标题。
                .title("裁剪")
                .statusBarColor(color)
                .toolBarColor(color)
                // 图片路径list或者数组。
                .inputImagePaths(pathList)
                // 图片输出文件夹路径。
                // 裁剪图片输出的最大宽高。
                .maxWidthHeight(400, 250)
                // 设置裁剪比例
                .aspectRatio(8, 5)
                // 图片压缩格式：JPEG、PNG。
                .compressFormat(Durban.COMPRESS_JPEG)
                // 图片压缩质量，请参考：Bitmap#compress(Bitmap.CompressFormat, int, OutputStream)
                .compressQuality(100)
                // 裁剪时的手势支持：ROTATE, SCALE, ALL, NONE.
                .gesture(Durban.GESTURE_SCALE)
                .controller(Controller.newBuilder()
                        .enable(true) // 是否开启控制面板。
                        .rotation(true) // 是否有旋转按钮。
                        .rotationTitle(true) // 旋转控制按钮上面的标题。
                        .scale(true) // 是否有缩放按钮。
                        .scaleTitle(true) // 缩放控制按钮上面的标题。
                        .build()) // 创建控制面板配置。
                .requestCode(200)
                .start();
    }
}
