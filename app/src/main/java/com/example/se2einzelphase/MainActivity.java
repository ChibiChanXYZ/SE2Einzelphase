package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView responseServerVisual = findViewById(R.id.MessageServer);
                EditText matrNrVisual = findViewById(R.id.MatrNr);
                String matrNrString = matrNrVisual.getText().toString();

                ThreadForButton p = new ThreadForButton(matrNrString);
                p.start();

                try {
                    p.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                responseServerVisual.setText(p.getResponseServer());

            }
        });



    }
}


 class ThreadForButton extends Thread {
     String matrNrToServer;
     String responseServer;

     ThreadForButton(String matrNr){
         matrNrToServer = matrNr;
     }

    public void run(){
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);         //Erstellen einer Verknüpfung zum Server

            //Erstellen von Streams zum Versenden und Empfangen von Daten vom Server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(matrNrToServer + '\n');                          //versenden unserer Daten zum Server

            responseServer = inFromServer.readLine();                                //lesen der Daten vom Server

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getResponseServer(){
         return responseServer;
    }
}