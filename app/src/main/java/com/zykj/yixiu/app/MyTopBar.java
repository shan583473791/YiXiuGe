package com.zykj.yixiu.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;


/**
 * Created by zykj on 2017/4/11.
 */

public class MyTopBar extends RelativeLayout {

    //定义属性
    //标题
    private  String titleText;
    private  float  titleTextSize;
    private  int    titleTextColor;
    //左侧按钮
    private  String leftText;
    private  float  leftTextSize;
    private  int    leftTextColor;
    private Drawable leftBackground;
    //右侧按钮
    private Drawable rightBackground;
    //控件
    private TextView title,right;
    private ImageView leftImageView;
    private Context context;
    public MyTopBar(Context context) {
        super(context);
    }
    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        //获取自定义属性
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.MyTopBar);
        // 获取标题的属性
        titleText=td.getString(R.styleable.MyTopBar_titleText);
        titleTextSize=td.getDimension(R.styleable.MyTopBar_titleTextSize,0);
        titleTextColor=td.getColor(R.styleable.MyTopBar_titleTextColor,0);
        //获取左侧按钮的属性
        leftText=td.getString(R.styleable.MyTopBar_leftText);
        leftTextSize=td.getDimension(R.styleable.MyTopBar_leftTextSize,0);
        leftTextColor=td.getColor(R.styleable.MyTopBar_leftTextColor,0);
        leftBackground=td.getDrawable(R.styleable.MyTopBar_leftBackground);
        //获取右侧按钮的属性
        rightBackground=td.getDrawable(R.styleable.MyTopBar_rightBackground);
        //创建控件
        title=new TextView(context);
        right=new TextView(context);
        leftImageView=new ImageView(context);

        //把所有属性设置到对应控件上
        //传入所有Title的属性
        title.setText(titleText);
        title.setTextSize(titleTextSize);
        title.setTextColor(titleTextColor);
        title.setGravity(Gravity.CENTER); //文字内容居中


        //传入所有按钮的属性
        right.setText(leftText);
        right.setTextSize(leftTextSize);
        right.setTextColor(leftTextColor);
        right.setGravity(Gravity.CENTER); //文字内容居中
        right.setBackgroundDrawable(leftBackground);

        //传入所有按钮的属性
        leftImageView.setImageDrawable(rightBackground);

        //把控件设置到View中
        //控制title 的位置
        LayoutParams  titleParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT); //中间位置
        addView(title,titleParams);

        LayoutParams  leftParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); //右侧位置
        addView(right,leftParams);
        LayoutParams  rightParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT); //左侧位置
        addView(leftImageView,rightParams);
        // 调用点击函数
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightClick.Click(v);
            }
        });
        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                leftClick.Click(v);
            }
        });
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //添加点击事件的set函数
    public void setLeftClick(MyTopBar.leftClick leftClick) {
        this.leftClick = leftClick;
    }

    public void setRightClick(MyTopBar.rightClick rightClick) {
        this.rightClick = rightClick;
    }
    private leftClick leftClick; //成员变量用于映射外部传入的对象
    private rightClick rightClick; //成员变量用于映射外部传入的对象
    //添加回调接口
    public  interface  leftClick{
        void Click(View view); //左侧点击

    }  public  interface  rightClick{
        void Click(View view); //左侧点击

    }

}
