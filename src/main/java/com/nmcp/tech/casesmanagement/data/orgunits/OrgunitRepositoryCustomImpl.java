package com.nmcp.tech.casesmanagement.data.orgunits;

import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeDao;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import fri.util.database.jpa.tree.uniqueconstraints.UniqueConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Hamza on 2019-03-29.
 */
@Repository
//@Transactional(readOnly = true)
public class OrgunitRepositoryCustomImpl implements OrgunitRepositoryCustom {

    private NestedSetsTreeDao orgUnitDao;


    public NestedSetsTreeDao getOrgUnitDao() {
        return orgUnitDao;
    }

    @Autowired
    public void setOrgUnitDao(@Qualifier("getOrgUnitDao") NestedSetsTreeDao orgUnitDao) {
        this.orgUnitDao = orgUnitDao;
    }

//    @PersistenceContext
//    public void setOrgUnitDao(EntityManager em) {
//        orgUnitDao = new NestedSetsTreeDao(Orgunit.class, new DbSessionJpaImpl(em));
//        orgUnitDao.setUniqueTreeConstraint(new UniqueWholeTreeConstraintImpl(
//                new String[][]{{"code"}},
//                false));
//    }

    @Override
    public NestedSetsTreeNode find(Serializable id) {
        return orgUnitDao.find(id);
    }

    @Transactional
    @Override
    public void update(NestedSetsTreeNode node) throws UniqueConstraintViolationException {
        orgUnitDao.update(node);
    }

    @Override
    public final boolean isRoot(NestedSetsTreeNode node) {
        return orgUnitDao.isRoot(node);
    }


    @Transactional
    @Override
    public NestedSetsTreeNode createRoot(NestedSetsTreeNode root) throws UniqueConstraintViolationException {
        return orgUnitDao.createRoot(root);
    }

    public List<NestedSetsTreeNode> getRoots() {
        return orgUnitDao.getRoots();
    }

    @Transactional
    @Override
    public synchronized void removeAll() {
        orgUnitDao.removeAll();
    }

    @Override
    public List<NestedSetsTreeNode> getTree(NestedSetsTreeNode parent) {
        return orgUnitDao.getTree(parent);
    }

    @Override
    public List<NestedSetsTreeNode> getTreeCacheable(NestedSetsTreeNode parent) {
        return orgUnitDao.getTreeCacheable(parent);
    }

    @Override
    public List<NestedSetsTreeNode> findSubTree(NestedSetsTreeNode parent, List<NestedSetsTreeNode> subNodes) {
        return orgUnitDao.findSubTree(parent, subNodes);
    }

    @Override
    public List<NestedSetsTreeNode> findDirectChildren(List<NestedSetsTreeNode> subNodes) {
        return orgUnitDao.findDirectChildren(subNodes);
    }

    @Override
    public int getChildCount(NestedSetsTreeNode parent) {
        return orgUnitDao.getChildCount(parent);
    }

    @Override
    public List<NestedSetsTreeNode> getChildren(NestedSetsTreeNode parent) {
        return orgUnitDao.getChildren(parent);
    }

    @Override
    public NestedSetsTreeNode getRoot(NestedSetsTreeNode node) {
        return orgUnitDao.getRoot(node);
    }

    @Override
    public NestedSetsTreeNode getParent(NestedSetsTreeNode child) {
        return orgUnitDao.getParent(child);
    }

    @Override
    public List<NestedSetsTreeNode> getPath(NestedSetsTreeNode node) {
        return orgUnitDao.getPath(node);
    }

    @Override
    public int getLevel(NestedSetsTreeNode node) {
        return orgUnitDao.getLevel(node);
    }

    @Override
    public int size(NestedSetsTreeNode parent) {
        return orgUnitDao.size(parent);
    }

    @Override
    public boolean isLeaf(NestedSetsTreeNode node) {
        return orgUnitDao.isLeaf(node);
    }

    @Override
    public boolean isEqualToOrChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent) {
        return orgUnitDao.isEqualToOrChildOf(child, parent);
    }

    @Override
    public boolean isChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent) {
        return orgUnitDao.isChildOf(child, parent);
    }


    @Override
    public List<NestedSetsTreeNode> find(NestedSetsTreeNode parent, Map<String, Object> criteria) {
        return orgUnitDao.find(parent, criteria);
    }

    @Transactional
    @Override
    public NestedSetsTreeNode addChild(NestedSetsTreeNode parent, NestedSetsTreeNode child) throws UniqueConstraintViolationException {
        return orgUnitDao.addChild(parent, child);
    }
}
