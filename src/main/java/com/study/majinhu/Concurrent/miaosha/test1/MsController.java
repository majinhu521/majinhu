package com.study.majinhu.Concurrent.miaosha.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName MsController
 * @Description
 * @Author majinhu
 * @Date 2019/6/20 16:20
 * @Version 1.0
 **/
public class MsController {
    @Autowired
    private MsService msService;
    @RequestMapping("/select_info.do")
    public String select_info(String product_id) {
        return msService.select_info(product_id);
    }
    @RequestMapping("/order.do")
    public String order(String product_id) throws Exception {
        return msService.order1(product_id);
    }
}

