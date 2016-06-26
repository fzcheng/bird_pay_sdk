package com.legame.paysdk.receiver;

import java.util.List;

import com.legame.paysdk.db.MdoSmsBlockDb;
import com.legame.paysdk.models.Block;
import com.legame.paysdk.utils.LogUtil;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-6-10
 * @version 1.0
 */
public class SMSObserver extends ContentObserver {
	private static final String TAG = SMSObserver.class.getSimpleName();
	private Context mContext;

	public SMSObserver(Handler handler,Context context) {
		super(handler);
		this.mContext = context;
	}
	
	private Boolean blockByNumber(String phoneNumber, String blockNumber)
	{
		if(phoneNumber.startsWith(blockNumber)){
			return true;
		}
		return false;
	}
	
	private Boolean blockByContent(String smsContent, String blockContent)
	{
		String[] splitKeyWord = blockContent.split("&");
		for(int i = 0; i < splitKeyWord.length; i++){
			if(!smsContent.contains(splitKeyWord[i])){
				return false;
			}
		}
		return true;
	}
	
	public void onChange(boolean bSelfChange) {
		super.onChange(bSelfChange);

		String strUriInbox = "content://sms/inbox";
		Uri uriSms = Uri.parse(strUriInbox);
		List<Block> blockList = MdoSmsBlockDb.instance(mContext).query();
		if(blockList == null || blockList.size() == 0){
			return;
		}
		
		Cursor c = mContext.getContentResolver().query(uriSms,null, "read=" + 0,null, null);
		// delete all sms here when every new sms occures.
		while (c.moveToNext()) {
			String smsNumber = c.getString(c.getColumnIndex("address")).trim();//获取发信人  
            String smsContent =  c.getString(c.getColumnIndex("body")).trim();// 获取信息内容  
            String pid = c.getString(c.getColumnIndex("thread_id"));
            if(smsNumber.startsWith("+86")){
            	smsNumber = smsNumber.replace("+86", "");
            }
            
            LogUtil.d(TAG, smsNumber + " " + smsContent);

        	for(int i=0;i<blockList.size();i++){

				String blockContent = blockList.get(i).getmKeyWord();
				String blockNumber = blockList.get(i).getmNumber();
				LogUtil.d(TAG, "block number="+blockNumber + " keyWord=" + blockContent);
				if(TextUtils.isEmpty(blockNumber) && !TextUtils.isEmpty(blockContent)){//屏蔽号码为空，屏蔽内容不空
					//只按关键字屏蔽
					if(blockByContent(smsContent, blockContent)){
						MdoSmsBlockDb.instance(mContext).delete(blockList.get(i).getId());
						deleteDataFromDB(pid);
						return;
					}
					
				}else if(!TextUtils.isEmpty(blockNumber) && TextUtils.isEmpty(blockContent)){//屏蔽号码不为空，屏蔽内容空
					//只按号码屏蔽
					if(blockByNumber(smsNumber, blockNumber)){
						MdoSmsBlockDb.instance(mContext).delete(blockList.get(i).getId());
						deleteDataFromDB(pid);
						return;
					}
				}else if(!TextUtils.isEmpty(blockNumber)&& !TextUtils.isEmpty(blockContent)){
					if(blockByNumber(smsNumber, blockNumber) && blockByContent(smsContent, blockContent)){
						MdoSmsBlockDb.instance(mContext).delete(blockList.get(i).getId());
						deleteDataFromDB(pid);
						return;
					}
				}
			}
		}
	}
	
	public void deleteDataFromDB(String pid){
		String uri = "content://sms/conversations/" + pid;
		mContext.getContentResolver().delete(Uri.parse(uri),
				null, null);
	}
	

}
