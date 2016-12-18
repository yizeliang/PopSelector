package cn.yzl.popselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.yzl.popselector.library.SelectPopOnSelectListener;
import cn.yzl.popselector.library.SelectPopWindow;

public class MainActivity extends AppCompatActivity {

    private Button button, button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPopWindow selectPopWindow = new SelectPopWindow(MainActivity.this,
                        new String[]{"item1", "条目2", "条目3"},
                        new SelectPopOnSelectListener() {
                            @Override
                            public void onSelect(int position) {
                                showToast(position);
                            }
                        }, getWindow(),
                        SelectPopWindow.TYPE_IOS_TRANSPARENT);

                //通过adapter设置 menu属性,别问我为什么没有封装起来,我懒....
//                selectPopWindow.getInnerAdapter().setMenuBGColor();
//                selectPopWindow.getInnerAdapter().setMenuBGRes();
//                selectPopWindow.getInnerAdapter().setTextColor();
//                selectPopWindow.getInnerAdapter().setTextSize();

                //取消按钮直接公布出来,能够随便设置属性
//                selectPopWindow.getCancelBut();

                selectPopWindow.show();
            }
        });


        button1 = (Button) findViewById(R.id.but1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPopWindow selectPopWindow = new SelectPopWindow(MainActivity.this,
                        new String[]{"item1", "条目2", "条目3"}, new SelectPopOnSelectListener() {
                    @Override
                    public void onSelect(int position) {
                        showToast(position);
                    }
                },
                        getWindow(),
                        SelectPopWindow.TYPE_NO_TRANSPARENT);

                selectPopWindow.show();
            }
        });


        button2 = (Button) findViewById(R.id.but2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPopWindow selectPopWindow = new SelectPopWindow(MainActivity.this,
                        new String[]{"看我可爱的分割线", "看我可爱的分割线", "看我可爱的分割线", "看我可爱的分割线"},
                        new SelectPopOnSelectListener() {
                            @Override
                            public void onSelect(int position) {
                                showToast(position);
                            }
                        },
                        getWindow(),
                        R.layout.view_pop_select_pop_custom,
                        R.layout.item_pop_selector_custom, null);

                //如果我只想自定义一个布局怎么办呢,其他的一个传0就好了哦
//                SelectPopWindow selectPopWindow = new SelectPopWindow(MainActivity.this,
//                        new String[]{"看我可爱的分割线", "看我可爱的分割线", "看我可爱的分割线", "看我可爱的分割线"},
//                        new SelectPopOnSelectListener() {
//                            @Override
//                            public void onSelect(int position) {
//                                showToast(position);
//                            }
//                        },
//                        getWindow(),
//                        0,
//                        R.layout.item_pop_selector_custom);
                selectPopWindow.show();
            }
        });

        button3 = (Button) findViewById(R.id.but3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPopWindow selectPopWindow = new SelectPopWindow(MainActivity.this,
                        new String[]{"条目1", "条目2", "条目3", "条目4", "条目5", "条目6", "条目7",
                                "条目8", "可以上下滑动的哦", "条目10", "条目11", "条目12", "条目13", "条目14", "条目15",
                                "条目16", "条目17", "条目18"},
                        new SelectPopOnSelectListener() {
                            @Override
                            public void onSelect(int position) {
                                showToast(position);
                            }
                        },
                        getWindow(),
                        SelectPopWindow.TYPE_IOS_TRANSPARENT);

                selectPopWindow.show();
            }
        });
    }

    public void showToast(int msg) {
        Toast.makeText(MainActivity.this, String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }


}
