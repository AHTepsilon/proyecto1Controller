package com.example.proyectocontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

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

    BufferedWriter writer;
    BufferedReader reader;

    private Socket socket;

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

        initClient();

        aBtn.setOnClickListener(
                (view)->
                {
                    //sendInput("a");
                    Log.d("tag", "a");
                }
        );

        bBtn.setOnClickListener(
                (view)->
                {
                    //sendInput("b");
                    Log.d("tag", "b");
                }
        );

        leftBtn.setOnClickListener(
                (view)->
                {
                    //sendInput("l");
                    Log.d("tag", "l");
                }
        );

        rightBtn.setOnClickListener(
                (view)->
                {
                    //sendInput("r");
                    Log.d("tag", "r");
                }
        );

        startBtn.setOnClickListener(
                (view)->
                {
                    //sendInput("s");
                    Log.d("tag", "s");
                }
        );
    }

    public void initClient()
    {
        new Thread(
                () ->
                {
                    try {
                        socket = new Socket("192.168.1.9", 4000);

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

    public void sendInput(String msg)
    {
        new Thread(
                ()->
                {
                    try {
                        writer.write(msg);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();
    }
}