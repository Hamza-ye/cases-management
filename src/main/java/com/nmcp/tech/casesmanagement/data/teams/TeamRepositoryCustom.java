package com.nmcp.tech.casesmanagement.data.teams;


import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeDao;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import fri.util.database.jpa.tree.uniqueconstraints.UniqueConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface TeamRepositoryCustom {
    NestedSetsTreeDao getTeamDao();

    boolean isPersistent(NestedSetsTreeNode nestedSetsTreeNode);

    NestedSetsTreeNode find(java.io.Serializable id);

    NestedSetsTreeNode createRoot(NestedSetsTreeNode root) throws UniqueConstraintViolationException;

    NestedSetsTreeNode addChild(NestedSetsTreeNode parent, NestedSetsTreeNode child) throws UniqueConstraintViolationException;

    void update(NestedSetsTreeNode node) throws UniqueConstraintViolationException;

    NestedSetsTreeNode getRoot(NestedSetsTreeNode node);

    NestedSetsTreeNode getParent(NestedSetsTreeNode child);

    List<NestedSetsTreeNode> getRoots();

    List<NestedSetsTreeNode> getChildren(NestedSetsTreeNode parent);

    int getChildCount(NestedSetsTreeNode parent);

    int getLevel(NestedSetsTreeNode node);

    List<NestedSetsTreeNode> getPath(NestedSetsTreeNode node);

    List<NestedSetsTreeNode> getTree(NestedSetsTreeNode parent);

    List<NestedSetsTreeNode> getTreeCacheable(NestedSetsTreeNode parent);

    List<NestedSetsTreeNode> findSubTree(NestedSetsTreeNode parent, List<NestedSetsTreeNode> subNodes);

    List<NestedSetsTreeNode> findDirectChildren(List<NestedSetsTreeNode> subNodes);

    int size(NestedSetsTreeNode parent);

    boolean isRoot(NestedSetsTreeNode node);

    boolean isLeaf(NestedSetsTreeNode node);

    boolean isEqualToOrChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent);

    boolean isChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent);

    List<NestedSetsTreeNode> find(NestedSetsTreeNode parent, Map<String, Object> criteria);

    void removeAll();
}
