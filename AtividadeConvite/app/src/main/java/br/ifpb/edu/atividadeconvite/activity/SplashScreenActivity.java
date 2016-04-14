package br.ifpb.edu.atividadeconvite.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ifpb.edu.atividadeconvite.R;

public class SplashScreenActivity extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(SplashScreenActivity.this, 3000);
    }

    @Override
    public void run() {

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();

    }
}
