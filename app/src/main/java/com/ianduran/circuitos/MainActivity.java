package com.ianduran.circuitos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetChecker.isInternetAvailable(MainActivity.this)){
                    Intent i = new Intent(MainActivity.this, LightsActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, R.string.not_connected ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
