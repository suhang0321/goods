package com.suhang.library.service;

import java.util.List;

import com.suhang.library.po.CartItemCustom;
import com.suhang.library.po.CartItemVo;

/**
 * @author hang.su
 * @since 2017-11-13 14:37
 */
public interface CartItemService {
    public List<CartItemVo> myCart(String uid)throws Exception;

    public CartItemVo findCartItemByBidAndUid(String bid,String uid)throws Exception;

    public void updateQuantityByCartItemId(int quantity,String cartItemId)throws Exception;

    public void insetCartItem(int quantity,String bid,String uid)throws Exception;

    public void deleteCartItemByCartItemId(String cartItemId)throws Exception;

    public CartItemVo updateQuantityByCartItemIdType(String cartItemId,Integer quantity)throws Exception;

    public CartItemVo findCartItemByCartItemId(String cartItemId)throws Exception;
}
