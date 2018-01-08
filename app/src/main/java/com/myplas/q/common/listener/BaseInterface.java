package com.myplas.q.common.listener;

import android.support.v4.app.Fragment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 黄双 on 2017/11/6 0006.
 */

public interface BaseInterface extends Serializable {
    void complete(int position);

    void dataBack(Fragment fragment, List agrs);
}
