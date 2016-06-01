package com.pythoncat.aidl_libiary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.pythoncat.core.model.Student;

/**
 * <action android:name="com.pythoncat.aidl_libiary.HelloService"/>
 * <hr/>
 * package="com.pythoncat.aidl_libiary"
 */
public class HelloService extends Service {
    public HelloService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IHelloInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String hello() throws RemoteException {
            return "Just Hello World";
        }

        @Override
        public Student getOne() throws RemoteException {
            return new Student();
        }
    }
}
