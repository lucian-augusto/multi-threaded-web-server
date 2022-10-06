package com.lucianaugusto.config;

public final class WebServerConstants {

    public static final int PORT = 1_234;
    public static final int MAX_THREADS = 50;
    public static final String HTTP_VERSION = "1.1";
    public static final String BASE_RESOURCE_PATH = "./resources";
    public static final String INDEX_FILE = "index.html";
    public static final String FILE_NOT_FOUND_PATH = BASE_RESOURCE_PATH + "/not_found.html";

    private WebServerConstants() {
    }
}
