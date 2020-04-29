package com.study.majinhu.designModel.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CriteriaMale
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 16:46
 * @Version 1.0
 **/
public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
