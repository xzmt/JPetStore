package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

    List<Order> getAllOrder();

    void deleteOrder(int orderId);

    void updateOrder(Order order);

    void updateOrderStatus(Order order);

    List<Order> searchOrder(String keywords);
}
