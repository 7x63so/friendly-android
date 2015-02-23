package xyz.gwh.android.friendly;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import xyz.gwh.android.lib.friendly.util.Logger;


public class FriendlySampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendly_sample);

        sayHello();
    }

    private void sayHello() {
        Logger.d("Hello world");
    }
}