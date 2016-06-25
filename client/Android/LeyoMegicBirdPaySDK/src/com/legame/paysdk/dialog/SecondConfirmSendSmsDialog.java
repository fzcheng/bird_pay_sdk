package com.legame.paysdk.dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.legame.paysdk.utils.ResourceUtil;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.18
 * @类说明 对于虚拟码两个弹框的操作
 *
 */
public class SecondConfirmSendSmsDialog extends Dialog {
	
	private ImageButton bt_cancle;

	private Button bt_send;

	private TextView tv_explain;
	
	private EditText et_inputMsg;
	
	private String virtualCode;

	public SecondConfirmSendSmsDialog(Context context) {
		super(context);
		init();
	}

	private void init() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(ResourceUtil.getLayout(getContext(),
				"lgsdk_confirm_dialog_virtual_code_sure"));
		getWindow().getAttributes().gravity = Gravity.CENTER;
		
		bt_cancle = (ImageButton) findViewById(ResourceUtil.getId(getContext(), "lgsdk_virtual_code_cancel"));
		bt_send = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_confirm_dialog_sure"));
		tv_explain = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_confirm_dialog_sure_explain"));
		et_inputMsg = (EditText) findViewById(ResourceUtil.getId(getContext(), "lgsdk_confirm_dialog_input_virtualcode"));
		et_inputMsg.setVisibility(View.GONE);
		et_inputMsg.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				virtualCode = String.valueOf(s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(isPattern(virtualCode)){
					bt_send.setEnabled(true);
				}
			}
		});
	}
	
	public void setButtonCancle(View.OnClickListener onClickListener){
		bt_cancle.setOnClickListener(onClickListener);
	}
	
	public void setButtonSend(View.OnClickListener onClickListener){
		bt_send.setOnClickListener(onClickListener);
	}
	
	public void setExplain(String explain){
		if(!TextUtils.isEmpty(explain)){
			tv_explain.setVisibility(View.VISIBLE);
			tv_explain.setText(explain);
			tv_explain.setTextColor(Color.RED);
		}
	}
	
	public void setEditText(){
		et_inputMsg.setVisibility(View.VISIBLE);
	}
	
	public String getVirtualCode(){
		return virtualCode;	
	}
	
	private static String patternVirtualCode = "(?<!\\d)\\d{10}(?!\\d)";
	public static boolean isPattern(String message){
		Pattern p = Pattern.compile(patternVirtualCode);
		Matcher matcher = p.matcher(message);
		return matcher.matches();
	}

}
