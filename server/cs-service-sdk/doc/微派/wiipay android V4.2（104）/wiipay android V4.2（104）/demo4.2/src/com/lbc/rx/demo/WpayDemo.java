package com.lbc.rx.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.bx.pay.BXPay;
import com.bx.pay.BXPay.PayCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WpayDemo extends Activity {
	Button pay_but_0001;
	Button pay_but_0002;
	Button pay_but_0003;
	Button pay_but_0004;
	TextView textTest1;
	TextView textTest;
	private BXPay bxpay;
	private Activity otherActivity;
	private static String payCode = "0001";
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		boolean b = false;
		String result = "";
		try {
			JSONObject jObject = new JSONObject(result == null
					|| "".equals(result) ? "{}" : result.trim());
			System.out.println("jObject=" + jObject.toString());
		} catch (JSONException e) {
			System.out.println("json�쳣�ˡ���������");
			e.printStackTrace();
		}
		context = this;
		// ApplicationInfo appInfo =
		// context.getPackageManager().getApplicationInfo(
		// context.getPackageName(), PackageManager.PERMISSION_DENIED);
		// String permissions = appInfo.permission;

		if (savedInstanceState != null) {
			b = savedInstanceState.getBoolean("KEY_START_FROM_OTHER_ACTIVITY",
					false);
			if (b) {
				if (bxpay == null)
					bxpay = new BXPay(this.otherActivity);
				Map<String, String> devPrivate = new HashMap<String, String>();
				devPrivate.put("������Ҫ����KEYֵ", "������Ҫ����VALUEֵ");
				bxpay.setDevPrivate(devPrivate);
				LinearLayout mianLinearLayout = new LinearLayout(
						this.otherActivity);
				pay_but_0001 = new Button(this.otherActivity);
				pay_but_0001.setText("paycode=0001֧   ��");
				pay_but_0001.setId(1);
				pay_but_0001.setOnClickListener(clickListener);
				mianLinearLayout.addView(pay_but_0001);
				pay_but_0002 = new Button(this.otherActivity);
				pay_but_0002.setText("paycode=0002֧   ��");
				pay_but_0002.setId(2);
				pay_but_0002.setOnClickListener(clickListener);
				mianLinearLayout.addView(pay_but_0002);
				this.otherActivity.setContentView(mianLinearLayout);
			}
		}
		if (!b) {
			super.onCreate(savedInstanceState);
		
			LinearLayout mianLinearLayout = new LinearLayout(this);
			LinearLayout.LayoutParams mainLinearLayoutParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT);
			mianLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			mianLinearLayout.setOrientation(LinearLayout.VERTICAL);
			pay_but_0001 = new Button(this);
			pay_but_0001.setText("paycode=0001�������");
			pay_but_0001.setId(1);
			pay_but_0001.setOnClickListener(clickListener);
			mianLinearLayout.addView(pay_but_0001);
			pay_but_0002 = new Button(this);
			pay_but_0002.setText("paycode=0002--���ش����");
			pay_but_0002.setId(2);
			pay_but_0002.setOnClickListener(clickListener);
			mianLinearLayout.addView(pay_but_0002);

			pay_but_0003 = new Button(this);
			pay_but_0003.setText("paycode=0003--�������ͨ");
			pay_but_0003.setId(3);
			pay_but_0003.setOnClickListener(clickListener);
			mianLinearLayout.addView(pay_but_0003);

			pay_but_0004 = new Button(this);
			pay_but_0004.setText("paycode=0004--����һ����");
			pay_but_0004.setId(4);
			pay_but_0004.setOnClickListener(clickListener);
			mianLinearLayout.addView(pay_but_0004);
			this.setContentView(R.layout.frameloayout);
			this.setContentView(mianLinearLayout);
		}

	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case 1:
				pay("0001");
				break;
			case 2:
				pay("0002");

				break;
			case 3:
				pay("0003");
//				System.out.println("0003>>>>"+hasSpecialCharacter("4264 6549 5466 2545 6494 9256 7559 56; +-,;+--- ,#*-"));
				break;
			case 4:
				pay("0004");
//				System.out.println("0004>>>>"+hasSpecialCharacter("4264 6549 5466 2545 6494 9256 7559 56 ,"));

				break;
			default:
				break;
			}
		}
	};

	public static boolean hasSpecialCharacter(String str) {
		String regEx = "[~!@#$%^&*<>+-.;,]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	private void pay(String payCode) {
		if (bxpay == null)
			bxpay = new BXPay(this);
		Map<String, String> devPrivate = new HashMap<String, String>();
		devPrivate.put("������Ҫ����KEYֵ", "������Ҫ����VALUEֵ");
		bxpay.setDevPrivate(devPrivate);
		bxpay.pay(payCode, new BXPay.PayCallback() {

			@Override
			public void pay(Map<String, String> resultInfo) {
				new AlertDialog.Builder(WpayDemo.this).setTitle("֧��������أ�")
						.setMessage("���ؽ����" + resultInfo.toString())
						.setPositiveButton("ȷ��", null).show();

			}
		});
	}

	public void setActivity(Activity paramActivity) {
		System.out.println("LauncherActivity>>>>>>>otherActivity");
		this.otherActivity = paramActivity;
	}
	// ********************************
	// public void setViewAlpha(ImageView mBtn, boolean isChecked) {
	// // �����BUTTON������ʱ���ڱ����������ɫ
	// int m_disableColor = Color.argb(150, 255, 75, 75);
	// Drawable bkDrawable = mBtn.getBackground().mutate();
	//
	// if (null != bkDrawable) {
	// if (!isChecked) {
	// bkDrawable.clearColorFilter();
	// } else {
	// bkDrawable.setColorFilter(m_disableColor,
	// PorterDuff.Mode.DST_OUT);
	// }
	// }
	// mBtn.setBackgroundDrawable(bkDrawable);
	// }

}
