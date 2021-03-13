package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView response = findViewById(R.id.MessageServer);
                response.setText("Hier steht später die Antwort vom Server");
            }
        });
    }
}