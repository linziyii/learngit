package com.example.mysql.resp;

public class CommonResp<T> {
    private boolean success=true;
    private T content;
    private String messaghe;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessaghe() {
        return messaghe;
    }

    public void setMessaghe(String messaghe) {
        this.messaghe = messaghe;
    }
}
