package com.suhang.library.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suhang.library.mapper.OrderMapper;
import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;
import com.suhang.library.po.OrderVo;
import com.suhang.library.service.OrderService;
import com.suhang.library.util.ConstantUtil;
import com.sun.jmx.snmp.SnmpEngineParameters;

/**
 * @author hang.su
 * @since 2017-12-12 12:51
 */
@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    public Integer getPc(HttpServletRequest request)throws Exception{
        Integer pc = 1;
        String param = request.getParameter("pc");
       if (param!=null&&!param.trim().isEmpty() ){
           try {
               pc = Integer.parseInt(param);
           }catch (RuntimeException e){
               e.printStackTrace();
           }

       }
        return pc;
    }
    public Integer getTp(Integer tr,Integer ps)throws Exception{//totalpage总页数，totalrecord总记录数，pagesize每页多少条记录
        int tp = tr/ps;
        return tr%ps==0 ?tp:tp+1;
    }

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        Integer pc = getPc(request);
        Integer tr = orderService.findAllOrderCount();
        Integer ps = ConstantUtil.ORDER_AMOUNT_EACH_PAGE;
        Integer tp = getTp(tr,ps);
        List<OrderCustom> orderCustomList = orderService.findAll(pc,ps);
        for (OrderCustom orderCustom:orderCustomList){
            List<OrderItem> orderItemList = orderService.findOrderItemsByOid(orderCustom.getOid());
            orderCustom.setOrderItemList(orderItemList);
        }
        String url = request.getRequestURI() +"?" +request.getQueryString();
        Integer index = url.lastIndexOf("&pc");
        if (index!=-1){url = url.substring(0,index);}
        OrderVo orderVo = new OrderVo();
        orderVo.setPs(ps);
        orderVo.setPc(pc);
        orderVo.setUrl(url);
        orderVo.setTr(tr);
        orderVo.setTp(tp);
        request.setAttribute("pb",orderVo);
        request.setAttribute("orderCustomList",orderCustomList);
        return "forward:/adminjsps/admin/order/list.jsp";
    }
    @RequestMapping("/findOrderByStatus")
    public String findOrderByStatus(HttpServletResponse response,HttpServletRequest request,
        Model model)throws Exception{
        Integer status = Integer.parseInt(request.getParameter("status"));
        Integer pc = getPc(request);
        Integer ps = ConstantUtil.ORDER_AMOUNT_EACH_PAGE;
        Integer tr = orderService.findCountByStatus(status);
        Integer tp = getTp(tr,ps);
        List<OrderCustom> orderCustomList = orderService.findOrderByStatus(pc,ps,status);
        for (OrderCustom orderCustom:orderCustomList){
            List<OrderItem> orderItemList = orderService.findOrderItemsByOid(orderCustom.getOid());
            orderCustom.setOrderItemList(orderItemList);
        }
        String url = request.getRequestURI()+"?" +request.getQueryString();
        Integer index = url.lastIndexOf("&pc");
        if (index!=-1){url = url.substring(0,index);}
        OrderVo orderVo = new OrderVo();
        orderVo.setPs(ps);
        orderVo.setPc(pc);
        orderVo.setUrl(url);
        orderVo.setTp(tp);
        orderVo.setTr(tr);
        request.setAttribute("pb",orderVo);
        request.setAttribute("orderCustomList",orderCustomList);
        return "forward:/adminjsps/admin/order/list.jsp";
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
        return  "forward:/adminjsps/admin/order/desc.jsp";
    }
    @RequestMapping("/cancel")
    public String cancel(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        OrderCustom orderCustomDb = orderService.findOrderByOid(oid);
        if (orderCustomDb.getStatus()!=1){
            request.setAttribute("code","error");
            request.setAttribute("msg","状态错误，不能取消订单");
            return "forward:/adminjsps/msg.jsp";
        }else {
            orderService.updateStatusByOid(oid,5);
            request.setAttribute("code","success");
            request.setAttribute("msg","恭喜您操作成功，订单已取消");
            return "forward:/adminjsps/msg.jsp";
        }
    }
    @RequestMapping("/deliver")
    public String deliver(HttpServletRequest request,HttpServletResponse response,
        Model model)throws Exception{
        String oid = request.getParameter("oid");
        OrderCustom orderCustomDb = orderService.findOrderByOid(oid);
        if (orderCustomDb.getStatus()!=2){
            request.setAttribute("code","error");
            request.setAttribute("msg","状态错误，不能完成发货");
            return "forward:/adminjsps/msg.jsp";
        }else {
            orderService.updateStatusByOid(oid,3);
            request.setAttribute("code","success");
            request.setAttribute("msg","恭喜您操作成功，请查看物流信息确认收货");
            return "forward:/adminjsps/msg.jsp";
        }
    }
}
