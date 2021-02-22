package com.study.majinhu.jdkBase.Enum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 模块类型枚举
 */
public enum ModuleTypeEnums {

    MODULE_TYPE_CAROUSE(1, "基础轮播"),//例：首页顶部轮播
    MODULE_TYPE_TWO_PICS(2, "两图模式"),//左右两图不动
    MODULE_TYPE_THREE_PICS(3, "三图模式"),//例：首页主题特色下部
    MODULE_TYPE_THREE_CAROUSE(4, "三图分组轮播"),//例：首页优惠推荐
    MODULE_TYPE_MORE(5, "带标题多列"),//例：首页逛客怎么说
    MODULE_TYPE_ONE_THREE_CAROUSE(6, "顶部一图下部三图轮播"),//例：特产馆饮品
    MODULE_TYPE_FLOOR(7, "楼层"),
    MODULE_TYPE_FLASH_SALE(8, "限时抢购"),
    MODULE_TYPE_CROWDFUNDING(9, "众筹"),
    MODULE_TYPE_NEW(10, "新品"),
    MODULE_TYPE_ONE_ROW(11, "小图标单行"),//例：特产馆场馆图标
    MODULE_TYPE_TWO_ROW(12, "小图标两行"),//例：首页图标
    MODULE_TYPE_THREE_ROW(13, "小图标三行"),
    MODULE_TYPE_MORE_PICS(14, "最少1图最多4图区"),//例：特产馆头图
    MODULE_TYPE_ONE_PIC_TITLE(15, "单图带标题"),
    MODULE_TYPE_ONE_PIC_TITLES(16, "单图带标题列表"),
    MODULE_TYPE_ONE_PIC(17, "单图"),
    MODULE_TYPE_ONE_MORE_CAROUSE(18, "顶部一图下部多列");//例：特色馆吃食
    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    ModuleTypeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static final Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        for (ModuleTypeEnums type : EnumSet.allOf(ModuleTypeEnums.class)) {
            map.put(type.getCode(), type.getDesc().trim());
        }
    }

    public static String getValue(String code) {
        return map.get(code);
    }

}
