package com.legame.paysdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.legame.paysdk.utils.ResourceUtil;

/** 
 * 类说明：   
 * @author  xiaodm
 * @date    2014年12月25日
 * @version 1.0
 */
public class MoreConfirmDialog extends Dialog {
	private RelativeLayout mButtonLayout;
	private TextView mContent1;
	private Button mButton1;
	private Button mButton2;
	/*
	 * 图标类型
	 */
	public static final int TYPE_ERROR = 1;
	public static final int TYPE_OK = 2;
	public static final int TYPE_INFO = 3;
	public static final int TYPE_QUESTION = 4;
	
	public MoreConfirmDialog(Context context) {
		super(context,ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		init();
	}
	
	public void init(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_canceltips_dialog"));
		getWindow().getAttributes().gravity = Gravity.CENTER;
		mButtonLayout = (RelativeLayout) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button_layout"));
		mContent1 = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_content"));
		mButton1 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button1"));
		mButton2 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button2"));
		//将默认隐藏的组件设为隐藏
		mButtonLayout.setVisibility(View.GONE);
		mButton1.setVisibility(View.GONE);
		mButton2.setVisibility(View.GONE);
		
		ClickListener listener = new ClickListener();
		mButton1.setOnClickListener(listener);
		mButton2.setOnClickListener(listener);
	}

	/**
	 * 设置提示内容1
	 * @param content 要提示的内容字符串
	 * @return 当前的对话框对象
	 */
	public MoreConfirmDialog setContent1(String content){
		if(content != null && !"".equals(content)){
			mContent1.setText(content);
		}
		return this;
	}
	/**
	 * 设置提示内容1
	 * @param resId 要提示的字符串资源ID
	 * @return 当前的对话框对象
	 */
	public MoreConfirmDialog setContent1(int resId){
		mContent1.setText(resId);
		return this;
	}
	/**
	 * 设置并显示按钮1
	 * @param text 按钮的文本字符串
	 * @param listener 按钮单击事件监听
	 * @return 当前的对话框对象
	 */
	public MoreConfirmDialog showButton1(String text , android.view.View.OnClickListener listener){
		mButtonLayout.setVisibility(View.VISIBLE);
		if(text != null && !"".equals(text)){
			mButton1.setText(text);
		}
		if(listener != null){
			mButton1.setOnClickListener(listener);
		}
		mButton1.setVisibility(View.VISIBLE);
		return this;
	}
	

	/**
	 * 设置并显示按钮2
	 * @param text 按钮的文本字符串
	 * @param listener 按钮单击事件监听
	 * @return 当前的对话框对象
	 */
	public MoreConfirmDialog showButton2(String text , android.view.View.OnClickListener listener){
		mButtonLayout.setVisibility(View.VISIBLE);
		if(text != null && !"".equals(text)){
			mButton2.setText(text);
		}
		if(listener != null){
			mButton2.setOnClickListener(listener);
		}
		mButton2.setVisibility(View.VISIBLE);
		return this;
	}
	
	/**
	 * 所有按钮的默认单击事件监听
	 * @author xinhui.cheng
	 *
	 */
	private class ClickListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			dismiss();
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(android.view.KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER
    			|| event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN
    			|| event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP
    			|| event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT
    			|| event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT){
    		return true;
    	}
		
		return super.dispatchKeyEvent(event);
		
	}
}
