package com.study.majinhu.designModel.FilterPattern;

import java.util.List;

/**
 * @ClassName AndCriteria
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:47
 * @Version 1.0
 **/
public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}