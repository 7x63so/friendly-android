package xyz.gwh.android.lib.friendly.async;

import android.os.AsyncTask;

/**
 * A friendlier wrapper for android.os.AsyncTask.
 * Each Task method is bound to its equivalent in AsyncTask's execute().
 */
public class Task {

    /**
     * Represents the action performed in the task.
     */
    public static abstract class Action {

        public abstract void during();

        public void before() {
            // available for hire
        }

        public void after() {
            // available for hire
        }

        public void cancelled() {
            // available for hire
        }
    }

    /**
     * A simpler Action with only the during() method available.
     */
    public abstract static class SimpleAction extends Action {

        @Override
        public final void before() {
            // restricted
        }

        @Override
        public final void after() {
            // restricted
        }

        @Override
        public final void cancelled() {
            // restricted
        }
    }

    private Task() {
        // restrict instantiation
    }

    /**
     * Executes an AsyncTask and calls back to the Action
     * methods at each step of the execution.
     */
    public static void run(final Action action) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                action.before();
            }

            @Override
            protected Void doInBackground(Void... params) {
                action.during();
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

    /**
     * Executes an AsyncTask and calls the SimpleAction method in doInBackground().
     */
    public static void run(final SimpleAction task) {
        new android.os.AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                task.during();
                return null;
            }
        }.execute();
    }
}
