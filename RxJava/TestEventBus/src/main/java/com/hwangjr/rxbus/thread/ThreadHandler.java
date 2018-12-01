package com.hwangjr.rxbus.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public interface ThreadHandler {
    Executor getExecutor();

    static ThreadHandler DEFAULT = new ThreadHandler() {
        private Executor executor;

        @Override
        public Executor getExecutor() {
            if(executor == null) {
                executor = Executors.newCachedThreadPool();
            }
            return executor;
        }

    };
}
