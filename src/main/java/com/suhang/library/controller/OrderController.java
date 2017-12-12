package com.suhang.library.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suhang.library.po.CartItemVo;
import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;
import com.suhang.library.po.OrderVo;
import com.suhang.library.po.UserCustom;
import com.suhang.library.service.CartItemService;
import com.suhang.library.service.OrderService;
import com.suhang.library.util.ConstantUtil;

/**
 * @author hang.su
 * @since 2017-11-16 8:39
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemService cartItemService;
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

    @RequestMapping("/myOrder")
    public String myOrder(HttpServletRequest request, HttpServletResponse response,
        Model model)throws Exception{
        UserCustom userCustom = (UserCustom) request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        Integer pc = getPc(request);
        Integer ps = ConstantUtil.ORDER_AMOUNT_EACH_PAGE;
        List<OrderCustom> orderCustomList = orderService.findOrderByUid(uid,pc,ps);
        Integer tr = orderService.findOrderCountByUid(uid);
        Integer tp = getTp(tr,ps);
        String url = request.getRequestURI()+"?" +request.getQueryString();
        int index = url.lastIndexOf("&pc");
        if (index!=-1){url = url.substring(0,index);}
        for (OrderCustom orderCustom:orderCustomList){//循环遍历为每一个订单添加设置它的订单条目
            List<OrderItem> orderItemList = orderService.findOrderItemsByOid(orderCustom.getOid());
            orderCustom.setOrderItemList(orderItemList);
        }
        OrderVo orderVo = new OrderVo();
        orderVo.setPs(ps);
        orderVo.setPc(pc);
        orderVo.setTp(tp);
        orderVo.setTr(tr);
        orderVo.setUrl(url);
        orderVo.setUid(uid);
        request.setAttribute("pb",orderVo);
        request.setAttribute("orderCustomList",orderCustomList);
        return "/jsps/order/list.jsp";
    }

    @RequestMapping("/createOrder")
    public String createOrder(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String cartItemIds = request.getParameter("cartItemIds");
        Object[] cartItemIdArray = cartItemIds.split(",");
        String address = request.getParameter("address");
        double total = Double.parseDouble(request.getParameter("total"));
        UserCustom userCustom = (UserCustom) request.getSession().getAttribute("sessionUser");
        String uid = userCustom.getUid();
        OrderCustom orderCustom = orderService.insertOrder(address,total,uid);
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for (int j=0; j<cartItemIdArray.length;j++){
            CartItemVo cartItemVo = cartItemService.findCartItemByCartItemId(cartItemIdArray[j]+"");
            cartItemService.deleteCartItemByCartItemId(cartItemIdArray[j]+"");
            OrderItem orderItem = orderService.insertOrderItem(cartItemVo.getBid(),cartItemVo.getBname(),cartItemVo.getImage_b(),orderCustom.getOid(),cartItemVo.getSubtotal(),cartItemVo.getQuantity(),cartItemVo.getCurrPrice());
            orderItemList.add(orderItem);
        }
        orderCustom.setOrderItemList(orderItemList);
        request.setAttribute("order",orderCustom);
        return "/jsps/order/ordersucc.jsp";
    }
    @RequestMapping("/load")
    public String load(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        String btn = request.getParameter("btn");
        OrderCustom orderCustom = orderService.findOrderByOid(oid);
        List<OrderItem> orderItemList = orderService.findOrderItemsByOid(oid);
        for (OrderItem orderItem :orderItemList){
            BigDecimal b1 = new BigDecimal(orderItem.getCurrPrice()+"");
            BigDecimal b2 = new BigDecimal(orderItem.getQuantity()+"");
            BigDecimal b3 = b1.multiply(b2);
            double b4 =b3.doubleValue();
            orderItem.setSubtotal(b4);
        }
        orderCustom.setOrderItemList(orderItemList);
        request.setAttribute("order" ,orderCustom);
        request.setAttribute("btn",btn);
        return  "/jsps/order/desc.jsp";

    }
    @RequestMapping("/cancel")
    public String cancel(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        OrderCustom orderCustomDb = orderService.findOrderByOid(oid);
        if (orderCustomDb.getStatus()!=1){
            request.setAttribute("code","error");
            request.setAttribute("msg","状态错误，不能取消订单");
            return "forward:/jsps/msg.jsp";
        }else {
            orderService.updateStatusByOid(oid,5);
            request.setAttribute("code","success");
            request.setAttribute("msg","恭喜您操作成功，订单已取消");
            return "forward:/jsps/msg.jsp";
        }
    }
    @RequestMapping("/confirm")
    public String confirm(HttpServletResponse response, HttpServletRequest request,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        OrderCustom orderCustomDb = orderService.findOrderByOid(oid);
        if (orderCustomDb.getStatus()!=3){
            request.setAttribute("code","error");
            request.setAttribute("msg","状态错误，无法确认收货");
            return "forward:/jsps/msg.jsp";
        }else {
            orderService.updateStatusByOid(oid,4);
            request.setAttribute("code","success");
            request.setAttribute("msg","恭喜您确认收货，交易成功");
            return "forward:/jsps/msg.jsp";
        }
    }
    @RequestMapping("/paymentPre")
    public String paymentPre(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        OrderCustom orderCustom = orderService.findOrderByOid(oid);
        request.setAttribute("order",orderCustom);
        return "forward:/jsps/order/pay.jsp";
    }
}
