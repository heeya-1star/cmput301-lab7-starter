package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView textView;
    private Button back_b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        textView = findViewById(R.id.clicked_city);
        back_b = (Button) findViewById(R.id.back_button);
        String cityRecived = getIntent().getStringExtra("change_activity");
        if (cityRecived != null) {
            textView.setText(cityRecived);
        }

        back_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

    }

}
