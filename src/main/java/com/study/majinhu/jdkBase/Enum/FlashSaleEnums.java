package com.study.majinhu.jdkBase.Enum;

public enum FlashSaleEnums implements RedisKeysEnums {

    /**
     *
     */
    InitialStock(1, "InitialStock", "初始库存"),
    SurplusStock(2, "SurplusStock", "剩余库存"),
    Sold(3, "Sold", "已经售出的");

    // 成员变量
    private long numId; // 唯一标识ID
 	private String key; // redis key 前缀
 	private String desc; // 描述

 	// 构造方法
 	private FlashSaleEnums(long numId, String key, String desc) {
 		this.numId = numId;
 		this.key = key;
 		this.desc = desc;
 	}

	@Override
	public String getKey() {
 	    //FlashSale:InitialStock_
		return RedisKeysEnums.GROUP.FlashSale + COLON + key + UNDERSCORE;
	}

 	public long getNumId() {
 		return numId;
 	}
 	public void setNumId(long numId) {
 		this.numId = numId;
 	}
 	public String getDesc() {
 		return desc;
 	}
 	public void setDesc(String desc) {
 		this.desc = desc;
 	}
 	public void setKey(String key) {
 		this.key = key;
 	}

}
