package com.study.majinhu.designModel.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CriteriaFemale
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:46
 * @Version 1.0
 **/
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
