package com.study.majinhu.jdkBase.collection.tree;



import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuTree
 * @Description 菜单树
 * @Author majinhu
 * @Date 2022/4/28 15:43
 * @Version 1.0
 **/
public class MenuTree {

    /**
     * 所有的菜单数据
     */
    private List<TreeNode> menuList = new ArrayList<TreeNode>();

    public MenuTree(List<TreeNode> menuList) {
        this.menuList = menuList;
    }

    /**
     * 建立树形结构
     *
     * @return
     */
    public List<TreeNode> buildTree() {
        List<TreeNode> treeMenus = new ArrayList<TreeNode>();
        for (TreeNode menuNode : getRootNode()) {
            menuNode = buildChildTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    /**
     * 递归，建立子树形结构
     *
     * @param pNode
     * @return
     */
    private TreeNode buildChildTree(TreeNode pNode) {
        List<TreeNode> childMenus = new ArrayList<TreeNode>();
        for (TreeNode menuNode : menuList) {
            if (menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode));
            }
        }
        pNode.setChildren(childMenus);
        return pNode;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    private List<TreeNode> getRootNode() {
        List<TreeNode> rootMenuLists = new ArrayList<TreeNode>();
        for (TreeNode menuNode : menuList) {
            if ("0".equals(menuNode.getParentId().toString())) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
    
}