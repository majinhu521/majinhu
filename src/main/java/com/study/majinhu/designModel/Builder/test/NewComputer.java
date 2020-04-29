package com.study.majinhu.designModel.Builder.test;

/**
 * @ClassName NewComputer
 * @Description
 * @Author majinhu
 * @Date 2020/2/25 15:56
 * @Version 1.0
 **/
public class NewComputer {
    private String cpu;
    private String screen;
    private String memory;
    private String mainboard;
    public NewComputer() {
        throw new RuntimeException("can’t init");
    }
    private NewComputer(Builder builder) {
        cpu = builder.cpu;
        screen = builder.screen;
        memory = builder.memory;
        mainboard = builder.mainboard;
    }
    public static final class Builder {
        private String cpu;
        private String screen;
        private String memory;
        private String mainboard;

        public Builder() {}

        public Builder cpu(String val) {
            cpu = val;
            System.out.println("制作cpu"+val);
            return this;
        }
        public Builder screen(String val) {
            screen = val;
            System.out.println("制作屏幕"+val);
            return this;
        }
        public Builder memory(String val) {
            memory = val;
            System.out.println("制作内存"+val);
            return this;
        }
        public Builder mainboard(String val) {
            mainboard = val;
            System.out.println("制作主板"+val);
            return this;
        }
        public NewComputer build() {
            return new  NewComputer(this);}
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getMainboard() {
        return mainboard;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }
}
