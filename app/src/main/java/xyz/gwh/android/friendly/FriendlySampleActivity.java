package xyz.gwh.android.friendly;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import xyz.gwh.android.lib.friendly.async.Action;
import xyz.gwh.android.lib.friendly.async.BackgroundTask;
import xyz.gwh.android.lib.friendly.async.SimpleAction;
import xyz.gwh.android.lib.friendly.util.Logger;


public class FriendlySampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendly_sample);

        sayHello();
    }

    private void sayHello() {
        new BackgroundTask().run(new Action() {
            @Override
            public void doAction() {
                Logger.i(message());
            }
        });

        new BackgroundTask().run(new SimpleAction() {
            @Override
            public void doAction() {
                Logger.i(message());
            }
        }, 3000);
    }

    private String message() {
        return new SimpleDateFormat("hh:mm:ss").format(new Date())  + " Hello world!";
    }
}