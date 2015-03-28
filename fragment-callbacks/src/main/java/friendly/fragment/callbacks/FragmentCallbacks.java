package friendly.fragment.callbacks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Finds @FragmentCallbacks and casts them to the fragment's activity.
 */
public class FragmentCallbacks {

    private static final String TAG = "FragmentCallbacks";

    /**
     * Should be called in Fragment.onAttach().
     */
    public static void register(FragmentActivity activity, Fragment fragment) {
        Class<?> fragmentClass = fragment.getClass();

        // traverse the class hierarchy
        while (fragmentClass != null) {
            for (Field field : fragmentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(FragmentCallback.class)) {
                    Class<?> callbackClass = field.getType();

                    // accept interfaces only
                    if (!callbackClass.isInterface()) {
                        throw new IllegalStateException(callbackClass.getSimpleName() + " is not an interface");
                    }

                    // instead of generic ClassCastException
                    if (!callbackClass.isInstance(activity)) {
                        throw new IllegalStateException("The " + activity.getClass().getSimpleName() + " must implement " + fragmentClass.getSimpleName() + ".");
                    }

                    // cast the activity as the callback
                    try {
                        field.setAccessible(true);
                        field.set(fragment, callbackClass.cast(activity));
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "Couldn't register " + field.getName() + " in " + fragmentClass.getSimpleName() + ".");
                    }
                }
            }

            fragmentClass = fragmentClass.getSuperclass();
        }
    }

    /**
     * Should be called in Fragment.onDetach().
     */
    public static void unregister(Fragment fragment) {
        Class<?> fragmentClass = fragment.getClass();

        // traverse the class hierarchy
        while (fragmentClass != null) {
            for (Field field : fragmentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(FragmentCallback.class)) {

                    // set the callback to null
                    try {
                        field.setAccessible(true);
                        field.set(fragment, null);
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "Couldn't unregister " + field.getName() + " in " + fragmentClass.getSimpleName() + ".");
                    }
                }
            }

            fragmentClass = fragmentClass.getSuperclass();
        }
    }
}