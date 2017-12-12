package com.suhang.library.mapper;

import java.util.List;
import java.util.Map;

import com.suhang.library.po.BookCustom;
import com.suhang.library.po.BookVo;

/**
 * @author hang.su
 * @since 2017-11-10 14:49
 */
public interface BookMapper {
    public List<BookCustom> findByCategory(BookVo bookVo) throws Exception;

    public Integer findCountByCid(String cid) throws Exception;

    public List<BookCustom> findBookByAuthor(BookVo bookVo) throws Exception;

    public Integer findCountByAuthor(String author) throws Exception;

    public List<BookCustom> findBookByPress(BookVo bookVo) throws Exception;

    public Integer findCountByPress(String press)throws Exception;

    public List<BookCustom> findBookLikeBname(BookVo bookVo)throws Exception;

    public  Integer findCountLikeBname(BookVo bookVo)throws Exception;

    public List<BookCustom> findBookByMulity(BookVo bookVo)throws Exception;

    public Integer findCountByMulity(BookVo bookVo)throws Exception;

    public BookCustom findBookByBid(String bid)throws Exception;

    public Integer findBookCountByCid(String cid)throws Exception;

    public void insertBook(BookCustom bookCustom)throws Exception;

    public void updateBookByBid(BookCustom bookCustom)throws Exception;

    public void deleteBookByBid(String bid)throws Exception;
}
