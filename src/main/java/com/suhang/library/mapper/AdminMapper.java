package com.suhang.library.mapper;

import com.suhang.library.po.Admin;

/**
 * @author hang.su
 * @since 2017-11-21 13:48
 */
public interface AdminMapper {
    public Admin findAdminByNameAndPwd(Admin admin)throws Exception;
}
