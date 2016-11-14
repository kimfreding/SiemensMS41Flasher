package no.chipster.siemensms41flasher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
//import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
//import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    usb USB = new usb();
    Button Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log = (Button) findViewById(R.id.Log);
       // Log.setOnClickListener(this);


    }

    public void OnClickLog(View v)
    {

       // USB.openDevice();
        Context context = getApplicationContext();
        USB.context = (Context)this;
        CharSequence text = "Connecting!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        USB.openDevice();
    }


}
