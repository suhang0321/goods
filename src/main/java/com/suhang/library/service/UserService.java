package com.suhang.library.service;

import com.suhang.library.exception.UserException;
import com.suhang.library.po.UserCustom;

/**
 * @author hang.su
 * @since 2017-10-30 10:17
 */
public interface UserService {
    public boolean ifqueryCountByLoginname(String loginname) throws Exception;

    public boolean ifqueryCountByEmail(String email)throws Exception;

    public void insertUser(UserCustom userCustom)throws Exception;

    public void activation(String activationCode)throws UserException;

    public UserCustom login(UserCustom userCustom)throws  Exception;

    public void updatePassByOldPass(String uid,String newloginpass,String oldloginpass)throws UserException;

}
