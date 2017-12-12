package com.suhang.library.serviceimp;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.suhang.library.exception.UserException;
import com.suhang.library.mapper.UserMapper;
import com.suhang.library.po.User;
import com.suhang.library.po.UserCustom;
import com.suhang.library.service.UserService;
import com.suhang.library.util.MailUtil;

/**
 * @author hang.su
 * @since 2017-10-30 10:17
 */
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean ifqueryCountByLoginname(String loginname) throws Exception {
        int count = userMapper.queryCountByLoginname(loginname);
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ifqueryCountByEmail(String email) throws Exception {
        int count = userMapper.queryCountByEmail(email);
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertUser(UserCustom userCustom) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String uid = uuid.substring(0, 32);
        userCustom.setUid(uid);
        Integer status = 0;
        String str1 = UUID.randomUUID().toString().substring(0, 32);
        String str2 = UUID.randomUUID().toString().substring(0, 32);
        String activationCode = str1 + str2;
        userCustom.setActivationCode(activationCode);
        userCustom.setStatus(status);
        MailUtil mailUtil = new MailUtil();
        String body = MessageFormat.format("您已完成狗宝会员的注册，点击<a href=http://localhost:8080/goods/user/activation.action?activationCode={0}>这里</a>完成激活", userCustom.getActivationCode());
        String receiveMailAccount = userCustom.getEmail();
        mailUtil.sendTo(body, receiveMailAccount);
        userMapper.insertUser(userCustom);
    }

    @Override
    public void activation(String activationCode) throws UserException {

        UserCustom userCustom = null;
        try {
            userCustom = userMapper.findUserByCode(activationCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userCustom == null) throw new UserException("无效的验证码！");
        if (userCustom.getStatus() == 1) throw new UserException("您已激活成功，请不要二次激活");
              Map<String, String> map = new HashMap<String, String>();
                map.put("status", "1");
                map.put("uid", userCustom.getUid());
        try {
            userMapper.updateStatusByid(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserCustom login(UserCustom userCustom) throws Exception {
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginname",userCustom.getLoginname());
        map.put("loginpass",userCustom.getLoginpass());
        UserCustom userCustomDb = userMapper.findByNameAndPass(map);
        return userCustomDb;
    }

    @Override
    public void updatePassByOldPass(String uid, String newloginpass, String oldloginpass) throws UserException {
        Map<String,String> querymap = new HashMap<String,String>();
        querymap.put("uid",uid);
        querymap.put("loginpass",oldloginpass);

        Integer number = null;
        try {
            number = userMapper.findByUidAndPass(querymap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (number==0) {throw new UserException("老密码不正确！");}
            else{
                Map<String,String> updatemap =new HashMap<String,String>();
                updatemap.put("newloginpass",newloginpass);
                updatemap.put("uid",uid);
            try {
                userMapper.updatePassByUid(updatemap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
