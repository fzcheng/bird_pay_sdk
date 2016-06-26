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
 * @author  Terry.Lu
 * @date    2013年7月11日
 * @version 1.0
 */
public class TextDialog extends Dialog {
	private RelativeLayout mTitleLayout;
	private RelativeLayout mButtonLayout;
	private TextView mTitle;
	private TextView mContent;
	private Button mButton1;
	private Button mButton2;
	private Button mButton3;
	
	/*
	 * 图标类型
	 */
	public static final int TYPE_ERROR = 1;
	public static final int TYPE_OK = 2;
	public static final int TYPE_INFO = 3;
	public static final int TYPE_QUESTION = 4;
	
	public TextDialog(Context context) {
		super(context,ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		init();
	}
	
	public void init(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_text_dialog"));
		getWindow().getAttributes().gravity = Gravity.CENTER;
		mTitleLayout = (RelativeLayout) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_title_layout"));
		mButtonLayout = (RelativeLayout) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button_layout"));
		mTitle = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_title"));
		mContent = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_content"));
		mButton1 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button1"));
		mButton2 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button2"));
		mButton3 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_text_dialog_button3"));
		
		//将默认隐藏的组件设为隐藏
		mTitleLayout.setVisibility(View.GONE);
		mTitle.setVisibility(View.GONE);
		mButtonLayout.setVisibility(View.GONE);
		mButton1.setVisibility(View.GONE);
		mButton2.setVisibility(View.GONE);
		mButton3.setVisibility(View.GONE);
		
		ClickListener listener = new ClickListener();
		mButton1.setOnClickListener(listener);
		mButton2.setOnClickListener(listener);
		mButton3.setOnClickListener(listener);
	}
	
	/**
	 * 设置并显示标题
	 * @param title 要显示的标题字符串
	 * @return 当前的对话框对象
	 */
	public TextDialog showTitle(String title){
		if(title != null && !"".equals(title)){
			mTitle.setText(title);
			mTitle.setVisibility(View.VISIBLE);
			mTitleLayout.setVisibility(View.VISIBLE);
		}
		return this;
	}
	/**
	 * 设置并显示标题
	 * @param resId 要显示的标题的字符串资源ID
	 * @return 当前的对话框对象
	 */
	public TextDialog showTitle(int resId){
		mTitle.setText(resId);
		mTitle.setVisibility(View.VISIBLE);
		mTitleLayout.setVisibility(View.VISIBLE);
		return this;
	}
	/**
	 * 设置提示内容
	 * @param content 要提示的内容字符串
	 * @return 当前的对话框对象
	 */
	public TextDialog setContent(String content){
		if(content != null && !"".equals(content)){
			mContent.setText(content);
		}
		return this;
	}
	/**
	 * 设置提示内容
	 * @param resId 要提示的字符串资源ID
	 * @return 当前的对话框对象
	 */
	public TextDialog setContent(int resId){
		mContent.setText(resId);
		return this;
	}
	
//	/**
//	 * 设置并显示自定义图标
//	 * @param imageResId 图片资源ID
//	 * @return 当前的对话框对象
//	 */
//	public TextDialog showCustomIcon(int imageResId){
//		mIcon_iv.setImageResource(imageResId);
//		mIcon_iv.setVisibility(View.VISIBLE);
//		return this;
//	}

	/**
	 * 设置并显示按钮1
	 * @param text 按钮的文本字符串
	 * @param listener 按钮单击事件监听
	 * @return 当前的对话框对象
	 */
	public TextDialog showButton1(String text , android.view.View.OnClickListener listener){
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
	public TextDialog showButton2(String text , android.view.View.OnClickListener listener){
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
	 * 设置并显示按钮3
	 * @param text 按钮的文本字符串
	 * @param listener 按钮单击事件监听
	 * @return 当前的对话框对象
	 */
	public TextDialog showButton3(String text , android.view.View.OnClickListener listener){
		mButtonLayout.setVisibility(View.VISIBLE);
		if(text != null && !"".equals(text)){
			mButton3.setText(text);
		}
		mButton3.setOnClickListener(listener);
		mButton3.setVisibility(View.VISIBLE);
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
