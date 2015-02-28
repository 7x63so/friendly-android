package friendly.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import friendly.fragment.FragmentCallback;
import friendly.fragment.FriendlyFragment;

/**
 * A sample fragment to demo features in FriendlyFragment.
 */
public class FriendlySampleFragment extends FriendlyFragment {

    public interface ArrivingCallback extends FragmentCallback {
        /**
         * Prints a message to the console.
         */
        public void sayHello();
    }

    public interface LeavingCallback extends FragmentCallback {
        /**
         * Prints a message to the console.
         */
        public void sayGoodbye();
    }

    private ArrivingCallback arrivingCallback;
    private LeavingCallback leavingCallback;
    private TextView sampleText;

    public static FriendlySampleFragment newInstance() {
        return new FriendlySampleFragment();
    }

    @Override
    protected void registerCallbacks() {
        arrivingCallback = bindCallback(ArrivingCallback.class);
        leavingCallback = bindCallback(LeavingCallback.class);
    }

    @Override
    protected void bindViews() {
        sampleText = bindView(R.id.sample_text);
    }

    @Override
    protected void initialize() {
        arrivingCallback.sayHello();
        leavingCallback.sayGoodbye();

        sampleText.setText("Lorem ipsum...");
    }

    @Override
    protected View layout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_sample, null);
    }

    @Override
    protected void unregisterCallbacks() {
        arrivingCallback = null;
        leavingCallback = null;
    }
}