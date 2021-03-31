package com.study.majinhu.redis.cart;

import com.alipay.api.domain.Product;
import com.study.majinhu.designModel.CommandPattern.Stock;

/**
 * @ClassName Sku
 * @Description Sku
 * @Author majinhu
 * @Date 2021/3/15 10:04
 * @Version 1.0
 **/
public class Sku {
    private Long id;
    private Float price;
    private Product product;
    private Integer stock;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
