package com.study.majinhu.jdkBase.collection.tree;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName StoreRichTextType
 * @Description
 * @Author majinhu
 * @Date 2022/5/11 17:07
 * @Version 1.0
 **/
@Data
@Builder
public class TreeNode {
    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.Integer parentId;
    private java.util.List<TreeNode> children;
}
