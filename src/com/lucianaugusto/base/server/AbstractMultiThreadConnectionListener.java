package com.lucianaugusto.base.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lucianaugusto.config.WebServerConstants;

public abstract class AbstractMultiThreadConnectionListener implements ConnectionListener {

    private final ExecutorService requestPool;

    protected AbstractMultiThreadConnectionListener() {
        this.requestPool = Executors.newFixedThreadPool(WebServerConstants.MAX_THREADS);
    }

    protected ExecutorService getRequestPool() {
        return requestPool;
    }
}
