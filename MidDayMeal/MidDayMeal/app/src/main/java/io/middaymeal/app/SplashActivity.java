package io.middaymeal.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    private boolean loginCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loginCheck = Utility.getPreferences(this,Constants.keyLoginCheck,false);


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {



                    Intent intent = null;
                    if (loginCheck)
                        if (Utility.getPreferences(SplashActivity.this,Constants.userType).equalsIgnoreCase("2")){
                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        }else {
                            startActivity(new Intent(SplashActivity.this,FeedbackManagerMainActivity.class));
                        }
                    else {
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }




                    finish();

                }
            }
        };

        timerThread.start();

    }


}
