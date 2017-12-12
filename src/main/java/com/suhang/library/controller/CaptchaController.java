package com.suhang.library.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.suhang.library.service.UserService;

/**
 * @author hang.su
 * @since 2017-11-02 12:54
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {
    private static final Log log = LogFactory.getLog(CaptchaController.class);
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UserService userService;
    @RequestMapping("/getCaptcha")
    public String getCaptcha(Model model) {
        model.addAttribute("timestamp", System.currentTimeMillis());
        return "captcha";
    }
    @RequestMapping("/getCaptchaImage")
    public ModelAndView getCaptchaImage(Model model,HttpServletRequest request, HttpServletResponse response,
        @RequestParam(value = "timestamp", required = false) String timestamp) throws IOException {
        if (StringUtils.isEmpty(timestamp)) {
            //System.out.println("没有时间戳\ttimestamp:" + timestamp);
            model.addAttribute("timestamp", System.currentTimeMillis());
        } else {
            //System.out.println("有时间戳\ttimestamp:" + timestamp);
            model.addAttribute("timestamp", timestamp);
        }

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        log.info("======生成了一个验证码，内容为：" + capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/checkCaptcha")
    @ResponseBody
    public String checkCaptcha(@RequestParam(value = "timestamp", required = false) String timestamp,
        @RequestParam(value = "verifyCode", required = false)
        String verifyCode,HttpServletRequest request,HttpServletResponse response)throws Exception {
        boolean returnStr = false;
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String original =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        log.info("======用户输入的验证码：" + verifyCode);
        log.info("======正确的验证码：" + original);

        if (!StringUtils.isEmpty(verifyCode)) {
            if (verifyCode.equalsIgnoreCase(original)) {
                returnStr = true;
            }
        }
        response.getWriter().print(returnStr + "");
        log.info("======验证码验证结果" + returnStr);
        return null;
    }
    @RequestMapping("/ajaxValidateLoginname")
    @ResponseBody
    public String ajaxValidateLoginname(@RequestParam(value ="loginname",required = false)
        String loginname,HttpServletRequest request,HttpServletResponse response)throws Exception{
        request.getParameter("loginname");
         boolean b =userService.ifqueryCountByLoginname(loginname);
         response.getWriter().print(b+"");
         log.info("======用户名验证结果" +b);
        return null;
    }
    @RequestMapping("/ajaxValidateEmail")
    @ResponseBody
    public String ajaxValidateEmail(@RequestParam(value = "email",required = false)
        String email,HttpServletRequest request,HttpServletResponse response)throws Exception{
        request.getParameter("email");
        boolean bool =userService.ifqueryCountByEmail(email);
        response.getWriter().print(bool+"");
        log.info("========Email验证结果" +bool);
        return null;
    }
}
