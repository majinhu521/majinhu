package com.study.majinhu.aop;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @ClassName SpelParer
 * @Description
 * @Author majinhu
 * @Date 2019/6/26 14:32
 * @Version 1.0
 **/
public class SpelParer {
    public static String getkey(String key, String[] parameterName, Object[] args){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(key);
        EvaluationContext contex = new StandardEvaluationContext();
        if(args.length<=0){
            return  null;
        }
        for (int i = 0; i < args.length; i++) {
            contex.setVariable(parameterName[i],args[i]);
        }
        return  exp.getValue(contex,String.class);

    }

    public static void main(String[] args) {
        String Key = "#LISON+' '+#JAMES";
        String name1 ="LISON";
        String name2 ="JAMES";
        String[] names = new String[]{name1,name2};
        Object[] obj = new Object[2];
        obj[0] = "LISON1";
        obj[1] = "JAMES1";
        System.out.println(SpelParer.getkey(Key,names,obj));


    }
}
