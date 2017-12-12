package com.suhang.library.serviceimp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.suhang.library.mapper.CartItemMapper;
import com.suhang.library.po.CartItemVo;
import com.suhang.library.service.CartItemService;

/**
 * @author hang.su
 * @since 2017-11-13 14:37
 */
public class CartItemServiceImp implements CartItemService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItemVo> myCart(String uid) throws Exception {
        List<CartItemVo> cartItemVoList = cartItemMapper.findCartByUid(uid);
        for (CartItemVo cartItemVo:cartItemVoList){
            BigDecimal b1 =new BigDecimal(cartItemVo.getCurrPrice()+"");
            BigDecimal b2 = new BigDecimal(cartItemVo.getQuantity()+"");
            BigDecimal b3 = b1.multiply(b2);
            double b4 =b3.doubleValue();
            cartItemVo.setSubtotal(b4);
        }
        return cartItemVoList;
    }

    @Override
    public CartItemVo findCartItemByBidAndUid(String bid, String uid) throws Exception {
        Map<String,String> map = new HashMap<String,String>();
        map.put("bid",bid);
        map.put("uid",uid);
        CartItemVo cartItemVo = cartItemMapper.findByUindAndBid(map);
        return cartItemVo;
    }

    @Override
    public void updateQuantityByCartItemId(int quantity, String cartItemId) throws Exception {
        Map<String,String>map =new HashMap<String,String>();
        map.put("quantity",quantity+"");
        map.put("cartItemId",cartItemId);
        cartItemMapper.updateQuantityByCartId(map);
    }

    @Override
    public void insetCartItem( int quantity, String bid, String uid) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String cartItemId = uuid.substring(0,32);
        Map<String,String>map = new HashMap<String,String>();
        map.put("cartItemId",cartItemId);
        map.put("quantity",quantity+"");
        map.put("bid",bid);
        map.put("uid",uid);
        cartItemMapper.insertCartItem(map);
    }

    @Override
    public void deleteCartItemByCartItemId(String cartItemId) throws Exception {
        cartItemMapper.deleteCartItemByCartItemId(cartItemId);
    }

    @Override
    public CartItemVo updateQuantityByCartItemIdType(String cartItemId,Integer quantity) throws Exception {
        Map<String,String>map = new HashMap<String,String>();
        map.put("cartItemId",cartItemId);
        map.put("quantity",quantity+"");
        cartItemMapper.updateQuantityByCartId(map);
        CartItemVo cartItemVo = cartItemMapper.findCartItemByCartItemIdType(cartItemId);
        BigDecimal b1 =new BigDecimal(cartItemVo.getCurrPrice()+"");
        BigDecimal b2 = new BigDecimal(cartItemVo.getQuantity()+"");
        BigDecimal b3 = b1.multiply(b2);
        double b4 =b3.doubleValue();
        cartItemVo.setSubtotal(b4);
        return cartItemVo;
    }

    @Override
    public CartItemVo findCartItemByCartItemId(String cartItemId) throws Exception {
        CartItemVo cartItemVo = cartItemMapper.findCartItemByCartItemIdType(cartItemId);
        return cartItemVo;
    }
}
