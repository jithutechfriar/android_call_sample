package com.example.android_call_sample1;
import android.os.Build;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.util.Log;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class ConnectionClass extends Connection {
    @Override
    public void onShowIncomingCallUi() {
        super.onShowIncomingCallUi();
        Log.i("myCallLog","onShowIncomingCallUi"+" called");

    }

    public ConnectionClass() {
        super();
        Log.i("myCallLog","ConnectionClassConstructorCalled");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setConnectionProperties(Connection.PROPERTY_SELF_MANAGED);
        }
    }

    @Override
    public void onCallAudioStateChanged(CallAudioState state) {
        super.onCallAudioStateChanged(state);
        Log.i("myCallLog","onCallAudioStateChanged"+" called");

    }

    @Override
    public void onHold() {
        super.onHold();
    }

    @Override
    public void onUnhold() {
        super.onUnhold();
    }

    @Override
    public void onAnswer() {
        super.onAnswer();
        Log.i("myCallLog","onAnswer"+" called");
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
        Log.i("myCallLog","onDisconnect"+" called");
    }


}
