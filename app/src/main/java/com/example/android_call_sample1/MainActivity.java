package com.example.android_call_sample1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnStartCall,btnEndCall;

    private TelecomManager telecomManager;
    private final String PHONE_ACCOUNT_LABEL ="phone_account_label";
    private PhoneAccountHandle accountHandle;
    private PhoneAccount phoneAccount;
    private ConnectionClass systemCallConnection;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartCall =(Button)findViewById(R.id.btnStartCall);
        btnEndCall =(Button)findViewById(R.id.btnEndCall);

        createAccount();

        btnStartCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCall();
            }
        });

        btnEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                endCall();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void createAccount(){
        telecomManager = (TelecomManager) getSystemService(Context.TELECOM_SERVICE);

        if (telecomManager ==null){
            throw new RuntimeException("cannot obtain telecom system service");
        }

        ComponentName connectionServiceName = new ComponentName(getApplicationContext(),ConnectionService.class);
        accountHandle = new PhoneAccountHandle(connectionServiceName,PHONE_ACCOUNT_LABEL);

        try {
            phoneAccount = telecomManager.getPhoneAccount(accountHandle);
            if (phoneAccount==null){
                PhoneAccount.Builder builder = PhoneAccount.builder(accountHandle,PHONE_ACCOUNT_LABEL);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder.setCapabilities(PhoneAccount.CAPABILITY_SELF_MANAGED);
                }
                phoneAccount= builder.build();
                telecomManager.registerPhoneAccount(phoneAccount);
            }

            accountHandle = phoneAccount.getAccountHandle();

            if (telecomManager.getPhoneAccount(accountHandle)==null){
                throw new RuntimeException("cannot create account");
            }

        }catch (SecurityException e){
            throw new RuntimeException("cannot create account",e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void createCall(){
        try {

            Bundle extras = new Bundle();
            extras.putParcelable(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE,accountHandle);
            Uri uri = Uri.fromParts(PhoneAccount.SCHEME_SIP,"test_call",null);
            telecomManager.placeCall(uri,extras);

        }catch (SecurityException e){
            throw new RuntimeException("cannot place call ",e);
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    void endCall(){
//        try {
//
//            systemCallConnection.setDisconnected(new DisconnectCause(DisconnectCause.REMOTE));
//            systemCallConnection.destroy();
//            systemCallConnection=null;
//
//        }catch (SecurityException e){
//            throw  new SecurityException("cannot end the call", e);
//        }
//    }
}