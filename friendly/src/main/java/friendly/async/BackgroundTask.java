package friendly.async;

/**
 * Performs an Action on a background thread.
 */
public abstract class BackgroundTask {

    public void run(final Action action) {
        runInBackground(action, 0);
    }

    public void run(final Action action, int delay) {
        runInBackground(action, delay);
    }

    public void run(final Runnable runnable) {
        runInBackground(new Action() {
            @Override
            public void doAction() {
                runnable.run();
            }
        }, 0);
    }

    public void run(final Runnable runnable, int delay) {
        runInBackground(new Action() {
            @Override
            public void doAction() {
                runnable.run();
            }
        }, delay);
    }

    /**
     * Performs the Action after a delay.
     */
    protected abstract void runInBackground(final Action action, int delay);
}