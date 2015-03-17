package friendly.async;

import android.os.AsyncTask;
import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * A friendlier wrapper for android.os.AsyncTask.
 * Calls Action methods at each step of the task's execution.
 */
public final class SimpleAsyncTask extends BackgroundTask implements BackgroundTask.Cancelable {

    private AsyncTask<Void, Integer, Void> task;
    private Executor executor;

    public SimpleAsyncTask() {
        this(false);
    }

    public SimpleAsyncTask(boolean runInParallel) {
        executor = runInParallel ? AsyncTask.THREAD_POOL_EXECUTOR : AsyncTask.SERIAL_EXECUTOR;
    }

    @Override
    protected void runInBackground(final Action action, int delay) {
        task = new AsyncTask<Void, Integer, Void>() {
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
        };

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                task.executeOnExecutor(executor);
            }
        };

        handler.postDelayed(runnable, delay);
    }

    @Override
    public void cancel() {
        if (task != null) {
            task.cancel(true);
        }
    }
}