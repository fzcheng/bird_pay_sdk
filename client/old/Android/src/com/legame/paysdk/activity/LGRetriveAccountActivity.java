package com.legame.paysdk.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.RetriveAccountNetEngine;
import com.legame.paysdk.network.engine.RetriveAccountSMSNetEngine;
import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.resultdata.RetriveAccountResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.utils.DataValidUtil;
import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   重置密码
 * @author  xinhui.cheng
 * @date    2013-10-21
 * @version 1.0
 */
public class LGRetriveAccountActivity extends Activity implements OnClickListener,NetTaskListener{

	private static final int TAG_PHONE = 1;
	private static final int TAG_VCODE = 2;
	
	private View mPhoneView;
	private View mVCodeView;
	private EditText mPhoneEt;
	private EditText mVCodeEt;
	private TextView mHintForgotPwdTv;
	private TextView mHintTv;
	
	private NetTask mNetTask;
	
	private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(ResourceUtil.getLayout(this, "lgsdk_retrive_account_layout"));
		switch (GlobalVal.getOrientation(this)) {
		case ORIENTATION_LANDSCAPE:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		case ORIENTATION_PORTRAIT:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		}
		initViews();
		
	}
	
	private void initViews(){
		initTitle();
		initContentViews();
	}
	
	private void initTitle(){
		Button backBtn = (Button) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_back_btn"));
		TextView titleTv = (TextView) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_title_content"));
		backBtn.setText(ResourceUtil.getString(this, "lgsdk_back"));
		titleTv.setText(ResourceUtil.getString(this, "lgsdk_retrive_account"));
		backBtn.setOnClickListener(this);
	}
	
	private void initContentViews(){
		mPhoneView = findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_phone_layout"));
		mVCodeView = findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_vcode_layout"));
		
		mPhoneEt = (EditText) mPhoneView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_et"));
		mVCodeEt = (EditText) mVCodeView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_et"));
		mHintTv = (TextView) mVCodeView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_hint_tv"));
		mHintForgotPwdTv = (TextView) mPhoneView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_hint_tv"));
		mHintForgotPwdTv.setText(ResourceUtil.getString(this, "lgsdk_forgot_password_or_account_tip"));
		mPhoneEt.setInputType(InputType.TYPE_CLASS_PHONE);
		mPhoneEt.setHint(ResourceUtil.getString(this, "lgsdk_please_input_bound_phone"));
		
		mVCodeEt.setInputType(InputType.TYPE_CLASS_PHONE);
		mVCodeEt.setHint(ResourceUtil.getString(this, "lgsdk_please_input_received_vcode"));
		
		Button phoneBtn = (Button) mPhoneView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_btn"));
		Button vcodeBtn = (Button) mVCodeView.findViewById(ResourceUtil.getId(this, "lgsdk_retrive_account_item_btn"));
		
		phoneBtn.setText(ResourceUtil.getString(this, "lgsdk_retrive_account"));
		vcodeBtn.setText(ResourceUtil.getString(this, "lgsdk_confirm_pwd_change"));
		
		phoneBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phone = mPhoneEt.getText().toString();
				if(DataValidUtil.isValidMobilePhoneNo(phone)){
					submitPhone(phone);
				} else {
					Toast.makeText(LGRetriveAccountActivity.this, 
							ResourceUtil.getString(LGRetriveAccountActivity.this, "lgsdk_bind_phone_error_num_toast"), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		vcodeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String vcode = mVCodeEt.getText().toString();
				if(TextUtils.isEmpty(vcode)){
					Toast.makeText(LGRetriveAccountActivity.this,
							ResourceUtil.getString(LGRetriveAccountActivity.this, "lgsdk_verify_binding_no_code_toast"), Toast.LENGTH_SHORT).show();
				} else {
					submitVCode(mPhoneEt.getText().toString(), vcode);
				}
			}
		});
		
		mPhoneView.setVisibility(View.VISIBLE);
		mVCodeView.setVisibility(View.GONE);
	}

	
	private void showProgressDialog(){
		if(mProgressDialog == null){
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage(getString(ResourceUtil.getString(this, "lgsdk_submitting_data_please_wait")));
			mProgressDialog.setCancelable(false);
			mProgressDialog.setCanceledOnTouchOutside(false);
		}
		mProgressDialog.show();
	}
	
	private void submitPhone(String phone){
		showProgressDialog();
		RetriveAccountSMSNetEngine engine = new RetriveAccountSMSNetEngine(phone);
		mNetTask = new NetTask(this, engine, TAG_PHONE);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	private void submitVCode(String phone ,String vcode){
		showProgressDialog();
		RetriveAccountNetEngine engine = new RetriveAccountNetEngine(phone, vcode);
		mNetTask = new NetTask(this, engine, TAG_VCODE);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	
	private void back() {
		if(mVCodeView.getVisibility() == View.VISIBLE){
			mVCodeView.setVisibility(View.GONE);
			mPhoneView.setVisibility(View.VISIBLE);
		} else {
			setResult(RESULT_CANCELED);
			finish();
		}
	}
	
	@Override
	public void onClick(View v) {
		back();
	}

	private void closeProgressDialog(){
		if(mProgressDialog != null && mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}
	
	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		closeProgressDialog();
		BaseResultData resultData = engine.getResultData();
		if(resultData.getErrorCode() != 0){
			String errorTip = resultData.getErrorTip();
			if(TextUtils.isEmpty(errorTip)){
				Toast.makeText(this, ResourceUtil.getString(this, "lgsdk_net_error_please_retry"), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, errorTip, Toast.LENGTH_SHORT).show();
			}
		} else {
			if(tag == TAG_PHONE){
				String phone = mPhoneEt.getText().toString();
				mPhoneView.setVisibility(View.GONE);
				mVCodeView.setVisibility(View.VISIBLE);
				mHintTv.setText(getString(ResourceUtil.getString(this, "lgsdk_retrive_account_hint"), phone));
				
			} else if(tag == TAG_VCODE){
				RetriveAccountResultData data = (RetriveAccountResultData) resultData;
				Intent intent = new Intent();
				intent.putExtra("username", data.getUserName());
				intent.putExtra("password", data.getPassword());
				setResult(RESULT_OK, intent);
				finish();
			}
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		Toast.makeText(this, ResourceUtil.getString(this, "lgsdk_net_error_please_retry"), Toast.LENGTH_SHORT).show();
		closeProgressDialog();
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			back();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
