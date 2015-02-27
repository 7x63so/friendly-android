package friendly.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import friendly.async.Action;
import friendly.async.BackgroundTask;
import friendly.util.Logger;


public class FriendlySampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayHello();
        numberCrunch();
    }

    private void sayHello() {
        Logger.i("Hello world!");
    }

    private void numberCrunch() {
        new BackgroundTask().run(new Action() {
            @Override
            public void before() {
                Logger.i("Preparing to crunch some numbers...");
            }

            @Override
            public void doAction() {
                Logger.i("Crunching... ");

                int result = 1;
                for (int i = 0; i < 10; i++) {
                    result += result;
                }

                Logger.i("Result: " + result);
            }

            @Override
            public void after() {
                Logger.i("Finished!");
            }
        });
    }
}