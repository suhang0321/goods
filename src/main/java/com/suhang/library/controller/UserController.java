package com.suhang.library.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.suhang.library.exception.UserException;
import com.suhang.library.po.UserCustom;
import com.suhang.library.service.UserService;

/**
 * @author hang.su
 * @since 2017-11-01 13:33
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public String regist(UserCustom userCustom, HttpServletRequest request, HttpServletResponse respond, Model model) throws Exception {
        /**
         * controller校验
         */
        Map<String, String> errors = new HashMap<>();
        String loginname = userCustom.getLoginname();
        if (loginname == null || loginname.trim().isEmpty()) {
            errors.put("loginname", "用户名不能为空");
        } else if (loginname.length() < 3 || loginname.length() > 20) {
            errors.put("loginname", "请输入3到20个字符的用户名");
        } else if (!userService.ifqueryCountByLoginname(loginname)) {
            errors.put("loginname", "用户名已被注册");
        }
        String loginpass = userCustom.getLoginpass();
        if (loginpass == null | loginpass.trim().isEmpty()) {
            errors.put("loginpass", "密码不能为空");
        } else if (loginpass.length() < 3 | loginpass.length() > 20) {
            errors.put("loginpass", "请将密码输入在3到20个字符之间");
        }
        String reloginpass = userCustom.getReloginpass();
        if (reloginpass == null || reloginpass.trim().isEmpty()) {
            errors.put("reloginpass", "确认密码不能为空");
        } else if (!reloginpass.equals(loginpass)) {
            errors.put("reloginpass", "两次输入不相同");
        }
        String email = userCustom.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空");
        } else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
            errors.put("email", "Email格式不正确");
        } else if (!userService.ifqueryCountByEmail(email)) {
            errors.put("email", "Email已被注册");
        }
        String verifyCode = userCustom.getVerifyCode();
        String original = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (verifyCode == null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode", "验证码不能为空");
        } else if (!verifyCode.equalsIgnoreCase(original)) {
            errors.put("verifyCode", "验证码输入错误");
        }
        if (errors.size() > 0) {
            model.addAttribute("form", userCustom);
            request.setAttribute("errors", errors);
            return "forward:/jsps/user/regist.jsp";
        }

        /**
         * 使用service完成业务
         */
        userService.insertUser(userCustom);

        /**
         * 保存成功信息，转发到msg.jsp页面
         */
        request.setAttribute("code", "success");
        request.setAttribute("msg", "注册狗宝会员成功，请到您的邮箱激活");
        return "forward:/jsps/msg.jsp";
    }

    @RequestMapping("/activation")
    public String activation(String activationCode, HttpServletResponse response,
        HttpServletRequest request, Model model) throws Exception {
        request.getParameter("activationCode");
       try {
           userService.activation(activationCode);
           request.setAttribute("code","success");
           request.setAttribute("msg","恭喜您激活成功，请登陆您的账号！");
       }catch (UserException e){
           request.setAttribute("code", "error");
           request.setAttribute("msg",e.getMessage());
       }

        return "forward:/jsps/msg.jsp";
    }
    @RequestMapping("/login")
    public String login(UserCustom userCustom,HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        /**
         * 校验表单数据
         */
        Map<String,String> errors = new HashMap<String, String>();
        String loginname = userCustom.getLoginname();
        if (loginname==null||loginname.trim().isEmpty()){
         errors.put("loginname","用户名不能为空！");
        }else if (loginname.length()<3||loginname.length()>20){
            errors.put("loginname","用户名长度应该在3到20个字符之间");
        }
        String loginpass = userCustom.getLoginpass();
        if (loginpass==null||loginpass.trim().isEmpty()){
            errors.put("loginpass","密码不能为空");
        }else if (loginpass.length()<3||loginpass.length()>20){
            errors.put("loginpass","密码长度应该在3到20个字符之间！");
        }
        String verifyCode = userCustom.getVerifyCode();
        String original = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (verifyCode==null||verifyCode.trim().isEmpty()){
            errors.put("verifyCode","验证码不能为空");
        }else if (!verifyCode.equalsIgnoreCase(original)){
            errors.put("verifyCode","验证码输入错误");
        }
        if (errors.size()>0){
            request.setAttribute("errors",errors);
            model.addAttribute("user",userCustom);
            return "forward:/jsps/user/login.jsp";
        }
        /**
         * 调用service的login方法
         */
        UserCustom userCustomDb = userService.login(userCustom);//在数据库查询到的数据
        /**
         * 登陆校验
         */
        if (userCustomDb==null){
            request.setAttribute("msg","用户名或密码错误！");
            model.addAttribute("user",userCustom);
            return "forward:/jsps/user/login.jsp";
        }else{
            if(userCustomDb.getStatus()==0){
                request.setAttribute("msg","用户还没有激活！");
                model.addAttribute("user",userCustom);
                return "forward:/jsps/user/login.jsp";
            }else{
                request.getSession().setAttribute("sessionUser",userCustomDb);//把数据库查到的数据装到session中
                //将用户名信息保存到Cookie中
                String loginnameDb = userCustomDb.getLoginname();
                loginnameDb = URLEncoder.encode("loginnameDb","utf-8");
                Cookie cookie = new Cookie("loginnameDb",loginnameDb);
                cookie.setMaxAge(60*60*24*10);//保存10天
                response.addCookie(cookie);
                return "redirect:/index.jsp";
            }
        }
    }
    @RequestMapping("/updatePass")
    public String updatePass(HttpServletRequest request,HttpServletResponse response,UserCustom userCustom,
        Model model)throws Exception{
        /**
         * 校验表单数据
         */
        request.getParameter("userCustom");
        Map<String,String> errors = new HashMap<String,String>();
        String loginpass = userCustom.getLoginpass();//旧密码
        if (loginpass==null||loginpass.trim().isEmpty()){
            errors.put("loginpass","原密码不能为空");
        }else if (loginpass.length()<3||loginpass.length()>20){
            errors.put("loginpass","请输入3到20位的用户密码");
        }
        String newloginpass = userCustom.getNewloginpass();
        if (newloginpass==null||newloginpass.trim().isEmpty()){
            errors.put("newloginpass","新密码不能为空");
        }else if (newloginpass.length()<3||newloginpass.length()>20){
            errors.put("newloginpass","新密码长度应该在3到20个字符之间！");
        }
        String reloginpass = userCustom.getReloginpass();
        if (reloginpass==null||reloginpass.trim().isEmpty()){
            errors.put("reloginpass","确认密码不能为空！");
        }else if (!reloginpass.equals(newloginpass)){
            errors.put("reloginpass","确认密码与新密码不相同！");
        }
        String verifyCode = userCustom.getVerifyCode();
        String original = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (verifyCode==null||verifyCode.trim().isEmpty()){
            errors.put("verifyCode","验证码不能为空");
        }else if (!verifyCode.equalsIgnoreCase(original)){
            errors.put("verifyCode","验证码输入错误！");
        }
        if (errors.size()>0){
            request.setAttribute("errors",errors);
            model.addAttribute("user",userCustom);
            return "forward:/jsps/user/psw.jsp";
        }
        /**
         * 在session中获取已登陆用户的uid
         */
        UserCustom usercustomeInSession = (UserCustom)request.getSession().getAttribute("sessionUser");
        if (usercustomeInSession==null){
            request.setAttribute("msg","您还没有登陆，请先登录！");
            return "forward:/jsps/user/login.jsp";
        }
        try {
            userService.updatePassByOldPass(usercustomeInSession.getUid(),userCustom.getNewloginpass(),userCustom.getLoginpass());
            request.setAttribute("code","success");
            request.setAttribute("msg","恭喜您修改密码成功");
            return "forward:/jsps/msg.jsp";
        }catch (UserException exception){
            request.setAttribute("msg",exception.getMessage());
            model.addAttribute("user",userCustom);
            return "forward:/jsps/user/pwd.jsp";
        }
    }
    @RequestMapping("/quick")
    public String quick(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception {
        request.getSession().invalidate();
        return "redirect:/index.jsp";

    }
}
