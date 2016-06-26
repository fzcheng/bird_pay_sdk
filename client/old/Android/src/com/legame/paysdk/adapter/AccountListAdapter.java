package com.legame.paysdk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-18
 * @version 1.0
 */
public class AccountListAdapter extends SimpleBaseAdapter<UserInfo>{

//	private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
	
//	private String mHintLastLoginTime;
	private String mHintLastLoginGame;
	private String mHintNotHave;
	
	private AccountListAdapterCallback mCallback;
	
	public interface AccountListAdapterCallback{
		void onDelete(int position);
	}
	
	public AccountListAdapter(Context context, List<UserInfo> list) {
		super(context, list);
//		mHintLastLoginTime = context.getString(R.string.lgsdk_last_login_time_hint);
		mHintLastLoginGame = context.getString(ResourceUtil.getString(getContext(),  "lgsdk_last_login_game_hint"));
		mHintNotHave = context.getString(ResourceUtil.getString(getContext(),  "lgsdk_not_have"));
	}

	public void setCallback(AccountListAdapterCallback callback){
		mCallback = callback;
	}
	
	@Override
	public View getView(LayoutInflater inflater, int position,
			View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(ResourceUtil.getLayout(getContext(), "lgsdk_account_list_item_layout"), null);
			holder = new ViewHolder();
			holder.userNameTv = (TextView) convertView.findViewById(ResourceUtil.getId(getContext(), "lgsdk_account_list_item_username_tv"));
			holder.gameNameTv = (TextView) convertView.findViewById(ResourceUtil.getId(getContext(), "lgsdk_account_list_item_gameName_tv"));
//			holder.timeTv = (TextView) convertView.findViewById(R.id.lgsdk_account_list_item_time_tv);
			holder.deleteIv = (ImageView) convertView.findViewById(ResourceUtil.getId(getContext(), "lgsdk_account_list_item_delete_iv"));
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		UserInfo info = mList.get(position);
		
		holder.userNameTv.setText(info.getUserName());
		holder.gameNameTv.setText(mHintLastLoginGame+
				GlobalVal.getAppNameByPackage(getContext(), info.getGamePkgName()));
		if(info.getLastTime() == 0){
			holder.gameNameTv.setText(mHintLastLoginGame+mHintNotHave);
//			holder.timeTv.setText(mHintLastLoginTime +mHintNotHave);
		} else {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTimeInMillis(info.getLastTime());
//			holder.timeTv.setText(mHintLastLoginTime+
//					mFormat.format(calendar.getTime()));
		}
		
		holder.deleteIv.setOnClickListener(new BtnClickListener(position));
		
		return convertView;
	}

	private class BtnClickListener implements OnClickListener{

		private int position;
		
		public BtnClickListener(int position){
			this.position = position;
		}
		
		@Override
		public void onClick(View v) {
			if(mCallback != null){
				mCallback.onDelete(position);
			}
		}
		
	}
	
	private static class ViewHolder{
		TextView userNameTv;
		TextView gameNameTv;
//		TextView timeTv;
		ImageView deleteIv;
	}

}
