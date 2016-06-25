package com.legame.paysdk.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.ResourceUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PaytypeListAdapter extends SimpleBaseAdapter<String>{
	
	private PayTypeListener payTypeListener;

	public PaytypeListAdapter(Context context,List<String> payTypeList) {
		super(context,payTypeList);
	}

	@Override
	public View getView(LayoutInflater inflater, final int position,
			View convertView, ViewGroup parent) {
		ViewHolder viewHolder ;
		if(convertView == null){
			convertView = inflater.inflate(ResourceUtil.getLayout(getContext(), "mbssdk_paytype_item"), null);
			viewHolder = new ViewHolder();
			viewHolder.iv_logo = (ImageView) convertView.findViewById(ResourceUtil.getId(getContext(), "mbs_paytype_item_iv_logo"));
			viewHolder.tv_paytype = (TextView) convertView.findViewById(ResourceUtil.getId(getContext(), "mbs_paytype_item_tv_explain"));
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.iv_logo.setImageResource(getImgResId(position));
		viewHolder.tv_paytype.setText(getPaytypeText(position));
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				payTypeListener.onSlect(position, mList.get(position));
			}
		});
		
		return convertView;
	}
	
	public void setPaytypeListener(PayTypeListener payTypeListener){
		this.payTypeListener = payTypeListener;
	}
	
	private static class ViewHolder{
		ImageView iv_logo;
		TextView tv_paytype;
	}
	
	private int getImgResId(int position){
		return ResourceUtil.getDrawable(getContext(), mList.get(position));
	}
	
	private String getPaytypeText(int position){
		return payTypeTextMap.get(mList.get(position));
	}
	
	public interface PayTypeListener{
		public void onSlect(int position,String payType);
		public void onCancel();
	}
	
	public static Map<String,String> payTypeTextMap;
	static{
		payTypeTextMap = new HashMap<String,String>();
		payTypeTextMap.put(Constants.ALIPAY_TYPE, "支付宝支付");
		payTypeTextMap.put(Constants.WXPAY_TYPE, "微信支付");
		payTypeTextMap.put(Constants.ZWJFPAY_TYPE, "移动积分支付");
		payTypeTextMap.put(Constants.SMSPAY_TYPE, "话费支付");
	}

	

}
