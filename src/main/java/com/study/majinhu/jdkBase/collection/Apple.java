package com.study.majinhu.jdkBase.collection;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName Apple
 * @Description  Apple 为列，作为 stream 测试
 * @Author majinhu
 * @Date 2021/11/13 9:12
 * @Version 1.0
 **/
@Data
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;
    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    public static void main(String[] args){
        //添加一些测试数据：
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

//        1、分组
//        List里面的对象元素，以某个属性来分组，例如，以id分组，将id相同的放在一起：
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));

        System.err.println("groupBy:"+groupBy);

//        2、List转Map
//        id为key，apple对象为value，可以这么做：
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         *  a -> a 所有对象放入map的value值，单个可以用 Apple::getName
         *  Function.identity() 替换   a -> a
        **/
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k1));
        System.err.println("List转Map:"+appleMap);

        Map<Integer, Apple> appleMap2 = appleList.stream().collect(Collectors.toMap(Apple::getId, Function.identity(),(k1, k2)->k1));
        System.err.println("List转Map2:"+appleMap2);

        Map<Integer, String> appleMap3 = appleList.stream().collect(Collectors.toMap(Apple::getId, Apple::getName,(k1,k2)->k1));
        System.err.println("List转Map3:"+appleMap3);
        //toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：
        Map<Integer, Apple> appleMap4 = appleList.stream().collect(Collectors.toMap(Apple::getId, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
        System.err.println("List转Map4:"+appleMap4);
        //这里只是简单的使用后者覆盖前者来解决key重复问题。还有一种分组的方法,分组后不覆盖：
        Map<Integer, List<Apple>> appleMap5 = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.err.println("List转Map5:"+appleMap5);

//        3、过滤Filter 过滤出符合条件的数据
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
        System.err.println("filterList:"+filterList);

        //根据某一个值进行过滤。过滤完毕是否存在。不存在返回null
        Apple apple = appleList.stream().filter(a -> a.getName().equals("香蕉")).findAny().orElse(null);
        if(apple != null){
            System.err.println("findAny:"+apple);
        }
//        4.求和
//        将集合中的数据按照某个属性求和:
//         计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:"+totalMoney);

//        5.查找流中最大 最小值
//        Collectors.maxBy 和 Collectors.minBy 来计算流中的最大或最小值。
        Optional<Apple> maxDish =appleList.stream().
                collect(Collectors.maxBy(Comparator.comparing(Apple::getMoney)));
//        maxDish.ifPresent(System.out::println);
//        if(maxDish.isPresent()){
//            System.out.println(maxDish.get());
//        }
        System.out.println(maxDish.get());

        Optional<Apple> minDish =appleList.stream().
                collect(Collectors.minBy(Comparator.comparing(Apple::getMoney)));
        minDish.ifPresent(System.out::println);

        // 根据id去重
        List<Apple> unique = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))), ArrayList::new ));
        System.err.println("unique:"+unique);

        //下表展示 Collectors 类的静态工厂方法。
        //工厂方法	返回类型	作用
        //toList	List<T>	把流中所有项目收集到一个 List
        //toSet	Set<T>	把流中所有项目收集到一个 Set，删除重复项
        //toCollection	Collection<T>	把流中所有项目收集到给定的供应源创建的集合menuStream.collect(toCollection(), ArrayList::new)
        //counting	Long	计算流中元素的个数
        //sumInt	Integer	对流中项目的一个整数属性求和
        //averagingInt	Double	计算流中项目 Integer 属性的平均值
        //summarizingInt	IntSummaryStatistics	收集关于流中项目 Integer 属性的统计值，例如最大、最小、 总和与平均值
        //joining	String	连接对流中每个项目调用 toString 方法所生成的字符串collect(joining(", "))
        //maxBy	Optional<T>	一个包裹了流中按照给定比较器选出的最大元素的 Optional， 或如果流为空则为 Optional.empty()
        //minBy	Optional<T>	一个包裹了流中按照给定比较器选出的最小元素的 Optional， 或如果流为空则为 Optional.empty()
        //reducing	归约操作产生的类型	从一个作为累加器的初始值开始，利用 BinaryOperator 与流 中的元素逐个结合，从而将流归约为单个值累加int totalCalories = menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
        //collectingAndThen	转换函数返回的类型	包裹另一个收集器，对其结果应用转换函数int howManyDishes = menuStream.collect(collectingAndThen(toList(), List::size))
        //groupingBy	Map<K, List<T>>	根据项目的一个属性的值对流中的项目作问组，并将属性值作 为结果 Map 的键
        //partitioningBy	Map<Boolean,List<T>>	根据对流中每个项目应用谓词的结果来对项目进行分区
        //原文链接：https://blog.csdn.net/lu930124/article/details/77595585
    }

}
