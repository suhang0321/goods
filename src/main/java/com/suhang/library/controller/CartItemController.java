package com.suhang.library.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suhang.library.po.CartItemCustom;
import com.suhang.library.po.CartItemVo;
import com.suhang.library.po.UserCustom;
import com.suhang.library.service.CartItemService;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * @author hang.su
 * @since 2017-11-13 14:36
 */
@Controller
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @RequestMapping("/myCart")
    public String myCart(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        UserCustom userCustom=(UserCustom)request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        List<CartItemVo> cartItemVoList = cartItemService.myCart(uid);
        request.setAttribute("cartItemList",cartItemVoList);
        return "forward:/jsps/cart/list.jsp";
    }

    @RequestMapping("/add")
    public String add(HttpServletResponse response,HttpServletRequest request ,
        CartItemCustom cartItemCustom,Model model)throws Exception{
        UserCustom userCustom=(UserCustom)request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        String bid = cartItemCustom.getBid();
        Integer quantity = cartItemCustom.getQuantity();
        CartItemVo cartItemVoDb = cartItemService.findCartItemByBidAndUid(bid,uid);
        if (cartItemVoDb==null){
            cartItemService.insetCartItem(quantity,bid,uid);
        }else{
            Integer totalquantity = cartItemVoDb.getQuantity() + quantity;
            String cartItemId = cartItemVoDb.getCartItemId();
            cartItemService.updateQuantityByCartItemId(totalquantity,cartItemId);
        }
        List<CartItemVo> cartItemVoList = cartItemService.myCart(uid);
        request.setAttribute("cartItemList",cartItemVoList);
        return "forward:/jsps/cart/list.jsp";
    }
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String cartItemId = request.getParameter("cartItemIds");
        cartItemService.deleteCartItemByCartItemId(cartItemId);
        UserCustom userCustom=(UserCustom)request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        List<CartItemVo> cartItemVoList = cartItemService.myCart(uid);
        request.setAttribute("cartItemList",cartItemVoList);
        return "forward:/jsps/cart/list.jsp";
    }
    @RequestMapping("/deletebatch")
    public String deletebatch(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String cartItemIds = request.getParameter("cartItemIds");
        Object[] cartItemIdArray = cartItemIds.split(",");
        for (int i=0; i<cartItemIdArray.length;i++){
            cartItemService.deleteCartItemByCartItemId(cartItemIdArray[i]+"");
        }
        UserCustom userCustom=(UserCustom)request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        List<CartItemVo> cartItemVoList = cartItemService.myCart(uid);
        request.setAttribute("cartItemList",cartItemVoList);
        return "forward:/jsps/cart/list.jsp";

    }
    @RequestMapping("/updateQuantity")
    public @ResponseBody CartItemVo updateQuantity(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String cartItemId = request.getParameter("cartItemId");
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        CartItemVo cartItemVo = cartItemService.updateQuantityByCartItemIdType(cartItemId,quantity);
        return cartItemVo;
    }
    @RequestMapping("/loadCartItems")
    public String loadCartItems(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String cartItemIds = request.getParameter("cartItemIds");
        double total = Double.parseDouble(request.getParameter("total"));
        Object[] cartItemIdArray = cartItemIds.split(",");
        List<CartItemVo> cartItemList = new ArrayList<CartItemVo>();
        for (int i=0;i<cartItemIdArray.length;i++){
            CartItemVo cartItemVo = cartItemService.findCartItemByCartItemId(cartItemIdArray[i]+"");
            BigDecimal b1 =new BigDecimal(cartItemVo.getCurrPrice()+"");
            BigDecimal b2 = new BigDecimal(cartItemVo.getQuantity()+"");
            BigDecimal b3 = b1.multiply(b2);
            double b4 =b3.doubleValue();
            cartItemVo.setSubtotal(b4);
            cartItemList.add(cartItemVo);
        }
        request.setAttribute("cartItemList",cartItemList);
        request.setAttribute("cartItemIds",cartItemIds);
        request.setAttribute("total",total);
        return "/jsps/cart/showitem.jsp";
    }
}
