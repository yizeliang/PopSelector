package cn.yzl.popselector.library.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.yzl.popselector.library.R;
import cn.yzl.popselector.library.SelectPopWindow;

/**
 *
 * Created by 易点付 伊 on 2016/12/18.
 */
public class InnerAdapter extends BaseAdapter {

    private int customMenuLayout;

    private Context context;

    private String[] data;

    private int type;

    private int textColor;

    private int textSize;


    private int menuBGColor;

    private int menuBGRes;


    public InnerAdapter(Context context, String[] data, int type) {
        this.context = context;
        this.data = data;
        this.type = type;
    }

    public InnerAdapter(@LayoutRes int customMenuLayout, Context context, String[] data) {
        this.context = context;
        this.data = data;
        this.customMenuLayout = customMenuLayout;
        this.type = SelectPopWindow.TYPE_CUSTOM;

    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        int a=type;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(type == SelectPopWindow.TYPE_CUSTOM ? customMenuLayout : R.layout.item_pop_selector_yzl_cn, null);
            viewHoder = new ViewHoder();
            viewHoder.textView = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        switch (type) {
            case SelectPopWindow.TYPE_IOS_TRANSPARENT:
                viewHoder.textView.setBackgroundResource(R.drawable.bg_menu);
                break;
            case SelectPopWindow.TYPE_NO_TRANSPARENT:
                viewHoder.textView.setBackgroundResource(R.drawable.no_bg);
                viewHoder.textView.setBackgroundResource(R.color.white);
                break;
        }

        viewHoder.textView.setText(data[position]);

        if (textColor != 0) {
            viewHoder.textView.setTextColor(textColor);
        }
        if (textSize != 0) {
            viewHoder.textView.setTextSize(textSize);
        }

        if (menuBGColor != 0) {
            viewHoder.textView.setBackgroundColor(menuBGColor);
        }

        if (menuBGRes != 0) {
            viewHoder.textView.setBackgroundResource(menuBGRes);
        }

        return convertView;
    }

    class ViewHoder {
        TextView textView;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        notifyDataSetChanged();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();

    }

    public void setMenuBGColor(int menuBGColor) {
        this.menuBGColor = menuBGColor;
        notifyDataSetChanged();
    }

    public void setMenuBGRes(@DrawableRes int menuBGRes) {
        this.menuBGRes = menuBGRes;
        notifyDataSetChanged();
    }
}
