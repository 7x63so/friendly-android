package friendly.async;

/**
 * Represents an action to be performed.
 */
public abstract class Action {

    public abstract void perform();

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