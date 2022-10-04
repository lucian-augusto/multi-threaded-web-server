package com.lucianaugusto.http.standards;

public enum HttpContentTypeEnum {

    TEXT_HTML("text/html");

    private final String type;

    HttpContentTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
