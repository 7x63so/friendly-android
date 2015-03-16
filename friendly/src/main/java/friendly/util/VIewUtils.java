package friendly.util;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Utility class for View methods.
 */
public final class ViewUtils {

    private ViewUtils() {
        // restrict instantiation
    }

    /**
     * Sets the views to VISIBLE.
     */
    public static void makeVisible(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Sets the Views to INVISIBLE.
     */
    public static void makeInvisible(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Sets the Views to GONE.
     */
    public static void makeGone(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }
}
