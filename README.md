# CustomNavigatorBar
由于项目中经常用到此种组合控件,就封装了下，具体效果看下图，老司机可以绕道哈！

![Image.png](http://upload-images.jianshu.io/upload_images/3150565-ae7e2865f0ed281f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#####一、主要功能

* 支持左右图标动态设置
* 支持左右、中间文字动态修改
* 支持字体大小、颜色修改
* 支持左右图标，左中右文字隐藏显示
* 支持左右图标和文案的点击监听

#####二、基本使用方式

~~~
<com.example.android.customvView.CustomNavigatorBar
        android:id="@+id/customView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:leftImage="@drawable/leftarrow"
        app:rightImage="@drawable/rightarrow"
        app:leftImageVisiable="true"
        app:rightImageVisible="true"
        app:leftText="左边"
        app:rightText="右边"
        app:midText="标题"
        app:midTextFontColor="#ffffff"
        app:leftTextColor="#ffffff"
        app:rightTextColor="@color/colorAccent"
        app:titleBarBackground="@color/colorPrimary"
        app:midTextFontSize="18px"
        app:leftTextVisibale="true"
        app:rightTextVisible="true"
        app:leftTextFontSize="16px"
        app:rightTextFontSize="16px"
        />
~~~

####三、基本属性介绍
| 属性名    | 属性说明          | 属性值 |
|:-------------: |:-------------:|:-----:|
| titleBarBackground      | 标题栏背景色| color，reference，默认为white |
| leftImage     | 左边图片|   reference|
| leftImageVisiable |  左边图片是否可见     |  boolean，默认为true，显示控件   |
| leftText|    左边文案   |  string，reference    |
| leftTextVisibale|   左边文案是否可见     |   boolean，默认为true，显示控件   |
| leftTextFontSize|   左边文案字体大小    |   dimension，reference，默认为16sp  |
| leftTextColor|     左边文案字体颜色  |  color，reference   |
| midText|    中间文案   |   string，reference  |
| midTextVisiable|    中间文案是否可见   |   boolean，默认为true，显示控件   |
| midTextFontSize|   中间文案字体大小    |   dimension，reference，默认为18sp   |
| midTextFontColor|    中间文案字体颜色   |   color，reference   |
| rightText|   右边文案    |   color，reference   |
| rightTextVisible|   右边文案是否可见    |   boolean，默认为true，显示控件   |
| rightTextFontSize|   右边文案字体大小    |   dimension，reference，默认为16sp  |
| rightTextColor|    右边文案字体颜色   |  color，reference     |
| rightImage|    右边图片   |  reference   |
| rightImageVisible|    右边图片是否可见   |  boolean，默认为true，显示控件   |

####四、组合控件类
~~~
package com.example.android.customvView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.R;

/**
 * Created by WangLu on 2016/12/6.
 * E-mail:wang_lu90125@163.com
 */

public class CustomNavigatorBar extends RelativeLayout implements View.OnClickListener {

    private ImageView leftImage;
    private TextView leftText;
    private TextView midText;
    private ImageView rightImage;
    private TextView rightText;
    private OnCustomClickListener customClickListener ;

    public CustomNavigatorBar(Context context) {
        this(context,null);
    }

    public CustomNavigatorBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomNavigatorBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniView(context);
        /**
         * 两种初始化的不同，请看下面注释讲解
         */
        initOneType(context, attrs);//第一种初始化
//        initTwoType(context, attrs);//第二种初始化
    }

    private void iniView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true);
        leftImage = (ImageView) view.findViewById(R.id.left_image);
        leftText = (TextView) view.findViewById(R.id.left_text);
        midText = (TextView) view.findViewById(R.id.mid_text);
        rightText = (TextView) view.findViewById(R.id.right_text);
        rightImage = (ImageView) view.findViewById(R.id.right_image);

    }

    /**
     * 有兴趣的请参考鸿洋大神的自定义讲解
     *
     * 初始化属性值：这种写法，不管你在布局中有没有使用该属性，都会执行getXXX方法赋值
     *假设一个场景：
     *    private int attr_mode = 1;//默认为1
     *   //然后你在写getXXX方法的时候，是这么写的：
     *   attr_mode = array.getInt(i, 0);
     *
     *   可能你的自定义属性有个默认的值，然后你在写赋值的时候，一看是整形，就默默的第二个参数就给了个0，
     *   然而用户根本没有在布局文件里面设置这个属性，你却在运行时将其变为了0（而不是默认值），而第二种就不存在这个问题。
     *   当然这个场景可以由规范的书写代码的方式来避免，（把默认值提取出来，都设置对就好了）。
     *
     * 场景二：
     *
     *   其实还有个场景，假设你是继承自某个View，父类的View已经对该成员变量进行赋值了，然后你这边需要根据用户的设置情况，
     *   去更新这个值，第一种写法，如果用户根本没有设置，你可能就将父类的赋值给覆盖了。
     *
     * @param context
     * @param attrs
     */
    private void initTwoType(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNavigatorBar);
        if (null != typedArray) {
            Drawable leftDrawable = typedArray.getDrawable(R.styleable.CustomNavigatorBar_leftImage);
            leftImage.setImageDrawable(leftDrawable);

            boolean leftImageVisible = typedArray.getBoolean(R.styleable.CustomNavigatorBar_leftImageVisiable, false);
            if (leftImageVisible) {
                leftImage.setVisibility(View.VISIBLE);
            } else {
                leftImage.setVisibility(View.GONE);
            }

            typedArray.recycle();
        }
    }

    /**注：如果switch报错，请改为if-else
     * 初始化属性值：这种写法，只有在布局中设置了该属性值后，才会调用getXXX()方法赋值
     * @param context
     * @param attrs
     */
    private void initOneType(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNavigatorBar);
        int totalAttributes = typedArray.getIndexCount();
        for (int i = 0 ; i<totalAttributes ;i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.CustomNavigatorBar_leftImage:
                    leftImage.setImageDrawable(typedArray.getDrawable(index));
                    break ;
                case R.styleable.CustomNavigatorBar_leftImageVisiable:
                    getVisible(typedArray,leftImage,index);
                    break ;
                case R.styleable.CustomNavigatorBar_leftText:
                    leftText.setText(typedArray.getString(index));
                    break ;
                case R.styleable.CustomNavigatorBar_leftTextFontSize:
                    leftText.setTextSize(typedArray.getDimensionPixelSize(index, (int) sp2px(context,16)));
                    break ;
                case R.styleable.CustomNavigatorBar_leftTextColor:
                    leftText.setTextColor(typedArray.getColor(index, Color.WHITE));
                    break ;
                case R.styleable.CustomNavigatorBar_leftTextVisibale:
                    getVisible(typedArray,leftText,index);
                    break ;
                case R.styleable.CustomNavigatorBar_midText:
                    midText.setText(typedArray.getString(index));
                    break ;
                case R.styleable.CustomNavigatorBar_midTextVisiable:
                    getVisible(typedArray,midText,index);
                    break ;
                case R.styleable.CustomNavigatorBar_midTextFontSize:
                    midText.setTextSize(typedArray.getDimensionPixelSize(index,(int) sp2px(context,18)));
                    break ;
                case R.styleable.CustomNavigatorBar_midTextFontColor:
                    midText.setTextColor(typedArray.getColor(index,Color.WHITE));
                case R.styleable.CustomNavigatorBar_rightImage:
                    rightImage.setImageDrawable(typedArray.getDrawable(index));
                    break ;
                case R.styleable.CustomNavigatorBar_rightImageVisible:
                    getVisible(typedArray,rightImage,index);
                    break ;
                case R.styleable.CustomNavigatorBar_rightText:
                    rightText.setText(typedArray.getString(index));
                    break ;
                case R.styleable.CustomNavigatorBar_rightTextFontSize:
                    rightText.setTextSize(typedArray.getDimensionPixelSize(index,(int) sp2px(context,16)));
                    break ;
                case R.styleable.CustomNavigatorBar_rightTextColor:
                    rightText.setTextColor(typedArray.getColor(index, Color.WHITE));
                    break ;
                case R.styleable.CustomNavigatorBar_rightTextVisible:
                    getVisible(typedArray,rightText,index);
                    break ;
                case R.styleable.CustomNavigatorBar_titleBarBackground:
                    int titleBarBackgroundColor = typedArray.getColor(index, Color.GREEN);
                    setBackgroundColor(titleBarBackgroundColor);
                    break ;
            }
        }
        typedArray.recycle();
    }

    /**
     * 用来隐藏显示View，只有gone 和 visible两种情况，因为inVisible感到在这里用不到，就没有封装
     * @param typedArray
     * @param view
     * @param index
     */
    private void getVisible(TypedArray typedArray ,View view,int index) {
        boolean visible = typedArray.getBoolean(index, false);
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setVisible(View view ,boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 两种监听只能使用其中一种，不能同时使用
     *
     * ----------------------------第一种点击监听开始处----------------------------------------
     * @param clickListener
     */
    public void setLeftImageOnClickListener(View.OnClickListener clickListener) {
        if (null != clickListener) {
            leftImage.setOnClickListener(clickListener);
        }
    }

    public void setLeftTextOnClickListener(View.OnClickListener clickListener) {
        if (null != clickListener) {
            leftText.setOnClickListener(clickListener);
        }
    }
    public void setRightImageOnClickListener(View.OnClickListener clickListener) {
        if (null != clickListener) {
            rightImage.setOnClickListener(clickListener);
        }
    }

    public void setRightTextOnClickListener(View.OnClickListener clickListener) {
        if (null != clickListener) {
            rightText.setOnClickListener(clickListener);
        }
    }

    /**
     * ----------------------------第二种点击监听开始处----------------------------------------
     * @return
     */
    public void addViewClickListener(OnCustomClickListener listener) {
        leftText.setOnClickListener(this);
        leftImage.setOnClickListener(this);
        rightImage.setOnClickListener(this);
        rightText.setOnClickListener(this);
        this.customClickListener = listener ;
    }

    public interface OnCustomClickListener{
        void onClickListener(View rootView);
    }

    @Override
    public void onClick(View view) {
        customClickListener.onClickListener(view);
    }

    /**
     * ----------------------------第二种点击监听结束处----------------------------------------
     * @return
     */

    public ImageView getLeftImageView(){
        return leftImage;
    }
    public ImageView getRightImage(){
        return rightImage;
    }

    public TextView getLeftText(){
        return leftText;
    }

    public TextView getRightText(){
        return rightText;
    }

    public TextView getMidText(){
        return midText;
    }

    /**
     * 设置textView的标题内容
     * @param textDescribe
     */
    public void setLeftText(String textDescribe) {
        if (null != textDescribe) {
            leftText.setText(textDescribe);
        }
    }
    public void setMidText(String textDescribe) {
        if (null != textDescribe) {
            midText.setText(textDescribe);
        }
    }

    public void setRightText(String textDescribe) {
        if (null != textDescribe) {
            rightText.setText(textDescribe);
        }
    }

    /**
     * 设置textView的字体颜色
     * @param textColor
     */
    public void setLeftTextColor(int textColor) {
        leftText.setTextColor(textColor);
    }
    public void setMidTextColor(int textColor) {
        midText.setText(textColor);
    }

    public void setRightTextColor(int textColor) {
        rightText.setText(textColor);
    }

    /**
     * 设置title栏背景色
     * @param color
     */
    public void setTitleBarBackground(int color) {
        setBackgroundColor(color);
    }

    /**
     * 左右控件的隐藏显示
     * @param visible
     */
    public void setLeftImageVisible(boolean visible) {
        setVisible(leftImage, visible);
    }
    public void setLeftTextVisible(boolean visible) {
        setVisible(leftText, visible);
    }
    public void setRifhtImageVisible(boolean visible) {
        setVisible(rightImage, visible);
    }
    public void setRightTextVisible(boolean visible) {
        setVisible(rightText, visible);
    }


    private float sp2px(Context context, float defaultVal) {
        float applyDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, defaultVal, context.getResources().getDisplayMetrics());
        return applyDimension ;
    }

    private float dp2px(Context context, float defaultVal) {
        float applyDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, defaultVal, context.getResources().getDisplayMetrics());
        return applyDimension ;
    }

    private float dp2px(Context context, int defultVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (float) (defultVal*scale + 0.5);
    }

}

