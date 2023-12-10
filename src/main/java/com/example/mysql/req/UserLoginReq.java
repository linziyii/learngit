package com.example.mysql.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginReq {
    @NotNull(message="用户名不能为空")
    private String loginName;
    @NotNull(message = "密码不能为空")
    @Length(min=6,max =32,message = "密码格式错误")
    private String password;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}