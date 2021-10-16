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