~~~

####五、attrs.xml

~~~
<?xml version="1.0" encoding="utf-8" ?>
<resources>
    <declare-styleable name = "CustomNavigatorBar">
        <attr name="titleBarBackground" format="reference|color" />
        <attr name="leftImage" format="reference" />
        <attr name="leftImageVisiable" format="boolean" />
        <attr name="leftText" format="string|reference" />
        <attr name="leftTextVisibale" format="boolean" />
        <attr name="leftTextFontSize" format="dimension|reference" />
        <attr name="leftTextColor" format="color|reference" />
        <attr name="midText" format="string|reference" />
        <attr name="midTextVisiable" format="boolean" />
        <attr name="midTextFontSize" format="dimension|reference" />
        <attr name="midTextFontColor" format="color|reference" />
        <attr name="rightText" format="string|reference" />
        <attr name="rightTextVisible" format="boolean" />
        <attr name="rightTextFontSize" format="dimension|reference" />
        <attr name="rightTextColor" format="color|reference" />
        <attr name="rightImage" format="reference" />
        <attr name="rightImageVisible" format="boolean" />
    </declare-styleable>
</resources>
~~~

####六、组合控件布局（custom_title_bar.xml）
      为什么使用merge，因为组合控件已经extends RelativeLayout，如果根布局还是用viewGroup的话，会使布局重复嵌套，影响View的绘制性能；

