package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-21 13:37
 */
public class Admin {
    private String adminId;
    private String adminname;
    private String adminpwd;

    @Override
    public String toString() {
        return "Admin{" + "adminId='" + adminId + '\'' + ", adminname='" + adminname + '\'' + ", adminpwd='" + adminpwd + '\'' + '}';
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminname() {
        return adminname;
    }

    public String getAdminpwd() {
        return adminpwd;
    }
}
