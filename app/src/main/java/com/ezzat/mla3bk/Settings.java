package com.ezzat.mla3bk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    private User user;
    private Button name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        user = getCurrentUserFromDB();
        intializeViews();
        onClickActions();
    }

    private void onClickActions() {
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void intializeViews() {
        name = (Button) findViewById(R.id.nameBT);
    }
}
