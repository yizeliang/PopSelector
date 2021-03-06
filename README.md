# PopSelector
从下部弹出的选择框,修改头像最容易用到,拍照,选择图片


## 1 效果图
![image](https://github.com/yizeliang/PopSelector/raw/master/img/1.png)

![image](https://github.com/yizeliang/PopSelector/raw/master/img/2.png)

![image](https://github.com/yizeliang/PopSelector/raw/master/img/3.png)

![image](https://github.com/yizeliang/PopSelector/raw/master/img/4.png)

## 2 使用

对应1 中的 效果

```java
			//仿IOS 透明效果
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
				//不透明效果
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
				//自定义布局,详细请看demo
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
                        R.layout.item_pop_selector_custom);

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
                
                
				//很多条目的时候的状态
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

```

## 依赖

```gradle

//工程gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
//module
 dependencies {
	        compile 'com.github.yizeliang:PopSelector:1.0'
	}


```