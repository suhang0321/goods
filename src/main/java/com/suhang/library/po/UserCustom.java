package com.suhang.library.po;

import javax.management.remote.SubjectDelegationPermission;

/**
 * @author hang.su
 * @since 2017-11-01 12:27
 */
public class UserCustom extends User {

    private String reloginpass;
    private String verifyCode;
    private String newloginpass;

    public String getReloginpass() {
        return reloginpass;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public String getNewloginpass() {
        return newloginpass;
    }

    public void setReloginpass(String reloginpass) {
        this.reloginpass = reloginpass;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setNewloginpass(String newloginpass) {
        this.newloginpass = newloginpass;
    }

    @Override
    public String toString() {
        return "UserCustom{" + "reloginpass='" + reloginpass + '\'' + ", verifyCode='" + verifyCode + '\'' + ", newloginpass='" + newloginpass + '\'' + '}';
    }
}
