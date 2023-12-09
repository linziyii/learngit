package com.example.mysql.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserSaveReq {
    private Long id;
    @NotNull(message="用户名不能为空")

    private String loginName;
    @NotNull(message = "昵称不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    @Length(min=6,max =20,message = "密码长度为6-20位")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}