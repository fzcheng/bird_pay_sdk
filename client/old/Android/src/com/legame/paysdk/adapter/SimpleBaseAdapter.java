package com.legame.paysdk.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月17日
 * @version 1.0
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
	protected List<T> mList;
	private Context mContext;
	
	public SimpleBaseAdapter(Context context) {
		mContext = context;
	}
	
	public SimpleBaseAdapter(Context context, List<T> list) {
		this(context);
		mList = list;
	}

	protected Context getContext() {
		return mContext;
	}
	
	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public List<T> getList() {
		return mList;
	}

	public void setList(List<T> mList) {
		this.mList = mList;
	}
	
	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		return getView(inflater, position, convertView, parent);
	}

	public abstract View getView(LayoutInflater inflater, int position, View convertView, ViewGroup parent);
}
