package com.lemonslice.navibrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonslice.navibrain.R;

/**
 * Created by alexander on 11/11/2013.
 */
public class SecondActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("You typed: "+getIntent().getStringExtra("userText"));

    }
}