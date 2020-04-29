package com.study.majinhu.designModel.proxy.staticproxy.demo1;

/**
 * @ClassName ProxyImage
 * @Description 代理图片类，实现接口类，实际类作为成员。
 * @Author majinhu
 * @Date 2019/6/21 9:25
 * @Version 1.0
 **/
public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}