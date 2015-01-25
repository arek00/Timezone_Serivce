package com.arek00.timezone;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.arek00.timezone.services.TimeService;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Messenger serviceMessenger;
    private Messenger handlerMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.i("MyActivity","OnCreate");

        startService(new Intent(MyActivity.this, TimeService.class));
        bindService(new Intent(this,TimeService.class),serviceConnection, Context.BIND_AUTO_CREATE);

    }


    public void onSearchClick(View view)
    {
        sendMessageToService();
        Log.i("MyActivity","Button Clicked");
    }

    private void sendMessageToService()
    {
        String searchPhrase;
        EditText editText = (EditText) findViewById(R.id.searchCityText);
        searchPhrase = editText.getText().toString();

        if(serviceMessenger != null)
        {
            try
            {
                Message message = Message.obtain(null, TimeService.SEARCH_CITY);
                Bundle bundle = new Bundle();
                bundle.putString("name",searchPhrase);
                message.setData(bundle);
                message.replyTo = handlerMessenger;
                serviceMessenger.send(message);

                Log.i("MyActivity","Sent message");

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }



    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message message)
        {
            switch(message.what)
            {
                case TimeService.SEND_DATE :
                    setDateToTextView(message);
                    break;
                default : Log.i("MyActivity","Unknown message");
            }
        }

    }

    private void setDateToTextView(Message message)
    {
        String cityName, date;
        cityName = message.getData().getString("cityName");
        date = message.getData().getString("date");

        TextView textView = (TextView) findViewById(R.id.cityName);
        textView.setText(cityName);

        textView = (TextView) findViewById(R.id.currentHour);
        textView.setText(date);

    }


    //realizing connection with service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            serviceMessenger = new Messenger(service);

            Log.i("ServiceConnection","Creating service connection");

            try
            {
                Message message = Message.obtain(null, TimeService.REGISTER_CLIENT);
                message.replyTo = handlerMessenger;
                serviceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceMessenger = null;
        }
    };


    @Override
    public void onStop()
    {
        super.onStop();
        unbindService(serviceConnection);
        stopService(new Intent(MyActivity.this, TimeService.class));
    }

}
