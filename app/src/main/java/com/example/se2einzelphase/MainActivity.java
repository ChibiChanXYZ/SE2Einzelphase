package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
                TextView response = findViewById(R.id.MessageServer);

                findViewById(R.id.MatrNr);



                ThreadForButton p = new ThreadForButton(response);
                p.start();

                try {
                    p.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });



    }
}


 class ThreadForButton extends Thread {
    TextView response;

    ThreadForButton(TextView response){
        this.response= response;
    }

    public void run(){
        try {

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            response.setText("Hier steht später die Antwort vom Server");



            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}