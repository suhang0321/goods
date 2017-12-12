package com.suhang.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suhang.library.po.BookCustom;
import com.suhang.library.po.BookVo;
import com.suhang.library.service.BookService;
import com.suhang.library.util.ConstantUtil;

/**
 * @author hang.su
 * @since 2017-11-10 15:56
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    private int getPc(HttpServletRequest request) {
        int pc = 1;
        String param = request.getParameter("pc");
        if (param != null && !param.trim().isEmpty()) {
            try {
                pc = Integer.parseInt(param);
            } catch (RuntimeException e) {
            }
        }
        return pc;
    }

    private int getTp(int tr, int ps) { //totalpage totalrecord pagesize 总页数，总记录数，一页多少条记录
        int tp = tr / ps;
        return tr % ps == 0 ? tp : tp + 1;
    }

    @RequestMapping("/findBookByCategory")
    public String findBookByCategory(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        //"/goods/book/findBookByCategory.action?cid=xxx
        int pc = getPc(request);
        BookVo bookVo = new BookVo();
        String cid = request.getParameter("cid");
        String url1 = request.getRequestURI();//得到/goods/book/findBookByCategory.action
        String url2 = request.getQueryString();//得到cid=xxx
        String url = url1 + "?" + url2;
        int index = url.lastIndexOf("&pc=");
        if (index != -1) {
            url = url.substring(0, index);
        }
        int ps = ConstantUtil.AMOUNT_EACH_PAGE;
        List<BookCustom> booklist = bookService.findByCategory(pc, ps, cid);
        int tr = bookService.findCountByCid(cid);
        int tp = getTp(tr, ps);
        bookVo.setTp(tp);
        bookVo.setTr(tr);
        bookVo.setCid(cid);
        bookVo.setPc(pc);
        bookVo.setPs(ps);
        bookVo.setUrl(url);
        request.setAttribute("booklist", booklist);
        request.setAttribute("pb", bookVo);
        return "/jsps/book/list.jsp";
    }

    @RequestMapping("/findBookByAuthor")
    public String findBookByAuthor(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String author = request.getParameter("author");
        int ps = ConstantUtil.AMOUNT_EACH_PAGE;//pagesize 分页中每一页所包含的最大图书数量
        int pc = getPc(request);
        List<BookCustom> booklist = bookService.findBookByAuthor(pc, ps, author);
        int tr = bookService.findCountByAuthor(author);//totalrecord 查询到的总图书数
        int tp = getTp(tr, ps);//totalpage 算法得到一共显示多少页
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pc");
        if (index != -1) {
            url = url.substring(0, index);
        }
        BookVo bookVo = new BookVo();
        bookVo.setPs(ps);
        bookVo.setAuthor(author);
        bookVo.setTr(tr);
        bookVo.setTp(tp);
        bookVo.setPc(pc);
        bookVo.setUrl(url);
        request.setAttribute("pb", bookVo);
        request.setAttribute("booklist", booklist);
        return "/jsps/book/list.jsp";
    }

    @RequestMapping("/findBookByPress")
    public String findBookByPress(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String press = request.getParameter("press");
        int ps = ConstantUtil.AMOUNT_EACH_PAGE;//pagesize 分页中每一页所包含的最大图书数量
        int pc = getPc(request);
        List<BookCustom> booklist = bookService.findBookByPress(pc, ps, press);
        int tr = bookService.findCountByPress(press);//totalrecord 查询到的总图书数
        int tp = getTp(tr, ps);//totalpage 算法得到一共显示多少页
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pc");
        if (index != -1) {
            url = url.substring(0, index);
        }
        BookVo bookVo = new BookVo();
        bookVo.setPs(ps);
        bookVo.setPress(press);
        bookVo.setTr(tr);
        bookVo.setTp(tp);
        bookVo.setPc(pc);
        bookVo.setUrl(url);
        request.setAttribute("pb", bookVo);
        request.setAttribute("booklist", booklist);
        return "/jsps/book/list.jsp";
    }

    @RequestMapping("/findBookLikeBname")
    public String findBookLikeBname(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String bname = request.getParameter("bname");
        int pc = getPc(request);
        int ps = ConstantUtil.AMOUNT_EACH_PAGE;
        List<BookCustom> booklist = bookService.findBookLikeBname(pc, ps, bname);
        int tr = bookService.findCountLikeBname(bname);
        int tp = getTp(tr, ps);
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pc");
        if (index != -1) {
            url = url.substring(0, index);
        }
        BookVo bookVo = new BookVo();
        bookVo.setUrl(url);
        bookVo.setPs(ps);
        bookVo.setPc(pc);
        bookVo.setTp(tp);
        bookVo.setTr(tr);
        request.setAttribute("booklist", booklist);
        request.setAttribute("pb", bookVo);
        return "/jsps/book/list.jsp";
    }

    @RequestMapping("/findBookByMulity")
    public String findBookByMulity(HttpServletRequest request, HttpServletResponse response, Model model, BookCustom bookCustom) throws Exception {
        int pc = getPc(request);
        int ps = ConstantUtil.AMOUNT_EACH_PAGE;
        String bname = request.getParameter("bname");
        String author = request.getParameter("author");
        String press = request.getParameter("press");
        List<BookCustom> booklist = bookService.findBookByMulity(pc, ps, bname, author, press);
        int tr = bookService.findCountByMulity(bname, author, press);
        int tp = getTp(tr, ps);
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pc");
        if (index != -1) {
            url = url.substring(0, index);
        }
        BookVo bookVo = new BookVo();
        bookVo.setPc(pc);
        bookVo.setPs(ps);
        bookVo.setTr(tr);
        bookVo.setTp(tp);
        bookVo.setUrl(url);
        request.setAttribute("booklist", booklist);
        request.setAttribute("pb", bookVo);
        return "/jsps/book/list.jsp";
    }
    @RequestMapping("/load")
    public String load(HttpServletResponse response, HttpServletRequest request,
        Model model) throws Exception {
        String bid = request.getParameter("bid");
        BookCustom book = bookService.findBookByBid(bid);
        request.setAttribute("book",book);
        return "forward:/jsps/book/desc.jsp";
    }
}
