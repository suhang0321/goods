package com.suhang.library.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;

import com.suhang.library.mapper.AdminMapper;
import com.suhang.library.po.Admin;
import com.suhang.library.service.AdminService;

/**
 * @author hang.su
 * @since 2017-11-21 14:03
 */
public class AdminServiceImp implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String adminname, String adminpwd) throws Exception {
        Admin admin = new Admin();
        admin.setAdminname(adminname);
        admin.setAdminpwd(adminpwd);
        Admin adminDb = adminMapper.findAdminByNameAndPwd(admin);
        return adminDb;
    }
}
