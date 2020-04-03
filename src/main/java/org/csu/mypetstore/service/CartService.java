package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartDb;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

      @Autowired
      public CartMapper cartMapper;
      @Autowired
      public CatalogService catalogService;


//    //查购物车询
    public List<CartDb> getCartList(String userId)
    {
        return cartMapper.getCartList(userId);
    }
//
//    //删除购物车
    public void deleteCart(String userId,String itemId)
    {
        cartMapper.removeCartByItemId(userId,itemId);

    }
//
//    //向购物车中增加商品
    public void addCart(String userId,String itemId)
    {
        //如果购物车中有要加入的商品，则数量增加1
        if(cartMapper.containsItemId(userId,itemId))
        {
            cartMapper.incrementQuantityByItemId(userId,itemId);
        }
        else
        {
            //如果购物车中没有要加入的商品，则在购物车中新增商品信息
            // 加入前首先要判断是否有库存
            boolean isInStock = catalogService.isItemInStock(itemId);
            //根据itemId获取商品信息，并加入到cartDb
            Item item = catalogService.getItem(itemId);
            CartDb cartDb=new CartDb();
            cartDb.setUserId(userId);
            cartDb.setItemId(itemId);
            cartDb.setProductId(item.getProductId());
            cartDb.setName(item.getProduct().getName());
            cartDb.setInStock(isInStock);
            cartDb.setQuantity(1);
            cartDb.setListPrice(item.getListPrice());
            cartDb.setTotal(item.getListPrice());

            //加入购物车
            cartMapper.addCart(cartDb);
        }
    }

//    //更新购物车
    public void  updateCart(String userId,String itemId,int quantity)
    {
        cartMapper.updateCart(userId,itemId,quantity);
    }
}
