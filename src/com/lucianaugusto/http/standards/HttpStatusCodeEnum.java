package com.lucianaugusto.http.standards;

public enum HttpStatusCodeEnum {

    OK("200"),
    NOT_FOUND("404");

    private final String statusCode;

    HttpStatusCodeEnum(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
