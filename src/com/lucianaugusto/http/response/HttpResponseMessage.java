package com.lucianaugusto.http.response;

import static com.lucianaugusto.http.standards.HttpStatusCodeEnum.NOT_FOUND;
import static com.lucianaugusto.http.standards.HttpStatusCodeEnum.OK;

import java.io.File;

import com.lucianaugusto.base.utils.FileUtils;
import com.lucianaugusto.base.utils.IOUtils;
import com.lucianaugusto.config.WebServerConstants;
import com.lucianaugusto.http.standards.HttpContentTypeEnum;
import com.lucianaugusto.http.standards.HttpStatusCodeEnum;

public class HttpResponseMessage {

    private String statusLine;
    private String contentTypeLine;
    private File body;
    private static final String CRLF = "\r\n";
    private static final String CONTENT_TYPE_STRING = "Content-Type: ";

    public HttpResponseMessage(File file) {
        this.statusLine = generateStatusLine(IOUtils.fileExists(file) ? OK : NOT_FOUND);
        this.contentTypeLine = generateContentTypeLine(file);
        this.body = IOUtils.fileExists(file) ? file : new File(WebServerConstants.FILE_NOT_FOUND_PATH);
    }

    public String getStatusLine() {
        return statusLine;
    }

    public String getContentTypeLine() {
        return contentTypeLine;
    }

    public File getBody() {
        return body;
    }

    private String generateStatusLine(HttpStatusCodeEnum status) {
        return "HTTP/" + WebServerConstants.HTTP_VERSION + " " + status.getStatusCode() +  " Document Follows" + CRLF;
    }

    private String generateContentTypeLine(File file) {
        String extension = FileUtils.getFileExtension(file).get();
        return CONTENT_TYPE_STRING + HttpContentTypeEnum.getContentTypeFromExtension(extension).getType() + CRLF;
    }
}
