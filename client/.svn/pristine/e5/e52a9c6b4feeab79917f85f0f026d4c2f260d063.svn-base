package com.legame.paysdk.dialog;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.legame.paysdk.adapter.AccountListAdapter;
import com.legame.paysdk.adapter.AccountListAdapter.AccountListAdapterCallback;
import com.legame.paysdk.db.AnonymousLoginDb;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-18
 * @version 1.0
 */
public class AccountListDialog extends Dialog implements OnItemClickListener,AccountListAdapterCallback{
	
	private ListView mListView;
	private AccountListAdapter mAdapter;
	private ArrayList<UserInfo> mUserList;
	private AccountListDialogListener mListener;
	
	public interface AccountListDialogListener{
		void onSelectAccount(UserInfo info);
		void onDeleteAccount(UserInfo deleteInfo);
		void onAllDeleted();
	}

	public AccountListDialog(Context context,ArrayList<UserInfo> userList) {
		super(context, ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		mUserList = userList;
		init();
	}
	
	private void init(){
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_account_dialog_layout"));
		mListView = (ListView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_account_listview"));
		mAdapter = new AccountListAdapter(getContext(), mUserList);
		mAdapter.setCallback(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}
	
	public void setAccountListDialogListener(AccountListDialogListener l){
		mListener = l;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		UserInfo info = mUserList.get(arg2);
		if(mListener != null){
			mListener.onSelectAccount(info);
		}
		dismiss();
	}

	@Override
	public void onDelete(int position) {
		UserInfo info = mUserList.get(position);
		UserInfoDb.getInstance().deleteUser(info.getUserName());
		String un = AnonymousLoginDb.getInstance().getUsername(getContext().getPackageName());
		if(un != null){
			if(info.getUserName().equals(un)){
				AnonymousLoginDb.getInstance().deleteUsername(info.getUserName());
			}
		}
		mUserList.remove(position);
		if(mUserList.size() != 0){
			mAdapter.setList(mUserList);
			mAdapter.notifyDataSetChanged();
			if(mListener != null){
				mListener.onDeleteAccount(info);
			}
		} else {
			if(mListener != null){
				mListener.onAllDeleted();
			}
			dismiss();
		}
		
	}

}
