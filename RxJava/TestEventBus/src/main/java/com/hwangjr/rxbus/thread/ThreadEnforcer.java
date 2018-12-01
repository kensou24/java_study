package com.hwangjr.rxbus.thread;

import com.hwangjr.rxbus.Bus;

/**
 * Enforces a thread confinement policy for methods on a particular event bus.
 */
public interface ThreadEnforcer {

    /**
     * Enforce a valid thread for the given {@code bus}. Implementations may throw any runtime exception.
     *
     * @param bus Event bus instance on which an action is being performed.
     */
    void enforce(Bus bus);


    /**
     * A {@link ThreadEnforcer} that does no verification.
     */
    ThreadEnforcer ANY = new ThreadEnforcer() {
        @Override
        public void enforce(Bus bus) {
            // Allow any thread.
        }
    };


}
