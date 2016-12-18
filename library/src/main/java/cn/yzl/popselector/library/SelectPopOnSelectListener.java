package cn.yzl.popselector.library;

/**
 * Created by 易点付 伊 on 2016/11/4.
 */

public interface SelectPopOnSelectListener {
    /**
     *
     * @param position -1 取消
     *                 0 第一个按钮
     *                 1 第二个按钮
     */
    void onSelect(int position);
}
