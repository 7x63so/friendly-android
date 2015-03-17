package friendly.async;

import android.os.AsyncTask;
import android.os.Handler;

/**
 * A friendlier wrapper for android.os.AsyncTask.
 * Calls Action methods at each step of the task's execution.
 */
public final class SimpleAsyncTask extends BackgroundTask {
    @Override
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