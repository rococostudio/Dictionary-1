package com.school.dictionary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.school.dictionary.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView main_more;
    private DrawerLayout main_draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        startActivity(new Intent(this, ContentActivity.class));
    }

    public void init() {
        main_more = findViewById(R.id.main_more);
        main_draw = findViewById(R.id.main_draw);

        main_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_more:
                main_draw.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (main_draw.isDrawerOpen(GravityCompat.START)) {
                main_draw.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
