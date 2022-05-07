package com.example.android_call_sample1;

import android.os.Build;
import android.telecom.Connection;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class CallConnection extends Connection {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public CallConnection() {
        setConnectionProperties(PROPERTY_SELF_MANAGED);
//        setAudioModeIsVoip(true);
    }
}
