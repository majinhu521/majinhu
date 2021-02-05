package com.study.majinhu.transactianal;

import org.springframework.stereotype.Component;

/**
 * @ClassName OperationLogMapper
 * @Description
 * @Author majinhu
 * @Date 2020/3/5 15:56
 * @Version 1.0
 **/
@Component
public class OperationLogMapper {
    public void insert(OperationLog record){
        System.out.println("插入一条日志记录");
    }
}
