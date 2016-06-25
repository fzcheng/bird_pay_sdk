package com.legame.paysdk.listener;

import java.util.List;

public interface LeGameCallbackListener<T> {
	public void onGameCallback(int status, T data);
	public void onGameCallback2(int status, List<String> data);
}
