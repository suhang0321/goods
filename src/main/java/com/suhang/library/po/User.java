package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-10-30 12:00
 */
public class User {
    private String uid;
    private String loginname;
    private String loginpass;
    private String email;
    private int status;
    private String activationCode;

    public String getUid() {
        return uid;
    }

    public String getLoginname() {
        return loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public String getEmail() {
        return email;
    }

    public int getStatus() {
        return status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "User{" +
            "uid='" + uid + '\'' +
            ", loginname='" + loginname + '\'' +
            ", loginpass='" + loginpass + '\'' +
            ", email='" + email + '\'' +
            ", status=" + status +
            ", activationCode='" + activationCode + '\'' +
            '}';
    }
}
