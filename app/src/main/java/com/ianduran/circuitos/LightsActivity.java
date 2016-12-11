package com.ianduran.circuitos;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class LightsActivity extends Activity {

    private final String turnOn = "http://192.168.0.103/?alllightson";
    private final String turn1On = "http://192.168.0.103/?lighton1";
    private final String turn2On = "http://192.168.0.103/?lighton2";
    private final String turn3On = "http://192.168.0.103/?lighton3";
    private final String turn4On = "http://192.168.0.103/?lighton4";

    private final String turnOff = "http://192.168.0.103/?alllightsoff";
    private final String turn1Off = "http://192.168.0.103/?lightoff1";
    private final String turn2Off = "http://192.168.0.103/?lightoff2";
    private final String turn3Off = "http://192.168.0.103/?lightoff3";
    private final String turn4Off = "http://192.168.0.103/?lightoff4";

    private Button turnAllOn;
    private Button turnAllOff;
    private Switch switchOne;
    private Switch switchTwo;
    private Switch switchThree;
    private Switch switchFour;

    @Override
    public void onCreate(Bundle savedInstanceState){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_light_activity);


        turnAllOn = (Button)findViewById(R.id.btn_turn_all_on);
        turnAllOff = (Button)findViewById(R.id.btn_turn_all_off);
        switchOne = (Switch)findViewById(R.id.swtich_one);
        switchTwo = (Switch)findViewById(R.id.switch_two);
        switchThree = (Switch)findViewById(R.id.switch_three);
        switchFour = (Switch)findViewById(R.id.switch_four);


        turnAllOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetChecker.isInternetAvailable(LightsActivity.this)){
                    new TurnLed().execute(turnOn);
                    switchOne.setChecked(true);
                    switchTwo.setChecked(true);
                    switchThree.setChecked(true);
                    switchFour.setChecked(true);
                }else{
                    Toast.makeText(LightsActivity.this, R.string.not_connected ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        turnAllOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetChecker.isInternetAvailable(LightsActivity.this)){
                    new TurnLed().execute(turnOff);
                    switchOne.setChecked(false);
                    switchTwo.setChecked(false);
                    switchThree.setChecked(false);
                    switchFour.setChecked(false);
                }else{
                    Toast.makeText(LightsActivity.this, R.string.not_connected ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    new TurnLed().execute(turn1On);
                }else{
                    new TurnLed().execute(turn1Off);
                }
            }
        });

        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    new TurnLed().execute(turn2On);
                else
                    new TurnLed().execute(turn2Off);
            }
        });

        switchThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    new TurnLed().execute(turn3On);
                else
                    new TurnLed().execute(turn3Off);
            }
        });

        switchFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    new TurnLed().execute(turn4On);
                else
                    new TurnLed().execute(turn4Off);
            }
        });


    }

    private class TurnLed extends AsyncTask<String, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected Void doInBackground(String... args){
            try {
                try{
                    HttpClient client = new DefaultHttpClient();
                    client.execute(new HttpGet(args[0]));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
