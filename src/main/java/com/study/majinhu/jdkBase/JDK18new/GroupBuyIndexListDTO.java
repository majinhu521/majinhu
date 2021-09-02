package com.study.majinhu.jdkBase.JDK18new;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动商品表
 */
@Data
public class GroupBuyIndexListDTO implements Comparable<GroupBuyIndexListDTO>, Serializable {

    /**
     * 商品表id
     */
    private Integer id;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 剩余时间
     */
    private Long leftTime;

    private Integer storeId;

    private String storeName;
    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 物料编号，商品sku
     */
    private String sku;

    /**
     * 拼团价格
     */
    private BigDecimal price;

    /**
     * 成团人数
     */
    private Integer clustersNum;

    /**
     * 商品主图
     */
    private String picture;

    /**
     * 销量 -默认0
     */
    private Integer saleNum;



    @Override
    public int compareTo(GroupBuyIndexListDTO ob) {
        return saleNum.compareTo(ob.getSaleNum());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final GroupBuyIndexListDTO gbl = (GroupBuyIndexListDTO) obj;
        if (this == gbl) {
            return true;
        } else {
            return this.saleNum.equals(gbl.saleNum);
        }
    }

    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (saleNum == null ? 0 : saleNum.hashCode());
        return hashno;
    }



}
