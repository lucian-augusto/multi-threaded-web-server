package com.lucianaugusto;

import com.lucianaugusto.base.server.ConnectionListener;
import com.lucianaugusto.http.HttpConnectionListener;

public class MultiThreadedWebServer {

    public static void main(String[] args) {
        ConnectionListener connectionListener = new HttpConnectionListener();
        connectionListener.listen();
    }
}
