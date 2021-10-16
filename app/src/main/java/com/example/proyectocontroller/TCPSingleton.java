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

public class TCPSingleton extends Thread
{
    private Socket socket;
    BufferedWriter writer;
    BufferedReader reader;

    private static TCPSingleton uniqueInstance;

    public static TCPSingleton getInstance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new TCPSingleton();
            uniqueInstance.start();
        }

        return uniqueInstance;
    }

    @Override
    public void run()
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

    private TCPSingleton()
    {

    }
}
