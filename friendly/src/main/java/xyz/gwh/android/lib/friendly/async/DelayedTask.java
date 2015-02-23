package xyz.gwh.android.lib.friendly.async;

import android.os.Handler;

/**
 * A wrapper for Async.Task that adds a delay.
 */
public class DelayedTask {

    public static void run(final Task.Action action, int millis) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Task.run(action);
            }
        };

        handler.postDelayed(runnable, millis);
        handler.removeCallbacks(runnable);
    }

    public static void run(final Task.SimpleAction action, int millis) {
        Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Task.run(action);
            }
        };

        handler.postDelayed(runnable, millis);
        handler.removeCallbacks(runnable);
    }
}
