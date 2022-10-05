package com.lucianaugusto.http.standards;

public enum HttpContentTypeEnum {

    TEXT_HTML("text/html"),
    IMAGE_PNG("image/png"),
    APPLICATION_OCTET_STREAM("application/octet-stream");

    private final String type;

    HttpContentTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static HttpContentTypeEnum getContentTypeFromExtension(String extension) {
        switch (extension) {
            case "html":
                return TEXT_HTML;

            case "png":
                return IMAGE_PNG;

            default:
                return APPLICATION_OCTET_STREAM;
        }
    }
}
