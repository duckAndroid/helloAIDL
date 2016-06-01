package com.pythoncat.helloaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.github.johnpersano.supertoasts.SuperToast;
import com.pythoncat.aidl_libiary.IHelloInterface;
import com.pythoncat.core.model.Student;

public class MainActivity extends AppCompatActivity {

    private IHelloInterface iService;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService = IHelloInterface.Stub.asInterface(service);
            try {
                final String hello = iService.hello();
                LogUtils.e("hello::::::::" + hello);
                final Student one = iService.getOne();
                LogUtils.e(one);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SuperToast.cancelAllSuperToasts();
                        SuperToast.create(getApplicationContext(), hello, SuperToast.Duration.MEDIUM).show();
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iService = null;
            LogUtils.e("iService::::::::" + iService);
        }
    };
    private boolean bindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        final Intent in = new Intent();
        in.setClassName(this, "com.pythoncat.aidl_libiary.HelloService");
        in.setPackage("com.pythoncat.aidl_libiary");
        in.setAction("com.pythoncat.aidl_libiary.HelloService");
        bindService = bindService(in, conn, Context.BIND_AUTO_CREATE);
        LogUtils.e("bindService=" + bindService);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (conn != null) {
            unbindService(conn);
        }
    }

    public void clickButton(View v) {
        LogUtils.e("bindService=" + bindService);
        LogUtils.e(iService);
        if (iService == null) {
            SuperToast.cancelAllSuperToasts();
            SuperToast.create(getApplicationContext(), iService + "", SuperToast.Duration.MEDIUM).show();
        } else {
            SuperToast.cancelAllSuperToasts();
            try {
                SuperToast.create(getApplicationContext(), iService.hello(), SuperToast.Duration.MEDIUM).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }
}
