package com.suhang.library.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.suhang.library.po.Book;
import com.suhang.library.po.BookCustom;
import com.suhang.library.po.BookVo;
import com.suhang.library.po.CategoryCustom;
import com.suhang.library.service.BookService;
import com.suhang.library.service.CategoryService;
import com.suhang.library.util.ConstantUtil;

/**
 * @author hang.su
 * @since 2017-11-22 19:42
 */
@Controller
@RequestMapping("/adminBook")
public class AdminBookController {
    @Autowired
    private CategoryService categoryService;
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
        return "/adminjsps/admin/book/list.jsp";
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
        return "/adminjsps/admin/book/list.jsp";
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
        return "/adminjsps/admin/book/list.jsp";
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
        return "/adminjsps/admin/book/list.jsp";
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
        return "/adminjsps/admin/book/list.jsp";
    }

    @RequestMapping("/load")//这个方法显示图书，为编辑图书的第一步
    public String load(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        /**
         * 1获取图书的bid
         */
        String bid = request.getParameter("bid");
        /**
         * 2获取所要加载的图书
         */
        BookCustom book = bookService.findBookByBid(bid);
        /**
         * 3获取所有的一级分类
         */
        List<CategoryCustom> parents = categoryService.findFirstClass();
        /**
         * 获取当前一级分类下的所有二级分类
         */
        String cid = book.getCid();//当前图书所属的二级分类的id
        CategoryCustom categoryCustom = categoryService.findClassByCid(cid);//根据二级分类的id查找分类
        List<CategoryCustom> children = categoryService.findSecondClassByPid(categoryCustom.getPid());//根据二级分类的父id查找该父分类下的所有二级分类
        CategoryCustom oneparent = categoryService.findClassByCid(categoryCustom.getPid());//所属的父分类
        /**
         * po类有多层结构时需要逐层设置属性
         */
        book.setCategory(categoryCustom);
        book.getCategory().setParent(oneparent);
        request.setAttribute("book", book);
        request.setAttribute("parents", parents);
        request.setAttribute("children", children);
        return "forward:/adminjsps/admin/book/desc.jsp";
    }

    @RequestMapping("/findAllCategory")
    public String findAllCategory(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        List<CategoryCustom> parents = categoryService.findAllClass();
        request.setAttribute("parents", parents);
        return "/adminjsps/admin/book/left.jsp";
    }

    @RequestMapping("/addPre")
    public String addPre(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        List<CategoryCustom> parents = categoryService.findFirstClass();
        request.setAttribute("parents", parents);
        return "/adminjsps/admin/book/add.jsp";
    }

    @RequestMapping("/ajaxFindChildren")
    public @ResponseBody
    List<CategoryCustom> ajaxFindChildren(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String pid = request.getParameter("pid");
        List<CategoryCustom> children = categoryService.findSecondClassByPid(pid);
        return children;
    }

    //    String originalFilename = items_pic.getOriginalFilename();
//        if (items_pic != null && originalFilename.length() > 0 && originalFilename != null) {
//        String pic_path = "E:\\develop\\upload\\temp\\";
//        String newFile_Name = UUID.randomUUID() + originalFilename.substring(0, originalFilename.length() - 4);
//        File newFile = new File(pic_path + newFile_Name);
//        items_pic.transferTo(newFile);
//        itemsCustomer.setPic(newFile_Name);
//    }
//        itemsService.updateItems(id, itemsCustomer);
//        return "success";
    @RequestMapping("/insertBook")//需要在springmvc.xml文件中配置相应multipartResolver
    public String insertBook(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "item_image_b", required = false) MultipartFile item_image_b, @RequestParam(value = "item_image_w", required = false) MultipartFile item_image_w, BookCustom book) throws Exception {
        String originaleImagebName = item_image_b.getOriginalFilename();
        if (item_image_b != null && originaleImagebName != null && originaleImagebName.length() != 0) {
            String picImageb_path = "C:\\Users\\zhanghang\\git\\goods\\src\\main\\webapp\\";
            String newFileImage_bName = "book_img/" + UUID.randomUUID().toString().substring(0, 32) + ".jpg";
            File imagebFile = new File(picImageb_path + newFileImage_bName);
            item_image_b.transferTo(imagebFile);
            book.setImage_b(newFileImage_bName);
        }
        String originalImageW = item_image_w.getOriginalFilename();
        if (item_image_w != null && originalImageW != null && originalImageW.length() != 0) {
            String picImagewPath = "C:\\Users\\zhanghang\\git\\goods\\src\\main\\webapp\\";
            String newFileImagewName = "book_img/" + UUID.randomUUID().toString().substring(0, 32) + ".jpg";
            File imagewFile = new File(picImagewPath + newFileImagewName);
            item_image_w.transferTo(imagewFile);
            book.setImage_w(newFileImagewName);
        }
        bookService.insertBook(book);
        request.setAttribute("msg", "恭喜添加图书成功");
        return "/adminjsps/msg.jsp";
    }
    @RequestMapping("/editOrDeleteBook")
    public String editOrDeleteBook(HttpServletResponse response,HttpServletRequest request,
        Model model,BookCustom bookCustom)throws Exception{
        String method = request.getParameter("method");
        if (method.equals("edit")){
            String cid = request.getParameter("cid");//当前图书所属的二级分类在option中拿到
            bookCustom.setCid(cid);
            bookService.updateBookByBid(bookCustom);
            request.setAttribute("msg","修改图书成功!");
            return "forward:/adminjsps/msg.jsp";
        }
        if  (method.equals("delete")){
            String bid = request.getParameter("bid");
            BookCustom bookCustom1 = bookService.findBookByBid(bid);
            /**
             * 删除图书（tomcat路径下的）图片
             * C:\Users\zhanghang\git\goods\src\main\webapp\book_img\1af85a5e-d662-491e-afcd-19085bef.jpg
             */
            String savePath = "C:\\Users\\zhanghang\\git\\goods\\src\\main\\webapp\\";
            new File(savePath+bookCustom1.getImage_b()).delete();
            new File(savePath+bookCustom1.getImage_w()).delete();
            /**
             * 删除图书对象（数据库中）
             */
            bookService.deleteBookByBid(bid);
            request.setAttribute("msg","删除图书成功！");
            return "forward:/adminjsps/msg.jsp";
        }
        request.setAttribute("msg","未知的指令，");
        return "forward:/adminjsps/msg.jsp";
    }

}
