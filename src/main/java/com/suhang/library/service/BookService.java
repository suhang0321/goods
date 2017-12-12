package com.suhang.library.service;

import java.util.List;
import java.util.LongSummaryStatistics;

import com.suhang.library.po.BookCustom;

/**
 * @author hang.su
 * @since 2017-11-10 15:44
 */
public interface BookService {
    //pagecode当前页码，pagesize 每页包含的book数量，
    public List<BookCustom> findByCategory(int pc,int ps,String cid)throws Exception;

    public int findCountByCid(String cid)throws Exception;

    public List<BookCustom> findBookByAuthor(int pc,int ps,String author)throws Exception;

    public int findCountByAuthor(String author)throws Exception;

    public List<BookCustom> findBookByPress(int pc,int ps,String press)throws Exception;

    public Integer findCountByPress(String press)throws Exception;

    public List<BookCustom> findBookLikeBname(int pc,int ps,String bname)throws Exception;

    public Integer findCountLikeBname(String bname)throws Exception;

    public List<BookCustom> findBookByMulity(int pc,int ps,String bname,String author,String press)throws Exception;

    public Integer findCountByMulity(String bname ,String author,String press)throws Exception;

    public BookCustom findBookByBid(String bid)throws Exception;

    public Integer findBookCountByCid(String cid)throws Exception;

    public void insertBook(BookCustom bookCustom)throws Exception;

    public void updateBookByBid(BookCustom bookCustom)throws Exception;

    public void deleteBookByBid(String bid)throws Exception;
}
