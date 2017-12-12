package com.suhang.library.mapper;

import java.util.List;
import java.util.Map;

import com.suhang.library.po.CartItemVo;
import com.suhang.library.po.CartVo;

import net.sf.ehcache.transaction.xa.EhcacheXAException;


/**
 * @author hang.su
 * @since 2017-11-13 14:39
 */
public interface CartItemMapper {
    public List<CartItemVo> findCartByUid(String uid)throws Exception;

    public CartItemVo findByUindAndBid(Map<String,String> map)throws  Exception;

    public void updateQuantityByCartId(Map<String,String>map)throws Exception;

    public void insertCartItem(Map<String,String> map)throws Exception;

    public void deleteCartItemByCartItemId(String cartItemId)throws Exception;

    public CartVo findCartItemByCartItemId(String cartItemId)throws Exception;

    public CartItemVo findCartItemByCartItemIdType(String cartItemid)throws Exception;

}
