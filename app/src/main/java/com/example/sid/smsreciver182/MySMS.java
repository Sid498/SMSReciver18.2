package com.example.sid.smsreciver182;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by SID on 9/8/2017.
 */

public class MySMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsManager smsManager = SmsManager.getDefault();

        Bundle bundle = intent.getExtras();

        try{
            if (bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i=0;i<pdusObj.length;i++){
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdusObj[i]);

                    String mPhoneNumber = smsMessage.getDisplayOriginatingAddress();
                    //sender number
                    String mSenderNumber = mPhoneNumber;
                    //body of message
                    String mMessage = smsMessage.getDisplayMessageBody();
                    //showing whole message
                    Toast.makeText(context,"Sender Number : "+mSenderNumber + "\n Message : "+mMessage,Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
