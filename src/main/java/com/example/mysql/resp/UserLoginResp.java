package com.example.mysql.resp;

import java.io.Serializable;

public class UserLoginResp implements Serializable{
    private Long id;

    private String loginName;
    private String name;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String toke) {
        this.token = toke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}