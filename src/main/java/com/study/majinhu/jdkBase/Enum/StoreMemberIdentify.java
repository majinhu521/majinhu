package com.study.majinhu.jdkBase.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 微店主身份常量类
 */
public class StoreMemberIdentify {
    /**
     * 直营店（旗舰店）
     */
    public static final Integer FLAGSHIP = 11;
    /**
     * 伞下店(非旗舰店、乡镇店)
     */
    public static final Integer TOWNSHIP = 12;
    /**
     * 售后店
     */
    public static final Integer SALE = 13;
    /**
     * 社会化生态店
     */
    public static final Integer SOCIETY = 14;
    /**
     * 品类店
     */
    public static final Integer PRODUCT = 15;
    /**
     * 品旗店
     */
    public static final Integer BRAND = 16;
    /**
     * 001号店
     */
    public static final Integer ZERO = 17;
    /**
     * 直营店合伙人
     */
    public static final Integer FLAGSHIP_PARTNER = 21;
    /**
     * 伞下店合伙人
     */
    public static final Integer TOWNSHIP_PARTNER = 22;
    /**
     * 售后店合伙人
     */
    public static final Integer SALE_PARTNER = 23;
    /**
     * 社会化生态店合伙人
     */
    public static final Integer SOCIETY_PARTNER = 24;
    /**
     * 储备微店主
     */
    public static final Integer RESERVE = 0;

    public static List<Integer> storeIdentify = new ArrayList<Integer>();

    static {
        storeIdentify.add(FLAGSHIP);
        storeIdentify.add(TOWNSHIP);
        storeIdentify.add(SALE);
        storeIdentify.add(SOCIETY);
        storeIdentify.add(PRODUCT);
        storeIdentify.add(BRAND);
        storeIdentify.add(ZERO);
    }

    /**
     * 是否店铺身份
     * @param identify
     * @return
     */
    public static Boolean isStore(Integer identify) {
        if (storeIdentify.contains(identify)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
