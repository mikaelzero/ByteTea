package net.mikaelzero.bytetea.lib.clickdebounce;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MikaelZero
 * @CreateDate: 2020/7/3 2:38 PM
 * @Description: DebouncedWarp
 */
public class DebouncedWarp {

    public static long FROZEN_WINDOW_MILLIS = 1000L;

    private static final String TAG = DebouncedWarp.class.getSimpleName();

    private static final Map<View, FrozenView> viewWeakHashMap = new WeakHashMap<>();

    public static boolean shouldDoClick(View targetView) {

        FrozenView frozenView = viewWeakHashMap.get(targetView);
        final long now = now();

        if (frozenView == null) {
            frozenView = new FrozenView(targetView);
            frozenView.setFrozenWindow(now + FROZEN_WINDOW_MILLIS);
            viewWeakHashMap.put(targetView, frozenView);
            return true;
        }

        if (now >= frozenView.getFrozenWindowTime()) {
            frozenView.setFrozenWindow(now + FROZEN_WINDOW_MILLIS);
            return true;
        }

        return false;
    }

    private static long now() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    private static class FrozenView extends WeakReference<View> {
        private long frozenWindowTime;

        FrozenView(View referent) {
            super(referent);
        }

        long getFrozenWindowTime() {
            return frozenWindowTime;
        }

        void setFrozenWindow(long expirationTime) {
            this.frozenWindowTime = expirationTime;
        }
    }
}