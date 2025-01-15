package org.dongkoer.com.entity;

public class ResponseFromPython {

    private String data;
    private String message;
    private String status;

    public ResponseFromPython(String data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ResponseFromPython() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
