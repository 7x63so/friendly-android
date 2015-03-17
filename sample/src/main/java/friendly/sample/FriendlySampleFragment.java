package friendly.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import friendly.fragment.FriendlyFragment;
import friendly.util.ViewUtils;

/**
 * A sample fragment to demo features in FriendlyFragment.
 */
public class FriendlySampleFragment extends FriendlyFragment {

    public interface ArrivingCallback {
        /**
         * Prints a message to the console.
         */
        public void sayHello();
    }

    public interface LeavingCallback {
        /**
         * Prints a message to the console.
         */
        public void sayGoodbye();
    }

    private ArrivingCallback arrivingCallback;
    private LeavingCallback leavingCallback;

    private TextView sampleText;
    private Button sampleButton;

    public static FriendlySampleFragment newInstance() {
        return new FriendlySampleFragment();
    }

    @Override
    protected void registerCallbacks() {
        arrivingCallback = registerCallback(ArrivingCallback.class);
        leavingCallback = registerCallback(LeavingCallback.class);
    }

    @Override
    protected void bindViews() {
        sampleText = bindView(R.id.sample_text);
        sampleButton = bindViewWithAction(R.id.sample_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.makeInvisible(sampleText);
            }
        });
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