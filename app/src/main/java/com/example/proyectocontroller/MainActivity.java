package com.example.proyectocontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

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

    Button aBtn, bBtn, startBtn, leftBtn, rightBtn, debugButton;

    Connection TCP;

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
        debugButton = findViewById(R.id.button);

        TCP = new Connection();

        TCP.initClient();

        //initClient();

        debugButton.setOnClickListener(
                (view) ->
                {
                    TCP.sendMessage("a");
                }
        );

        aBtn.setOnClickListener(
                (view)->
                {
                    TCP.sendMessage("1");
                    Log.d("tag", "1");
                }
        );

        bBtn.setOnClickListener(
                (view)->
                {
                    TCP.sendMessage("2");
                    Log.d("tag", "2");
                }
        );

        leftBtn.setOnClickListener(
                (view)->
                {
                    TCP.sendMessage("3");
                    Log.d("tag", "3");
                }
        );

        rightBtn.setOnClickListener(
                (view)->
                {
                    TCP.sendMessage("4");
                    Log.d("tag", "4");
                }
        );

        startBtn.setOnClickListener(
                (view)->
                {
                    TCP.sendMessage("5");
                    Log.d("tag", "5");
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