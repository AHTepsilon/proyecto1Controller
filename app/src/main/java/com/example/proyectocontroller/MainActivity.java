package com.example.proyectocontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.os.Vibrator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button aBtn, bBtn, startBtn, leftBtn, rightBtn;
    Vibrator v;

    //Connection TCP;

    TCPSingleton TCPconnect;
    /*BufferedWriter writer;
    BufferedReader reader;

    private Socket socket;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        aBtn = findViewById(R.id.aButton);
        bBtn = findViewById(R.id.bButton);
        startBtn = findViewById(R.id.startButton);
        leftBtn = findViewById(R.id.leftArrowButton);
        rightBtn = findViewById(R.id.rightArrowButton);

        TCPconnect = TCPSingleton.getInstance();
        TCPconnect.setObserver(this);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //initClient();

        aBtn.setOnClickListener(
                (view)->
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    TCPconnect.sendMessage("1");
                    Log.d("tag", "1");
                }
        );

        bBtn.setOnClickListener(
                (view)->
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    TCPconnect.sendMessage("2");
                    Log.d("tag", "2");
                }
        );

        leftBtn.setOnClickListener(
                (view)->
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    TCPconnect.sendMessage("3");
                    Log.d("tag", "3");
                }
        );

        rightBtn.setOnClickListener(
                (view)->
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    TCPconnect.sendMessage("4");
                    Log.d("tag", "4");
                }
        );

        startBtn.setOnClickListener(
                (view)->
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    TCPconnect.sendMessage("5");
                    Log.d("tag", "5");
                }
        );
    }

    public void whenTheMessageArrives(String message)
    {
        runOnUiThread(
                ()->
                {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
        );
    }

    /*public void initClient()
    {
        new Thread(
                () ->
                {
                    try {
                        System.out.println("Connecting to server...");
                        socket = new Socket("192.168.1.69", 4000);
                        System.out.println("Established connection to server");

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        reader = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osr = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osr);

                        while(true)
                        {
                            System.out.println("Awaiting input...");
                            String line = reader.readLine();
                            System.out.println("Input received..." + line);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void sendMessage(String msg) {
        new Thread(
                () ->
                {
                    try {
                        writer.write(msg + "\n");
                        writer.flush();
                        Log.d(">>>", "message sent: " + msg);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }).start();
    }*/
}