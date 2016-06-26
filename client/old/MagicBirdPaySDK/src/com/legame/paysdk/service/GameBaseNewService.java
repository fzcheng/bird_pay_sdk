package com.legame.paysdk.service;

import java.util.LinkedList;
import java.util.Queue;

import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.GameBaseNewEngine;
import com.legame.paysdk.network.resultdata.GameBaseNewResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * 说明:游戏基地二次发送
 * @author KaiGuang
 * @date 2015.04.03
 * 
 */
public class GameBaseNewService extends Service{
	
	private static GameBaseNewService instance;
	
	private Queue<Object> msgQueue;
	
	private GameBaseNewService(){}
	
	public static GameBaseNewService getInstance(){
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		msgQueue = new LinkedList<Object>();
		instance = this;
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public synchronized void queueMsg(Object obj){
		msgQueue.add(obj);
	}
	
	private synchronized void queueRemove(){
		msgQueue.poll();
	}
	
	private boolean queueIsNull(){
		if(msgQueue.peek() == null){
			return true;
		}
		return false;
	}
	
	class ChargeThread extends Thread{
		@Override
		public void run() {
			while(!queueIsNull()){
				getGameBaseNewData();
				
			}
			
		}
	}
	
	private void getGameBaseNewData(){
		GameBaseNewEngine engine = new GameBaseNewEngine();
		engine.setData(getApplicationContext(), "", "", "");
		NetTask netTask = new NetTask(getBaseContext(), engine, 0);
		GameBaseSecondTaskListener listener = new GameBaseSecondTaskListener();
		netTask.setListener(listener);
		
	}
	
	
	private class GameBaseSecondTaskListener implements NetTaskListener{

		@Override
		public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
			GameBaseNewResultData resultData = (GameBaseNewResultData) engine.getResultData();
			if(resultData.getErrorCode() == 0){
				//获取成功
				queueRemove();
				
			}else{
				//获取失败，间隔一段时间重新发送请求
				sleep(30);
				
			}
			
		}

		@Override
		public void onTaskRunError(int tag) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTaskRunCanceled(int tag) {
			// TODO Auto-generated method stub
			
		}

	}
	
	private void sleep(int sleepTime){
		try {
			Thread.sleep(sleepTime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
