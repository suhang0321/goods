package com.suhang.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suhang.library.po.Admin;
import com.suhang.library.po.CategoryCustom;
import com.suhang.library.service.AdminService;
import com.suhang.library.service.BookService;
import com.suhang.library.service.CategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * @author hang.su
 * @since 2017-11-21 14:07
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @RequestMapping("/login")
    public String login(HttpServletResponse response,HttpServletRequest request,Admin admin,
        Model model)throws Exception{
        String adminname = request.getParameter("adminname");
        String adminpwd = request.getParameter("adminpwd");
        Admin adminDb = adminService.login(adminname,adminpwd);
        if (adminDb==null){
            request.setAttribute("msg","账号或密码错误");
            model.addAttribute("admin",admin);
            return "forward:/adminjsps/login.jsp";
        }else {
            request.getSession().setAttribute("admin",adminDb);
            return "redirect:/adminjsps/admin/index.jsp";
        }
    }
    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
        request.setAttribute("parents",categoryCustomList);
        return "forward:/adminjsps/admin/category/list.jsp";
    }
    @RequestMapping("/addParent")
    public String addParent(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        categoryService.addFirstClass(cname,desc);
        List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
        request.setAttribute("parents",categoryCustomList);
        return "forward:/adminjsps/admin/category/list.jsp";
    }
    @RequestMapping("/addChildrenPre")
    public String addChildrenPre(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String pid = request.getParameter("pid");
        List<CategoryCustom> parents = categoryService.findFirstClass();
        request.setAttribute("pid",pid);
        request.setAttribute("parents",parents);
        return "/adminjsps/admin/category/add2.jsp";
    }
    @RequestMapping("/addChild")
    public String addChild(HttpServletResponse response,HttpServletRequest request,
        Model model,CategoryCustom categoryCustom)throws Exception{
        String pid = request.getParameter("pid");
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        categoryService.addSecondClass(cname,desc,pid);
        List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
        request.setAttribute("parents",categoryCustomList);
        return "forward:/adminjsps/admin/category/list.jsp";
    }
    @RequestMapping("/editParentPre")
    public String editParentPre(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        /**
         * 加载一级分类
         * 1 从页面你获取要修改的一级分类的cid
         * 2 调用service查询出该一级分类的信息
         * 3 保存查询出的信息
         * 4 在edit.jsp页面显示
         */
        String cid = request.getParameter("cid");
        CategoryCustom parent = categoryService.findClassByCid(cid);
        request.setAttribute("parent",parent);
        return "forward:/adminjsps/admin/category/edit.jsp";
    }
    @RequestMapping("/editParent")
    public String editParent(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        /**
         * 从edit.jsp页面提交的表单开始 更改一级分类
         * 1 获取表单数据的cname和cid desc
         * 2 调用categoryservice的方法更改一级分类
         * 3 查询所有分类 返回到list.jsp页面显示
         */
        String cid = request.getParameter("cid");
        String cname  = request.getParameter("cname");
        String desc = request.getParameter("desc");
        categoryService.updateFirstClassByCid(cid,cname,desc);
        List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
        request.setAttribute("parents",categoryCustomList);
        return "forward:/adminjsps/admin/category/list.jsp";
    }
    @RequestMapping("/editChildPre")
    public String editChildPre(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String cid = request.getParameter("cid");
        CategoryCustom child = categoryService.findClassByCid(cid);
        CategoryCustom parent = categoryService.findClassByCid(child.getPid());
        child.setParent(parent);
        List<CategoryCustom> parents = categoryService.findFirstClass();
        request.setAttribute("parents",parents);
        request.setAttribute("child",child);
        return "forward:/adminjsps/admin/category/edit2.jsp";
    }
    @RequestMapping("/editChild")
    public String editChild(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        categoryService.updateSecondClassByCid(cid,cname,desc);
        List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
        request.setAttribute("parents",categoryCustomList);
        return "forward:/adminjsps/admin/category/list.jsp";
    }
    @RequestMapping("/deleteParent")
    public String deleteParent(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String cid = request.getParameter("cid");
        Integer num = categoryService.findChildCountByParentId(cid);
        if (num!=0){
            request.setAttribute("msg","当前分类下含有二级分类，无法删除");
            return "forward:/adminjsps/msg.jsp";
        }else {
            categoryService.deleteByCid(cid);
            List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
            request.setAttribute("parents",categoryCustomList);
            return "forward:/adminjsps/admin/category/list.jsp";
        }
    }
    @RequestMapping("/deleteChild")
    public String deleteChild(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String cid = request.getParameter("cid");
        Integer num = bookService.findBookCountByCid(cid);
        if (num!=0){
            request.setAttribute("msg","当前二级分类下含有图书，不能直接删除这个二级分类！");
            return "forward:/adminjsps/msg.jsp";
        }else {
            categoryService.deleteByCid(cid);
            List<CategoryCustom> categoryCustomList = categoryService.findAllClass();
            request.setAttribute("parents",categoryCustomList);
            return "forward:/adminjsps/admin/category/list.jsp";
        }
    }
}
