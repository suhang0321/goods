package com.suhang.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suhang.library.po.CategoryCustom;
import com.suhang.library.service.CategoryService;

/**
 * @author hang.su
 * @since 2017-11-09 10:24
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
@RequestMapping("/findAllClass")
    public String findAllClass(HttpServletRequest request,HttpServletResponse response,
    Model model)throws Exception{
        List<CategoryCustom> parents = categoryService.findAllClass();
        request.setAttribute("parents",parents);
        return "/jsps/left.jsp";
    }
}
