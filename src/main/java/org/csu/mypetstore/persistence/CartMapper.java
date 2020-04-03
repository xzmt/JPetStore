package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.CartDb;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartMapper {
    void addCart(CartDb cartDb);            //把商品添加到购物车数据库

    void removeCart(String userId);         //从数据库清空购物车

    void removeCartByItemId(String userId,String itemId);           //把商品从购物车中删除

    void incrementQuantityByItemId(String userId,String itemId);            /*把购物车中指定商品数量加一*/

    BigDecimal getSubTotal(String userId);

    List<CartDb> getCartList(String userId);

    boolean containsItemId(String userId,String itemId);

    void updateCart(String userId,String itemId,int quantity);
}
