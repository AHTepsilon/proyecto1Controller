package com.example.proyectocontroller;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connection
{

    BufferedWriter writer;
    BufferedReader reader;

    private Socket socket;

    public Connection()
    {


    }

    public void initClient()
    {

    }

    public void sendMessage(String msg)
    {
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
    }

}
