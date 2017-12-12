package com.suhang.library.mapper;

import java.util.Map;

import com.suhang.library.po.User;
import com.suhang.library.po.UserCustom;

/**
 * @author hang.su
 * @since 2017-10-31 9:23
 */
public interface UserMapper {
    public UserCustom findUserByUid(String uid) throws Exception;

    public void insertUser(UserCustom userCustom)throws Exception;

    public int queryCountByLoginname(String loginname)throws Exception;

    public int queryCountByEmail(String email)throws Exception;

    public UserCustom findUserByCode(String activationCode)throws Exception;

    public void updateStatusByid(Map<String,String> map)throws Exception;

    public UserCustom findByNameAndPass(Map<String,String> map)throws Exception;

    public Integer findByUidAndPass(Map<String,String> map)throws Exception;

    public void updatePassByUid(Map<String,String> map)throws Exception;
}
