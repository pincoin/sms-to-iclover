package kr.co.pincoin.broadcastsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class Receiver extends BroadcastReceiver {
    private static final String TAG = "Receiver";

    private static final String KOOKMIN = "16449999";
    private static final String NONGHUP = "15882100";
    private static final String SHINHAN = "15778000";
    private static final String WOORI = "15885000";
    private static final String IBK = "15662566";


    @Override
    public void onReceive(Context context, Intent intent) {
        PaymentNotifyAsyncTask paymentNotifyAsyncTask = new PaymentNotifyAsyncTask();

        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] messages = (Object[]) bundle.get("pdus");

                if (messages != null) {
                    SmsMessage[] smsMessage = new SmsMessage[messages.length];

                    for (int i = 0; i < messages.length; i++) {
                        smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
                    }

                    String phone = smsMessage[0].getOriginatingAddress();
                    String message = smsMessage[0].getMessageBody();

                    if (phone != null && message != null) {
                        paymentNotifyAsyncTask.execute("1Ò", phone, message);
                    }
                }
            }
        }
    }
}
