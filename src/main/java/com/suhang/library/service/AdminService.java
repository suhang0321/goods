package com.suhang.library.service;

import com.suhang.library.po.Admin;

/**
 * @author hang.su
 * @since 2017-11-21 14:02
 */
public interface AdminService {
    public Admin login(String adminname,String adminpwd)throws Exception;
}
