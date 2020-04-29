package com.study.majinhu.designModel.Builder.test;

/**
 * @ClassName ClientTest
 * @Description https://www.jianshu.com/p/3d1c9ffb0a28
 * @Author majinhu
 * @Date 2020/2/25 15:57
 * @Version 1.0
 **/
public class ClientTest {
    public static void main(String[] args) {
        // 非 Builder 模式
        Computer computer;

        computer = new Computer("cpu", "screen", "memory", "mainboard");
        // Builder 模式
        NewComputer newComputer = new NewComputer.Builder()
                .cpu("cpu")
                .screen("screen")
                .memory("memory")
                .mainboard("mainboard")
                .build();
    }
}
