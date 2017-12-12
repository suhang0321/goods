package com.suhang.library.serviceimp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.suhang.library.mapper.BookMapper;
import com.suhang.library.po.BookCustom;
import com.suhang.library.po.BookVo;
import com.suhang.library.service.BookService;

/**
 * @author hang.su
 * @since 2017-11-10 15:45
 */
public class BookServiceImp implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<BookCustom> findByCategory(int pc, int ps, String cid) throws Exception {
        int snep = (pc-1)*ps;
        BookVo bookVo = new BookVo();
        bookVo.setSnep(snep);
        bookVo.setPc(pc);
        bookVo.setPs(ps);
        bookVo.setCid(cid);
        List<BookCustom> booklist = bookMapper.findByCategory(bookVo);
        return booklist;
    }

    @Override
    public int findCountByCid(String cid) throws Exception {
        int tr = bookMapper.findCountByCid(cid);//totalrecord 查询到的二级分类下的总数
        return tr;
    }

    @Override
    public List<BookCustom> findBookByAuthor(int pc, int ps, String author) throws Exception {
        BookVo bookvo =new BookVo();
        int snep = (pc-1)*ps;
        bookvo.setSnep(snep);
        bookvo.setAuthor(author);
        bookvo.setPs(ps);
        List<BookCustom>booklist = bookMapper.findBookByAuthor(bookvo);
        return booklist;
    }

    @Override
    public int findCountByAuthor(String author) throws Exception {
        int tr = bookMapper.findCountByAuthor(author);
        return tr;
    }

    @Override
    public List<BookCustom> findBookByPress(int pc, int ps, String press) throws Exception {
        BookVo bookvo = new BookVo();
        int snep = (pc-1)*ps;
        bookvo.setSnep(snep);
        bookvo.setPress(press);
        bookvo.setPs(ps);
        List<BookCustom> booklist = bookMapper.findBookByPress(bookvo);
        return booklist;
    }

    @Override
    public Integer findCountByPress(String press) throws Exception {
        int tr = bookMapper.findCountByPress(press);
        return  tr;
    }

    @Override
    public List<BookCustom> findBookLikeBname(int pc, int ps, String bname) throws Exception {
        int snep = (pc-1)*ps;
        BookVo bookVo = new BookVo();
        bookVo.setSnep(snep);
        bookVo.setPs(ps);
        bookVo.setBname(bname);
        List<BookCustom> booklist = bookMapper.findBookLikeBname(bookVo);
        return booklist;
    }

    @Override
    public Integer findCountLikeBname(String bname) throws Exception {
        BookVo bookVo = new BookVo();
        bookVo.setBname(bname);
        Integer tr = bookMapper.findCountLikeBname(bookVo);
        return  tr;
    }

    @Override
    public List<BookCustom> findBookByMulity(int pc, int ps, String bname, String author, String press) throws Exception {
        int snep =(pc-1)*ps;
        BookVo bookVo = new BookVo();
        bookVo.setBname(bname);
        bookVo.setPress(press);
        bookVo.setAuthor(author);
        bookVo.setSnep(snep);
        bookVo.setPs(ps);
        List<BookCustom> booklist = bookMapper.findBookByMulity(bookVo);
        return booklist;
    }

    @Override
    public Integer findCountByMulity(String bname, String author, String press) throws Exception {
        BookVo bookVo = new BookVo();
        bookVo.setBname(bname);
        bookVo.setAuthor(author);
        bookVo.setPress(press);
        Integer tr = bookMapper.findCountByMulity(bookVo);
        return tr;
    }

    @Override
    public BookCustom findBookByBid(String bid) throws Exception {
        BookCustom bookCustom = bookMapper.findBookByBid(bid);
        return bookCustom;
    }

    @Override
    public Integer findBookCountByCid(String cid) throws Exception {
        Integer num = bookMapper.findBookCountByCid(cid);
        return  num;
    }

    @Override
    public void insertBook(BookCustom bookCustom) throws Exception {
        bookCustom.setBid(UUID.randomUUID().toString().substring(0,32));
        bookMapper.insertBook(bookCustom);
    }

    @Override
    public void updateBookByBid(BookCustom bookCustom) throws Exception {
        bookMapper.updateBookByBid(bookCustom);
    }

    @Override
    public void deleteBookByBid(String bid) throws Exception {
        bookMapper.deleteBookByBid(bid);
    }
}
