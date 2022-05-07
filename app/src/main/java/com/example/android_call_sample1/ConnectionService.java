package com.example.android_call_sample1;

import android.content.Context;
import android.os.Build;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class ConnectionService extends android.telecom.ConnectionService {

    @Override
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i("myCallLog","onCreateIncomingConnection"+" called");
        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request);
    }

    @Override
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i("myCallLog","onCreateIncomingConnectionFailed"+" called");
        super.onCreateIncomingConnectionFailed(connectionManagerPhoneAccount, request);
    }

    @Override
    public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i("myCallLog","onCreateOutgoingConnection"+" called");
        return super.onCreateOutgoingConnection(connectionManagerPhoneAccount, request);
    }

    @Override
    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle connectionManagerPhoneAccount, ConnectionRequest request) {
        Log.i("myCallLog","onCreateOutgoingConnectionFailed"+" called");
        super.onCreateOutgoingConnectionFailed(connectionManagerPhoneAccount, request);
    }


}
