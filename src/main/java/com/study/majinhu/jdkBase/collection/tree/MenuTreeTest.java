package com.study.majinhu.jdkBase.collection.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MenuTreeTest
 * @Description
 * @Author majinhu
 * @Date 2022/5/11 17:11
 * @Version 1.0
 **/
public class MenuTreeTest {
    public static void main(String[] args){
        List<TreeNode> menuList = new ArrayList<>();
        List<TreeNode> datalist = new ArrayList<>();
        datalist.add(new TreeNode(1,"一级",0,null));
        datalist.add(new TreeNode(2,"一级",0,null));
        datalist.add(new TreeNode(3,"一级",1,null));
        datalist.add(new TreeNode(4,"一级",2,null));
        //方法1
//        MenuTree menuTree = new MenuTree(datalist);
//        menuList = menuTree.buildTree();
//        System.out.println(menuList);
        //方法2
        List<TreeNode> treeNodes = StreamToTree(datalist, 0);
        System.out.println(treeNodes);
    }

    /**
     * https://blog.csdn.net/qq_37700773/article/details/115087214
     * @param treeList
     * @param parentId
     * @return
     */
    public static List<TreeNode> StreamToTree(List<TreeNode> treeList, Integer parentId) {
        List<TreeNode> list = treeList.stream().filter(parent -> parent.getParentId().equals(parentId))
                .map(child -> {
                    child.setChildren(StreamToTree(treeList, child.getId()));
                    return child;
                }).collect(Collectors.toList());
        return list;
    }
}
