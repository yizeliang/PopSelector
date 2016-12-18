package cn.yzl.popselector.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.yzl.popselector.library.adapter.InnerAdapter;

/**
 * Created by 易点付 伊 on 2016/11/4.
 */
public class SelectPopWindow extends PopupWindow {

    public static final int TYPE_IOS_TRANSPARENT = 1;

    public static final int TYPE_NO_TRANSPARENT = TYPE_IOS_TRANSPARENT << 1;

    public static final int TYPE_CUSTOM = TYPE_NO_TRANSPARENT << 1;


    @IntDef({TYPE_IOS_TRANSPARENT, TYPE_NO_TRANSPARENT})
    private @interface Type {
    }


    private InnerListView innerListView;

    private InnerAdapter innerAdapter;

    private View content_v;

    private SelectPopOnSelectListener selectListener;
    private Window window;
    private View view;
    private Context context;

    private String[] menus;

    private int type;

    private TextView cancelBut;

    private int customLayout;

    private int customMenuLayout;

    private BaseAdapter customAdapter;

    /**
     * @param context
     * @param menus
     * @param selectListener
     * @param window
     * @param type
     */
    public SelectPopWindow(@NonNull Context context, @NonNull String[] menus,
                           @NonNull SelectPopOnSelectListener selectListener,
                           @NonNull Window window,
                           @Type int type) {
        this.context = context;
        this.window = window;
        this.selectListener = selectListener;
        this.menus = menus;
        this.type = type;
        initView();
    }

    /**
     * @param context
     * @param menus
     * @param selectListener
     * @param window
     * @param customLayout     自定义主布局 传0代表不自定义
     * @param customMenuLayout 自定义菜单按钮item布局 传0代表不自定义
     */
    public SelectPopWindow(@NonNull Context context, @NonNull String[] menus,
                           @NonNull SelectPopOnSelectListener selectListener,
                           @NonNull Window window,
                           @LayoutRes int customLayout,
                           @LayoutRes int customMenuLayout, BaseAdapter customAdapter) {
        this.context = context;
        this.window = window;
        this.selectListener = selectListener;
        this.menus = menus;
        this.type = TYPE_CUSTOM;
        this.customMenuLayout = customMenuLayout;
        this.customLayout = customLayout;
        this.customAdapter = customAdapter;
        initView();
    }


    private void initView() {
        view = LayoutInflater.from(context).inflate((type == TYPE_CUSTOM && customLayout != 0) ?
                customLayout : R.layout.view_pop_select_pop, null);

        cancelBut = (TextView) view.findViewById(R.id.but_cancel);

        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.onSelect(-1);
                dismiss();
            }
        });

        content_v = view.findViewById(R.id.content_v);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlph(1);
            }
        });

        innerListView = (InnerListView) view.findViewById(R.id.list_view);

        if (customAdapter != null) {
            innerListView.setAdapter(customAdapter);
        } else {
            if (type == TYPE_CUSTOM && customMenuLayout != 0) {
                innerAdapter = new InnerAdapter(customMenuLayout, context, menus);
            } else {
                innerAdapter = new InnerAdapter(context, menus, type);
            }
            innerListView.setAdapter(innerAdapter);
        }


        innerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectListener.onSelect(position);
                dismiss();
            }
        });

        setType();

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.no_bg));
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
    }

    private void setType() {
        switch (type) {
            case TYPE_IOS_TRANSPARENT:
                content_v.setPadding(DensityUtils.dp2px(context, 20), 0, DensityUtils.dp2px(context, 20), 0);
                break;
            case TYPE_NO_TRANSPARENT:
                content_v.setPadding(0, 0, 0, 0);
                content_v.setBackgroundColor(Color.parseColor("#F1F1F1"));
                innerListView.setDivider(new ColorDrawable(Color.parseColor("#F1F1F1")));
                innerListView.setDividerHeight(DensityUtils.dp2px(context, 1));
                cancelBut.setBackgroundResource(R.color.white);
                break;
        }
    }

    private void setWindowAlph(float alphaNumb) {
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = alphaNumb;
        window.setAttributes(params);
    }

    public void show() {
        showAtLocation(window.getDecorView().getRootView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        setWindowAlph(0.7f);
    }

    public TextView getCancelBut() {
        return cancelBut;
    }

    /**
     * 菜单的适配器,主要用于 设置菜单的样式
     *
     * @return
     */
    public InnerAdapter getInnerAdapter() {
        return innerAdapter;
    }

}
