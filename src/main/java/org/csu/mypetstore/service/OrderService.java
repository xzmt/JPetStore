package org.csu.mypetstore.service;


import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private CartMapper cartMapper;

    public Order insertOrder(Order order)
    {
        int orderId = getNextId("ordernum");        //获取序列表sequence中名称为"ordernum"的id序列值
        order.setOrderId(orderId);                         //设置order订单的orderId信息
        order.setTotalPrice(cartMapper.getSubTotal(order.getUsername()));          //设置order订单的总金额
        List<CartDb> cartDbList=cartMapper.getCartList(order.getUsername());
        List<LineItem> lineItemList=new ArrayList<>();

        //更新购物车中的商品对应的库存数量
        for (int i = 0; i < cartDbList.size() ; i++)
        {
            //新增订单order中的lineitems信息，并储存到数据库lineitem表中
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setLineNumber(i);
            lineItem.setItemId(cartDbList.get(i).getItemId());
            lineItem.setQuantity(cartDbList.get(i).getQuantity());
            lineItem.setUnitPrice(cartDbList.get(i).getListPrice());
            lineItem.setTotal(cartDbList.get(i).getTotal());
            lineItem.setName(cartDbList.get(i).getName());
            lineItemList.add(lineItem);
            lineItemMapper.insertLineItem(lineItem);

            //更新购物车中的商品对应的库存数量
            String itemId = cartDbList.get(i).getItemId();
            Integer increment = Integer.valueOf(cartDbList.get(i).getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
//            itemDAO.updateInventoryQuantity(param);
        }
        //将商品信息加入到订单中
        order.setLineItems(lineItemList);

        //执行新增订单和新增订单状态
        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);
        //清空购物车
        cartMapper.removeCart(order.getUsername());
        //将带有商品信息的订单结果返回
        return order;
    }

    //通过orderId查询此项订单信息
    public Order getOrder(int orderId)
    {
        //查询订单信息
        Order order = orderMapper.getOrder(orderId);
        //查询订单中的商品信息
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
        return order;
    }

    //查询某一用户的所有订单列表信息
    public List<Order> getOrdersByUsername(String username)
    {
        //return orderMapper.getOrdersByUsername(username);
        return orderMapper.getOrdersByUsername(username);
    }

    //获取序列表sequence中新的id序列值
    public int getNextId(String name)
    {
        Sequence sequence = new Sequence(name, -1);
        sequence = sequenceMapper.getSequence(sequence);
        if (sequence == null)
        {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceMapper.updateSequence(parameterObject);
        return sequence.getNextId();
    }

    /*获取所有的订单*/
    public List<Order> getAllOrder()
    {
        return orderMapper.getAllOrder();
    }

    public void updateOrder(Order order)
    {
        orderMapper.updateOrder(order);
        orderMapper.updateOrderStatus(order);
    }

    public void deleteOrder(int orderId)
    {
        orderMapper.deleteOrder(orderId);
    }

    public void changeOrderStatus(int orderId,String statusName)
    {
        Order order = getOrder(orderId);
        order.setStatus(statusName);
        updateOrder(order);
    }
}
