package com.oukasoft.ServiceBindSample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TestBindService extends Service {
	
	static final String TAG= "BindService";
	
	public class BindServiceBinder extends Binder{
		// �T�[�r�X�̎擾
		TestBindService getService(){
			return TestBindService.this;
		}
	}
	
	// Binder�̍쐬
	private final IBinder mBinder = new BindServiceBinder();
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
	}
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(TAG, "onBind");
		return mBinder;
	}
	@Override
	public boolean onUnbind(Intent intent){
		Log.i(TAG, "onUnbind");
		return true;
	}
	@Override
	public void onDestroy(){
		Log.i(TAG, "onDestroy");
	}

	public void TestFunction(){
		Log.i(TAG, "�K���ɍ�����֐����Ă΂�܂����i�O�ցO�j");
	}
}
