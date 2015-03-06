package friendly.async;

import android.os.AsyncTask;
import android.os.Handler;

/**
 * A friendlier wrapper for android.os.AsyncTask.
 * Each Action method is bound to its equivalent in AsyncTask's execute().
 */
public final class BackgroundTask {

    /**
     * Runs the Action in a background thread.
     */
    public void run(final Action action) {
        runInBackground(action, 0);
    }

    /**
     * Runs the Action in a background thread after the given delay.
     */
    public void run(final Action action, int delay) {
        runInBackground(action, delay);
    }

    /**
     * Runs the Runnable in a background thread.
     */
    public void run(final Runnable runnable) {
        runInBackground(new Action() {
            @Override
            public void doAction() {
                runnable.run();
            }
        }, 0);
    }

    /**
     * Runs the Runnable in a background thread after the given delay.
     */
    public void run(final Runnable runnable, int delay) {
        runInBackground(new Action() {
            @Override
            public void doAction() {
                runnable.run();
            }
        }, delay);
    }

    /**
     * Executes an AsyncTask and calls back to the Action
     * methods at each step of the execution.
     */
    protected void runInBackground(final Action action, int delay) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new AsyncTask<Void, Integer, Void>() {
                    @Override
                    protected void onPreExecute() {
                        action.before();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        action.doAction();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        action.after();
                    }

                    @Override
                    protected void onCancelled() {
                        action.cancelled();
                    }
                }.execute();
            }
        };

        handler.postDelayed(runnable, delay);
    }
}