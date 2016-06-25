package com.lbc.rx.demo;

import java.util.Map;

import com.bx.pay.ApkUpdate;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends Activity {
	private Activity otherActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		boolean b = false;
		if (savedInstanceState != null) {
			b = savedInstanceState.getBoolean("KEY_START_FROM_OTHER_ACTIVITY", false);
			if (b) {
				update(this.otherActivity);
			}
		}
		if (!b) {
			super.onCreate(savedInstanceState);
			update(this);
		}
	
	}
	private void update(final Context context){
	 	   ApkUpdate apkUpdate = new ApkUpdate(context,new ApkUpdate.ApkUpdateCallback(){
	 		  @Override
				public void launch(Map resultinfo) {
					
	                 //��Ҫ��ת��
					 Intent intent = new Intent(context,WpayDemo.class );
				     startActivity(intent);
				     LauncherActivity.this.finish();
				}
	        	
	   });

	}
	public void setActivity(Activity paramActivity) {
		System.out.println("LauncherActivity>>>>>>>otherActivity");
		this.otherActivity = paramActivity;
	}
}
