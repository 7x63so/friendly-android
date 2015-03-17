package friendly.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import friendly.async.Action;
import friendly.async.SimpleAsyncTask;
import friendly.util.Logger;


public class FriendlySampleActivity extends ActionBarActivity implements FriendlySampleFragment.ArrivingCallback, FriendlySampleFragment.LeavingCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doSomethingInBackground();
        showFragment(FriendlySampleFragment.newInstance(), false);
    }

    @Override
    public void sayHello() {
        Logger.i("Hi there");
    }

    @Override
    public void sayGoodbye() {
        Logger.i("See ya");
    }

    /**
     * Crunches some numbers on a background thread.
     */
    private void doSomethingInBackground() {
        new SimpleAsyncTask().run(new Action() {
            @Override
            public void before() {
                Logger.i("Preparing to crunch some numbers...");
            }

            @Override
            public void perform() {
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

    /**
     * Shows a... fragment.
     */
    private void showFragment(Fragment fragment, boolean addToBackStack) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}