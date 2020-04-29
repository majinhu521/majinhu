package com.study.majinhu.designModel.FilterPattern;

import java.util.List;

/**
 * @ClassName Criteria
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:45
 * @Version 1.0
 **/
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}