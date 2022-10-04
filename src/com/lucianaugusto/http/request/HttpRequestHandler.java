package com.lucianaugusto.http.request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import com.lucianaugusto.base.server.request.RequestHandler;
import com.lucianaugusto.base.utils.IOUtils;
import com.lucianaugusto.config.WebServerConstants;
import com.lucianaugusto.http.response.HttpResponseMessage;

public class HttpRequestHandler implements RequestHandler {

    private static final String CRLF = "\r\n";
    Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        InputStreamReader input = null;
        BufferedReader inputReader = null;
        try {
            input = new InputStreamReader(socket.getInputStream());
            inputReader = new BufferedReader(input);

            File file = new File(extractFileName(inputReader));
            extractAndPrintHeaderLines(inputReader);

            HttpResponseMessage responseMessage = generateHttpResponseMessage(file);

            sendResponse(responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing connection");
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(inputReader);
            IOUtils.closeQuietly(socket);
        }
    }

    private void sendResponse(HttpResponseMessage responseMessage) {
        DataOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeBytes(responseMessage.getStatusLine());
            outputStream.writeBytes(responseMessage.getContentTypeLine());
            outputStream.writeBytes(CRLF);

            fileInputStream = new FileInputStream(responseMessage.getBody());
            byte[] buffer = new byte[1024];
            int bytes = 0;
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    private HttpResponseMessage generateHttpResponseMessage(File fileName) {
        HttpResponseMessage responseMessage = null;

        if (IOUtils.fileExists(fileName)) {
            responseMessage = new HttpResponseMessage(fileName);
        } else {
            responseMessage = new HttpResponseMessage(new File(WebServerConstants.FILE_NOT_FOUND_PATH));
        }
        return responseMessage;
    }

    private String extractFileName(BufferedReader inputReader) throws IOException {
        String requestLine = inputReader.readLine();
        System.out.println(requestLine);
        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();
        String fileName = tokens.nextToken();
        if (fileName.equals("/")) {
            fileName += WebServerConstants.INDEX_FILE;
        }
        fileName = WebServerConstants.BASE_RESOURCE_PATH + fileName;
        return fileName;
    }

    private void extractAndPrintHeaderLines(BufferedReader inputReader) throws IOException {
        String line = null;
        while ((line = inputReader.readLine()).length() > 0) {
            System.out.println(line);
        }
    }
}
