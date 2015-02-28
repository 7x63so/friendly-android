package friendly.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A friendly wrapper of android.support.v4.app.Fragment.
 * <p/>
 * Makes setting up layout and registering callbacks easier.
 * All the usual lifecycle methods are still accessible.
 */
public abstract class FriendlyFragment extends Fragment {

    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = layout(inflater);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        registerCallbacks();
        bindViews();
        initialize();
    }

    @Override
    public void onDestroyView() {
        layout = null;
        unregisterCallbacks();
        super.onDestroyView();
    }

    /**
     * Returns the inflated layout to be set in onCreateView().
     */
    protected abstract View layout(LayoutInflater inflater);

    /**
     * Just a friendly suggestion.
     */
    protected void registerCallbacks() {
        // available for hire
    }

    /**
     * Just a friendly suggestion.
     */
    protected void bindViews() {
        // available for hire
    }

    /**
     * Just a friendly suggestion.
     */
    protected void initialize() {
        // available for hire
    }

    /**
     * Just a friendly suggestion.
     */
    protected void unregisterCallbacks() {
        // available for hire
    }

    /**
     * Optimistic wrapper for view.findViewById().
     */
    @SuppressWarnings("unchecked")
    protected final <T extends View> T bindView(int id) {
        return (T) layout.findViewById(id);
    }

    /**
     * Returns an instance of the FragmentActivity that has been cast to the given class type.
     */
    @Nullable
    protected final <T> T registerCallback(Class<T> clazz) {
        if (!isAdded()) {
            throw new IllegalStateException("You can't add a callback before the Fragment has attached to the Activity.");
        }

        final FragmentActivity activity = getActivity();
        if (activity != null) {
            if (clazz.isInstance(activity)) {
                return clazz.cast(activity);
            } else {
                throw new IllegalStateException("The Activity must implement " + clazz.getSimpleName() + ".");
            }
        }
        return null;
    }
}
