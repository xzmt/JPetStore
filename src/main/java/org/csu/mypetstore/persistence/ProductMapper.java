package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    List<Product> searchProductList(String keywords);

    List<String> searchAllProductList(String username);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String  productId);
}
