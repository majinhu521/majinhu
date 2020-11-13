package com.study.majinhu.designModel.IteratorPattern;

/**
 * @ClassName NameRepository
 * @Description
 * @Author majinhu
 * @Date 2020/5/18 10:16
 * @Version 1.0
 **/
public class NameRepository implements Container {
    public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
