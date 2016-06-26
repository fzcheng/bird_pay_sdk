package com.legame.paysdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-21
 * @version 1.0
 */
public class RegisterDialog extends Dialog implements android.view.View.OnClickListener{

	public static final int PAGE_TYPE_DEFAULT = 1;
	public static final int PAGE_TYPE_RETRIVE_ACCOUNT = 2;
	
	private String mUserName;
	private String mPwd;
	
	private TextView mUserNameTv;
	private EditText mPwdEt;
	private Button mModifyBtn;
	private Button mLoginBtn;
	
	private int mPageType = PAGE_TYPE_DEFAULT;
	
	private RegisterDialogListener mListener;
	
	private boolean mIsModifyPwd = false;
	
	public interface RegisterDialogListener{
		void onRegisterLogin(String userName, String pwd ,String newPwd);
		void onChangeAccount(String userName, String pwd);
		void onClickServiceTerms();
	}
	
	public RegisterDialog(Context context , String userName, String password , int pageType) {
		super(context, ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		mUserName = userName;
		mPwd = password;
		mPageType = pageType;
		init();
	}
	
	private void init(){
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_register_layout"));
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		mUserNameTv = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_regisger_username_tv"));
		mPwdEt = (EditText) findViewById(ResourceUtil.getId(getContext(), "lgsdk_register_pwd_et"));
		mModifyBtn = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_register_modify_btn"));
		mLoginBtn = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_register_login_btn"));
		TextView changeAccoutTv = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_register_change_account_tv"));
		TextView protocolTv = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_register_protocol_tv"));
		TextView hintTv = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_regisger_hint_tv"));
		
		mUserNameTv.setText(mUserName);
		mPwdEt.setText(mPwd);
		mPwdEt.setEnabled(false);
		mModifyBtn.setOnClickListener(this);
		mLoginBtn.setOnClickListener(this);
		protocolTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		protocolTv.setOnClickListener(this);
		changeAccoutTv.setOnClickListener(this);
		
		if(mPageType == PAGE_TYPE_RETRIVE_ACCOUNT){
			hintTv.setText(ResourceUtil.getString(getContext(), "lgsdk_retrive_account_success"));
		}
	}
	
	public void setRegisterDialogListener(RegisterDialogListener l){
		mListener = l;
	}

	private void login(){
		if(mIsModifyPwd){
			String pwd = mPwdEt.getText().toString();
			if(TextUtils.isEmpty(pwd)){
				Toast.makeText(getContext(), ResourceUtil.getString(getContext(), "lgsdk_please_input_password"), Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(!pwd.matches("^[0-9a-zA-Z]{6,20}$")){
				Toast.makeText(getContext(), ResourceUtil.getString(getContext(), "lgsdk_new_pwd_hint"), Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(mListener != null){
				mListener.onRegisterLogin(mUserName, mPwd, pwd);
			}
			
		} else {
			if(mListener != null){
				mListener.onRegisterLogin(mUserName, mPwd, null);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_register_login_btn")){
			login();
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_register_modify_btn")){
			if(!mIsModifyPwd){
				mPwdEt.setText("");
				mPwdEt.setEnabled(true);
				mModifyBtn.setText(ResourceUtil.getString(getContext(), "lgsdk_cancel"));
				mLoginBtn.setText(ResourceUtil.getString(getContext(), "lgsdk_save_pwd_and_login"));
				mIsModifyPwd = true;
			} else {
				mPwdEt.setText(mPwd);
				mPwdEt.setEnabled(false);
				mModifyBtn.setText(ResourceUtil.getString(getContext(), "lgsdk_modify"));
				mLoginBtn.setText(ResourceUtil.getString(getContext(), "lgsdk_now_login"));
				mIsModifyPwd = false;
			}
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_register_change_account_tv")){
			if(mListener != null){
				mListener.onChangeAccount(mUserName, mPwd);
			}
			dismiss();
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_register_protocol_tv")){
			if(mListener != null){
				mListener.onClickServiceTerms();
			}
		}
		
	}

}
