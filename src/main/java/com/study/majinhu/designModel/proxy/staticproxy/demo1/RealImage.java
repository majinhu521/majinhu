package com.study.majinhu.designModel.proxy.staticproxy.demo1;

/**
 * @ClassName RealImage
 * @Description
 * @Author majinhu
 * @Date 2019/6/21 9:23
 * @Version 1.0
 **/
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}