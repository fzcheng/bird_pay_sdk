package com.legame.leyo.smspay;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.legame.leyo.smspay.MdoPayBackCheck;
import com.legame.leyo.smspay.SmsPay;
import com.legame.leyo.smspay.extend.PayErrorCode;
import com.legame.leyo.smspay.thread.QueueSendSms;
import com.legame.leyo.smspay.thread.QueueSendSms.QueueSendSmsListener;
import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.LeGamePayMent.MbsPayCallback;
import com.legame.paysdk.activity.LYGamePayment;
import com.legame.paysdk.db.MdoSmsBlockDb;
import com.legame.paysdk.dialog.SecondConfirmDialog;
import com.legame.paysdk.dialog.SecondConfirmSendSmsDialog;
import com.legame.paysdk.dialog.TextDialog;
import com.legame.paysdk.models.Block;
import com.legame.paysdk.models.Command;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.models.SdkpayData;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.VirtualCodeNetEngine;
import com.legame.paysdk.network.resultdata.VirtualCodeResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.receiver.InterceptSmsReciever;
import com.legame.paysdk.utils.SmsBasePay;
import com.legame.paysdk.utils.SmsBasePay.MdoPayCallBack;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.7
 * @类说明 使用移动用户的虚拟码扣费
 *
 */
public class VirtualCodePay implements NetTaskListener{
	
	public static final String PAY_PROCESS_SEND_FIRST = "cmcczwjf01";
	public static final String PAY_PROCESS_SEND_SECOND = "cmcczwjf02";
	
	private String orderNo;
	private Context context;
	private Command command;
	private Block block;
	private ProgressDialog mProgressDialog;
	
	private MbsPayCallback listener;

	public VirtualCodePay(Context context,String orderNo,Command command,Block block){
		this.context = context;
		this.orderNo = orderNo;
		this.command = command;
		this.block = block;
	}
	
	public void pay(){
		
		final SmsBasePay smsBasePay = new SmsBasePay();
		
		if(command != null){
			if(!TextUtils.isEmpty(command.getZwjf())){
				showMsgToUser();
				return;
			}
			if(block != null)
				saveMdoSmsBlock(block);
			smsBasePay.sendSms(context, command.getmNumber(), command.getmContent(), new MdoPayCallBack() {
				
				@Override
				public void onMdoPayResult(int status) {
					// TODO Auto-generated method stub
					final int success = 0;
					if(success == status){
						
					}else{
						
					}
					smsBasePay.unRegisterReceiver(LYGamePayment.gContext);
				}
			});
		}
		
		
	}
	
	/**
	 * 弹出第一个框，提示点击按钮发送短信
	 */
	private void showMsgToUser(){
		final TextDialog dialog = new TextDialog(context);
		StringBuilder content = new StringBuilder();
		content.append("只需")
			   .append(command.getZwjf())
			   .append("积分，就可以进行兑换");
		dialog.setContent(content.toString());
		dialog.showButton1("去获取", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				payOnConfirm();
			}
		});
		dialog.showButton2("去兑换", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				payOnVirtualCode();
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	private void payOnConfirm(){
		
		final SecondConfirmSendSmsDialog sendSmsDialog = new SecondConfirmSendSmsDialog(context);
		sendSmsDialog.setButtonCancle(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.onLeYoPayResult(ErrorCode.ERROR_CANCEL_DIALOG, "取消第一个");
				sendSmsDialog.dismiss();
			}
		});
		
		sendSmsDialog.setButtonSend(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.setClickable(false);
				
				Uri smsToUri = Uri.parse("smsto:"+command.getmNumber());  
				  
				Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);  
				  
				intent.putExtra("sms_body", command.getmContent());  
				  
				context.startActivity(intent);
				sendSmsDialog.dismiss();
			}
		});
		StringBuilder explain = new StringBuilder();
		//点击确认按钮将发送短信内容XXX到号码XXXX,您将收到一条确认短信
		explain.append("通过手机发送短信内容")
			   .append(command.getmContent())
			   .append("到号码")
			   .append(command.getmNumber())
			   .append(",同时回复确认短信,即可获得兑换券。点击确认按钮我们会帮你跳转到发送短信界面");
		sendSmsDialog.setExplain(explain.toString());
		sendSmsDialog.setCancelable(false);
		sendSmsDialog.setCanceledOnTouchOutside(false);
		sendSmsDialog.show();
	}
	
	/**
	 * 弹出第二个框，根据指示发送第二条短信填入虚拟码
	 */
	private SecondConfirmSendSmsDialog virtualCodeDialog;
	private void payOnVirtualCode(){
		virtualCodeDialog = new SecondConfirmSendSmsDialog(context);
		virtualCodeDialog.setButtonSend(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String virtualCode = virtualCodeDialog.getVirtualCode();
				if(TextUtils.isEmpty(virtualCode)){
					Toast.makeText(context, "兑换券码不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(!SecondConfirmSendSmsDialog.isPattern(virtualCode)){
					Toast.makeText(context, "兑换券码必须为10位数字", Toast.LENGTH_SHORT).show();
					return;
				}
				
				String sid = NetTools.getSid(context);
				
				VirtualCodeNetEngine engine = new VirtualCodeNetEngine(sid,virtualCode,orderNo);
				NetTask task = new NetTask(context, engine, 0);
				task.setListener(VirtualCodePay.this);
				new Thread(task).start();
			}
		});
		
		virtualCodeDialog.setButtonCancle(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.onLeYoPayResult(ErrorCode.ERROR_CANCEL_DIALOG, "用户取消");
				virtualCodeDialog.dismiss();
			}
		});
		
		virtualCodeDialog.setEditText();
		virtualCodeDialog.setCancelable(false);
		virtualCodeDialog.setCanceledOnTouchOutside(false);
		virtualCodeDialog.show();
	}
	
	
	
	public void setListener(MbsPayCallback listener) {
		this.listener = listener;
	}
	
	private void saveMdoSmsBlock(Block block) {
		MdoSmsBlockDb db = MdoSmsBlockDb.instance(context);
		db.save(block);
	}

	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		VirtualCodeResultData resultData = (VirtualCodeResultData) engine.getResultData();
		if(resultData.getVirtualCodePayResult()){
			virtualCodeDialog.dismiss();
			listener.onLeYoPayResult(ErrorCode.ERROR_SUCCESS, "支付成功");
		}else{
			Toast.makeText(context, "无效的兑换券码", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void onTaskRunError(int tag) {
		listener.onLeYoPayResult(ErrorCode.ERROR_FAIL, "访问服务器失败-vc");
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		listener.onLeYoPayResult(ErrorCode.ERROR_USER_CANCELED, "用户取消");
	}
	
	private void waitingDlg(String message) {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(message);
		mProgressDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		 mProgressDialog.show();
	}
	
	private void cancelWaitingDlg() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}

}
