package com.study.majinhu.jdkBase.Enum;
/**
 * <p>Description: [redis key 值集合 枚举实现接口]</p>
 * Created on 2017年8月15日
 * Copyright (c) 2017 PM
 */
public interface RedisKeysEnums {
	
	public static String COLON = ":";// 冒号
	public static String UNDERSCORE = "_";// 下划线
	public static final long REDIS_CACHE_QUICK_DISCARD = 10;// redis 数据快速丢弃 10秒，单位秒
	public static final long REDIS_CACHE_DEFAULT_MISS_TIME = 86400;// redis 数据默认失效时间 1天，单位秒
	public static final long REDIS_CACHE_TWO_HOUR= 7200;// redis 两小时有效期，适用于列表类数据
	public static final long REDIS_CACHE_HALF_HOUR = 1800; //适用于基于地理位置的优惠券数据
	public static final long REDIS_CACHE_NEVER_EXPIRE = -1;// redis 数据永不过期
	public static final long REDIS_CACHE_FIVE_MIN = 300; //五分钟
	public static final long REDIS_CACHE_DEFAULT_WEEK = 86400 * 7;// redis 数据默认失效时间 1天，单位秒

	/**
	 * <p>Description: [分组定义：用来将不同类别或服务方法的内容分包存储，方便后续分库]</p>
	 * Created on 2017年8月18日
	 * @author  <a href="mailto: liuxiyang45@camelotchina.com">刘喜洋</a>
	 * @version 1.0 
	 * Copyright (c) 2017 PM
	 */
	public enum GROUP {
		SessionService,
		StorePageService,
		StoreService,
		ItemSelectService,
		
		loadItemModel,
		StoreModel,
		BasChangeStockRegionModel,
		SgStoreRead,
		SgCustomerRead,
		SgProductsRead,
		StoreInfoRead,
		SgStoreProductsRead,
		StoreCateBrand,
		CouponList,
		Navigation,
		SgRedisCoupon,
		SgRedisCart,
		SgRedisProductCommon,
		SgRedisOrder,
		SgRedisStore,
		SgRedisStoreIndex,
		FlashSale
	}

	/**
	 * <p>Discription:[需要重写getKey 加入group 和 colon]</p>
	 * Created on 2017年8月18日
	 */
	public String getKey();

}
