package com.suhang.library.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.BookCustom;
import com.suhang.library.po.BookVo;

/**
 * @author hang.su
 * @since 2017-11-10 15:02
 */
public class BookMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findByCategoryTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        bookVo.setPc(0);
        bookVo.setPs(12);
        List<BookCustom> booklist = bookMapper.findByCategory(bookVo);
        for (BookCustom bookCustom : booklist) {
            System.out.println(bookCustom);
        }
    }

    @Test
    public void selectCountByCidTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        Integer tr = bookMapper.findCountByCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        System.out.println(tr);

    }

    @Test
    public void findBookByAuthorTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookvo = new BookVo();
        bookvo.setAuthor("明日科技");
        bookvo.setSnep(0);
        bookvo.setPs(12);
        List<BookCustom> booklist = bookMapper.findBookByAuthor(bookvo);
        for (BookCustom bookCustom : booklist) {
            System.out.println(bookCustom);
        }
    }

    @Test
    public void findCountByAuthorTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        Integer tr = bookMapper.findCountByAuthor("明日科技");//totalrecord
        System.out.println(tr);
    }

    @Test
    public void findBookByPressTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setPress("清华大学出版社");
        bookVo.setSnep(0);
        bookVo.setPs(12);
        List<BookCustom> booklist = bookMapper.findBookByPress(bookVo);
        for (BookCustom bookCustom:booklist){
            System.out.println(bookCustom);
        }
    }

    @Test
    public void findCountByPressTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        Integer tr = bookMapper.findCountByPress("清华大学出版社");//totalrecord 查询到的某出版社图书的总本数
        System.out.println(tr);
    }

    @Test
    public void findBookLikeBnameTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setSnep(0);
        bookVo.setPs(12);
        bookVo.setBname("java");
        List<BookCustom> booklist = bookMapper.findBookLikeBname(bookVo);
        for (BookCustom bookCustom :booklist){
            System.out.println(bookCustom);
        }
    }

    @Test
    public void findCountLikeBname() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setBname("java");
        Integer tr = bookMapper.findCountLikeBname(bookVo);
        System.out.println(tr);
    }

    @Test
    public void findBookByMulityTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setSnep(0);
        bookVo.setPs(6);
        bookVo.setBname("java");
        bookVo.setPress("清华");
        bookVo.setAuthor("");
        List<BookCustom>booklist = bookMapper.findBookByMulity(bookVo);
        for (BookCustom bookCustom:booklist){
            System.out.println(bookCustom);
        }
    }

    @Test
    public void findCountByMulityTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookVo bookVo = new BookVo();
        bookVo.setAuthor("");
        bookVo.setPress("清华");
        bookVo.setBname("java");
        int tr = bookMapper.findCountByMulity(bookVo);
        System.out.println(tr);
    }

    @Test
    public void findBookByBidTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookCustom bookCustom = bookMapper.findBookByBid("000A18FDB38F470DBE9CD0972BADB23F");
        System.out.println(bookCustom);
    }

    @Test
    public void findCountByCidTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        Integer num = bookMapper.findBookCountByCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        System.out.println(num);
    }

    @Test
    public void insertBookTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookCustom bookCustom = new BookCustom();
        bookCustom.setCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        bookCustom.setBid("z");
        bookCustom.setBname("java");
        bookMapper.insertBook(bookCustom);
    }

    @Test
    public void updateBookByBidTest() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        BookCustom bookCustom = new BookCustom();
        bookCustom.setBid("89bbbbe1-c069-4530-8574-d8cfc492");
        bookCustom.setCid("4f0fa93b-430f-41d2-9179-fdc7f161");
        bookCustom.setPageNum(666);
        bookCustom.setBname("nlpnlp");
        bookCustom.setAuthor("mrkang");
        bookCustom.setBooksize(32);
        bookCustom.setEdition(1);
        bookMapper.updateBookByBid(bookCustom);
    }

    @Test
    public void deleteBookByBidTes() throws Exception {
        BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
        bookMapper.deleteBookByBid("e961d96b-6bf5-4155-8e44-a90c6f9a");
    }
}