~~~
<?xml version = "1.0" encoding = "utf-8" ?>
<merge xmlns:android="http://schemas.android.com/apk/res/android">

    <ImageView
        android:id="@+id/left_image"
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:layout_centerVertical="true"
        android:minHeight="20dp"
        android:minWidth="20dp"
        android:layout_marginLeft="10dp"
        android:src="@drawable/ic_launcher" />

    <TextView
        android:id="@+id/left_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_marginLeft="5dp"
        android:layout_toRightOf="@+id/left_image"
        android:gravity="center"
        android:minHeight="45dp"
        android:text="left" />

    <TextView
        android:id="@+id/mid_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:gravity="center"
        android:minHeight="45dp"
        android:text="mid"
        android:textSize="17sp" />

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:gravity="center"
        android:minHeight="45dp"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/right_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginRight="5dp"
            android:layout_toLeftOf="@+id/right_image"
            android:gravity="center"
            android:minHeight="45dp"
            android:text="right" />

        <ImageView
            android:id="@+id/right_image"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:minHeight="20dp"
            android:minWidth="20dp"
            android:src="@drawable/ic_launcher" />
    </LinearLayout>
</merge>
~~~

####七、具体使用

~~~
CustomNavigatorBar customNavigatorBar = (CustomNavigatorBar) findViewById(R.id.customView);
        /**
         * 第一种监听的具体实现
         */
        customNavigatorBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"left",Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 第二种监听的具体实现
         */
        customNavigatorBar.addViewClickListener(new CustomNavigatorBar.OnCustomClickListener() {
            @Override
            public void onClickListener(View rootView) {
                switch (rootView.getId()) {
                    case R.id.right_image:
                        Toast.makeText(MainActivity.this,"right_image is clicked",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.left_image:
                        Toast.makeText(MainActivity.this,"left_image is clicked",Toast.LENGTH_SHORT).show();
                        break ;
                }
            }
        });
~~~

###如有什么问题，敬请提出，十分感谢！希望越来越好，谢谢！
####如果喜欢，还请点击喜欢支持一下了，谢谢O(∩_∩)O~。
