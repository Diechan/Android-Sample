package com.oukasoft.ServiceBindSample;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Binder;
import android.os.IBinder;
import android.app.Service;

public class MainActivity extends Activity {

	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 

	// ���C�A�E�g
	LinearLayout ll;

    Button btnBind;
    Button btnUnBind;
    
    Button btnFunc;
    // Service�̕ۑ�
    TestBindService mService;
    boolean         connectionStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");
        ll = new LinearLayout(this);
        ll.setGravity( Gravity.CENTER_VERTICAL);
        ll.setOrientation( LinearLayout.VERTICAL );
        
        btnBind   = new Button(this);
        btnUnBind = new Button(this);
        
        btnFunc   = new Button(this);
        
        btnBind.setOnClickListener( new ServiceOnClickListener() );
        btnUnBind.setOnClickListener( new ServiceOnClickListener() );
        btnFunc.setOnClickListener( new ServiceOnClickListener() );
        
        btnBind.setText("Service Onbind");
        btnUnBind.setText("Service Unbind");
        btnFunc.setText("Service �K���Ȋ֐�");
        
        ll.addView( btnBind ,  WC );
        ll.addView( btnUnBind, WC );
        ll.addView( btnFunc , WC );
        setContentView(ll);

	}

	// �R�l�N�V�����쐬
	private ServiceConnection connection = new ServiceConnection() {	
		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// �T�[�r�X�ڑ����ɌĂ΂��
			Log.i("ServiceConnection", "onServiceConnected");
			// �o�C���_�[��ۑ�
			mService = ((TestBindService.BindServiceBinder)service).getService();
		}
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// �T�[�r�X�ؒf���ɌĂ΂��
			Log.i("ServiceConnection", "onServiceDisconnected");
			mService = null;
		}
	};

    class ServiceOnClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			if( view == btnBind ){
				// �o�C���h�J�n
				bindService( new Intent( MainActivity.this, TestBindService.class ) ,
						     connection,
						     Context.BIND_AUTO_CREATE 
						     );
				connectionStatus = true;
			}else if( view == btnUnBind ){
				if( connectionStatus ){
					// �o�C���h����Ă���ꍇ�A�o�C���h������
					unbindService( connection );
					connectionStatus = false;
				}
			}else if( view == btnFunc ){
				if( connectionStatus ){
					// �K���Ȋ֐����Ăяo��
					mService.TestFunction();
				}
			}
		}

    }

}
