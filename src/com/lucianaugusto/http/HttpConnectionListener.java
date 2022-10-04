package com.lucianaugusto.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.lucianaugusto.base.server.ConnectionListener;
import com.lucianaugusto.base.server.request.RequestHandler;
import com.lucianaugusto.base.utils.IOUtils;
import com.lucianaugusto.config.WebServerConstants;
import com.lucianaugusto.http.request.HttpRequestHandler;

public class HttpConnectionListener implements ConnectionListener {

    @Override
    public void listen() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(WebServerConstants.PORT);
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket clientConnection = serverSocket.accept();
                System.out.println("Opening Connection:");
                System.out.println("New client connected" + clientConnection.getInetAddress().getHostAddress());
                RequestHandler requestHandler = new HttpRequestHandler(clientConnection);
                requestHandler.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(serverSocket);
        }
    }
}
