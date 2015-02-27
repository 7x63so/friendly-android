package friendly.async;

/**
 * A simpler Action with only the doAction() method available.
 */
public abstract class SimpleAction extends Action {

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