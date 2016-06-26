package com.legame.paysdk.dialog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.db.AnonymousLoginDb;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.dialog.AccountListDialog.AccountListDialogListener;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-16
 * @version 1.0
 */
public class UserLoginDialog extends Dialog implements android.view.View.OnClickListener,AccountListDialogListener{

	private Activity mActivity;
	private EditText mAccountEt;
	private EditText mPasswordEt;
	private CheckBox mAutoLoginCb;
	private TextView mRetriveTv;
	
	private ArrayList<UserInfo> mUserList = new ArrayList<UserInfo>();
	
	private UserLoginDialogListener mListener;
	
	public interface UserLoginDialogListener{
		void onOneKeyRegister();
		void onLogin(String userName, String password);
		void onRetriveAccount();
	}
	
	public UserLoginDialog(Activity context) {
		super(context , ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		mActivity = context;
		init();
	}
	
	
	private void init(){
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_user_login_layout"));
		mAccountEt = (EditText) findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_et"));
		mPasswordEt = (EditText) findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_pwd_et"));
		mAutoLoginCb = (CheckBox) findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_checkbox"));
		mAutoLoginCb.setChecked(UserInfo.getAutoLoginState(getContext()));
		mRetriveTv = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_retrive_account_tv"));
		findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setOnClickListener(this);
		findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_onekey_register_btn")).setOnClickListener(this);
		findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_login_btn")).setOnClickListener(this);
		mRetriveTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		mRetriveTv.setOnClickListener(this);
		
		initData();
		
	}
	
	private void initData(){
		String currentUserName = UserInfo.getCurrentLoginUserName(mActivity);
		ArrayList<UserInfo> userList= UserInfoDb.getInstance().getNoRepeatUserListOrderByGame(mActivity.getPackageName());
		if(userList == null){
			userList = new ArrayList<UserInfo>();
		}
		if (userList.size() == 0) {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.GONE);
		} else {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.VISIBLE);
		}
		if(GlobalVal.sIsAnonymous){
			String un = AnonymousLoginDb.getInstance().getUsername(mActivity.getPackageName());
			if (!TextUtils.isEmpty(un)) {
				UserInfoDb db = UserInfoDb.getInstance();
				UserInfo info = db.getUserInfoByUsername(un);
				if (info != null) {
					mAccountEt.setText(info.getUserName());
					mPasswordEt.setText(info.getPassword());
				}
			} else {
				UserInfoDb db = UserInfoDb.getInstance();
				UserInfo info = db.getLastLoginUser();
				if (info == null) {
					info = db.getNotLoginUserInfo();
				}
				if (info != null) {
					mAccountEt.setText(info.getUserName());
					mPasswordEt.setText(info.getPassword());
				}
			}
			mUserList = userList;
			return;
		}
		
		
		if(TextUtils.isEmpty(currentUserName)){
			if(userList.size() > 0){
				mUserList = userList;
				UserInfo info = mUserList.get(0);
				mAccountEt.setText(info.getUserName());
				mPasswordEt.setText(info.getPassword());
			}
		} else {
			UserInfo info = UserInfoDb.getInstance().getUserInfoByUsername(currentUserName);
			if(info != null){
				mAccountEt.setText(info.getUserName());
				mPasswordEt.setText(info.getPassword());
				int index = userList.indexOf(info);
				if(index >= 0){
					userList.remove(index);
				}
				mUserList.add(info);
			} else {
				if(userList.size() > 0){
					UserInfo info1 = userList.get(0);
					mAccountEt.setText(info1.getUserName());
					mPasswordEt.setText(info1.getPassword());
				}
			}
			mUserList.addAll(userList);
		}
	}
	
	public void updateUserList() {
		mUserList.clear();
		initData();
	}
	public void setAccountInfo(String userName,String password){
		mAccountEt.setText(userName);
		mPasswordEt.setText(password);
//		UserInfo info = new UserInfo();
//		info.setUserName(userName);
//		info.setPassword(password);
//		mUserList.add(0, info);
	}
	
	public void setUserLoginDialogListener(UserLoginDialogListener listener){
		mListener = listener;
	}
	
	public boolean getAutoLoginState(){
		return mAutoLoginCb.isChecked();
	}
	
	private void login(){
		String username = mAccountEt.getText().toString();
		String pwd = mPasswordEt.getText().toString();
		if(TextUtils.isEmpty(username)){
			Toast.makeText(mActivity, ResourceUtil.getString(getContext(), "lgsdk_please_input_username"), Toast.LENGTH_SHORT).show();
			return ;
		}
		if(TextUtils.isEmpty(pwd)){
			Toast.makeText(mActivity, ResourceUtil.getString(getContext(), "lgsdk_please_input_password"), Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(!pwd.matches("^[0-9a-zA-Z]{6,20}$")){
			Toast.makeText(mActivity, ResourceUtil.getString(getContext(), "lgsdk_new_pwd_hint"), Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(mListener != null){
			mListener.onLogin(username, pwd);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")){	//点击弹出更多账号
			if(mUserList != null){
				AccountListDialog dialog = new AccountListDialog(mActivity, mUserList);
				dialog.setAccountListDialogListener(this);
				dialog.show();
			}
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_user_login_onekey_register_btn")){//一键注册
			if(mListener != null){
				mListener.onOneKeyRegister();
			}
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_user_login_login_btn")){//登录
			login();
		} else if(v.getId() == ResourceUtil.getId(getContext(), "lgsdk_user_login_retrive_account_tv")){
			if(mListener != null ){
				mListener.onRetriveAccount();
			}
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			this.cancel();
			mActivity.finish();
			if(ListenerHolder.sLoginListener != null){
				ListenerHolder.sLoginListener.onGameCallback(ErrorCode.ERROR_LOGIN_CANCELED, "login canceled");
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	
	@Override
	public void onSelectAccount(UserInfo info) {
		mAccountEt.setText(info.getUserName());
		mPasswordEt.setText(info.getPassword());
		mUserList.remove(info);
		mUserList.add(0, info);
	}

	@Override
	public void onDeleteAccount(UserInfo deleteInfo) {
		if(deleteInfo.getUserName().equals(mAccountEt.getText().toString())){
			UserInfo info = mUserList.get(0);
			mAccountEt.setText(info.getUserName());
			mPasswordEt.setText(info.getPassword());
		}
		
		ArrayList<UserInfo> userList= UserInfoDb.getInstance().getNoRepeatUserListOrderByGame(mActivity.getPackageName());
		
		if (userList != null && userList.size() == 0) {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.GONE);
		} else {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onAllDeleted() {
		mAccountEt.setText("");
		mPasswordEt.setText("");
		
		ArrayList<UserInfo> userList= UserInfoDb.getInstance().getNoRepeatUserListOrderByGame(mActivity.getPackageName());
		
		if (userList != null && userList.size() == 0) {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.GONE);
		} else {
			findViewById(ResourceUtil.getId(getContext(), "lgsdk_user_login_username_arrow")).setVisibility(View.VISIBLE);
		}
	}
}
