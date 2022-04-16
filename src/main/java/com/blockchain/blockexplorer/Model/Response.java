package com.blockchain.blockexplorer.Model;

public class Response {
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response() {
        super();
    }

    private boolean status;
    private String message;
    private Object data;
}
