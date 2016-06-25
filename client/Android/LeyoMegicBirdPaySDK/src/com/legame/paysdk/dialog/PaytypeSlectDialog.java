package com.legame.paysdk.dialog;

import java.util.List;

import com.legame.paysdk.adapter.PaytypeListAdapter;
import com.legame.paysdk.adapter.PaytypeListAdapter.PayTypeListener;
import com.legame.paysdk.utils.ResourceUtil;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.12
 * @类说明 选择支付类型
 * 
 */
public class PaytypeSlectDialog extends Dialog {

	private Context mContext;

	private RelativeLayout rl_firstPaytype;

	private ImageView iv_cancel;

	private ImageView iv_firstPaytypeLogo;

	private TextView tv_firstPaytypeExplain;

	private ListView lv_paytype;

	private TextView tv_payMoney;

	private PayTypeListener listener;

	public PaytypeSlectDialog(Context context) {
		super(context, ResourceUtil.getStyle(context, "mbssdk_paytype_style"));
		mContext = context;
		init();
	}

	private void init() {
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(ResourceUtil.getLayout(mContext, "mbssdk_paytype_list"));

		rl_firstPaytype = (RelativeLayout) findViewById(ResourceUtil.getId(
				mContext, "mbssdk_firstpaytype_rl"));

		iv_cancel = (ImageView) findViewById(ResourceUtil.getId(mContext,
				"mbs_iv_cancel"));
		iv_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.onCancel();
			}
		});

		iv_firstPaytypeLogo = (ImageView) findViewById(ResourceUtil.getId(
				mContext, "mbssdk_firstpaytype_item_iv_logo"));

		tv_firstPaytypeExplain = (TextView) findViewById(ResourceUtil.getId(
				mContext, "mbssdk_firstpaytype_item_tv_explain"));

		lv_paytype = (ListView) findViewById(ResourceUtil.getId(mContext,
				"mbssdk_lv_paytype"));

		tv_payMoney = (TextView) findViewById(ResourceUtil.getId(mContext,
				"mbssdk_tv_order_show_amount"));

		setParamLayout();

	}

	public void setPaytypeListener(PayTypeListener listener) {
		this.listener = listener;
	}

	public void setFirstPaytype(final String payType) {
		iv_firstPaytypeLogo.setImageResource(ResourceUtil.getDrawable(mContext,
				payType));
		tv_firstPaytypeExplain.setText(PaytypeListAdapter.payTypeTextMap
				.get(payType));
		rl_firstPaytype.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onSlect(0, payType);
			}
		});

	}

	public void setAdapter(List<String> payTypeList) {
		PaytypeListAdapter adapter = new PaytypeListAdapter(mContext,
				payTypeList);
		adapter.setPaytypeListener(listener);
		lv_paytype.setAdapter(adapter);
	}

	public void setPayMoney(String money) {
		tv_payMoney.setText(money);
		tv_payMoney.setTextColor(Color.RED);
	}

	private void setParamLayout() {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		Rect rect = new Rect();
		View view = getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		getWindow().getAttributes().height = dm.heightPixels - rect.top;
		getWindow().getAttributes().width = dm.widthPixels;
	}

}
