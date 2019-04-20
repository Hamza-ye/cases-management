package com.nmcp.tech.casesmanagement.data.teams;

import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeDao;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import fri.util.database.jpa.tree.uniqueconstraints.UniqueConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {
    private static Logger logger = LoggerFactory.getLogger(TeamRepositoryCustomImpl.class);
    //    protected ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
//    protected Class<N> entityClass = (Class<N>) genericSuperclass.getActualTypeArguments()[0].getClass();
    private NestedSetsTreeDao teamDao;

    public NestedSetsTreeDao getTeamDao() {
        return teamDao;
    }

    @Autowired
    public void setTeamDao(@Qualifier("getTeamDao") NestedSetsTreeDao teamDao) {
        this.teamDao = teamDao;
    }

//    @PersistenceContext
//    public void setteamDao(EntityManager em) {
//
////        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
////        logger.info("genericSuperclass.typeName is {}", genericSuperclass.getTypeName());
////        logger.info("getGenericInterfaces()[0] is {}", entityClass.getGenericInterfaces()[0]); //getGenericSuperclass()
////        logger.info("getGenericSuperclass().typeName  is {}", entityClass.getGenericSuperclass());
////        logger.info("entityClass is {}", entityClass.getSimpleName());
//        this.teamDao = new NestedSetsTreeDao(Team.class, new DbSessionJpaImpl(em)); //genericSuperclass.getActualTypeArguments()[1]
//        this.teamDao.setUniqueTreeConstraint(new UniqueWholeTreeConstraintImpl(
//                new String[][]{{"code"}},
//                false));
//    }

    @Override
    public boolean isPersistent(NestedSetsTreeNode nestedSetsTreeNode) {
        return teamDao.isPersistent(nestedSetsTreeNode);
    }

    @Override
    public NestedSetsTreeNode find(Serializable id) {
        return teamDao.find(id);
    }

    @Transactional
    @Override
    public void update(NestedSetsTreeNode node) throws UniqueConstraintViolationException {
        teamDao.update(node);
    }

    @Override
    public final boolean isRoot(NestedSetsTreeNode node) {
        return teamDao.isRoot(node);
    }


    @Transactional
    @Override
    public NestedSetsTreeNode createRoot(NestedSetsTreeNode root) throws UniqueConstraintViolationException {
        return teamDao.createRoot(root);
    }

    public List<NestedSetsTreeNode> getRoots() {
        return teamDao.getRoots();
    }

    @Transactional
    @Override
    public synchronized void removeAll() {
        teamDao.removeAll();
    }

    @Override
    public List<NestedSetsTreeNode> getTree(NestedSetsTreeNode parent) {
        return teamDao.getTree(parent);
    }

    @Override
    public List<NestedSetsTreeNode> getTreeCacheable(NestedSetsTreeNode parent) {
        return teamDao.getTreeCacheable(parent);
    }

    @Override
    public List<NestedSetsTreeNode> findSubTree(NestedSetsTreeNode parent, List<NestedSetsTreeNode> subNodes) {
        return teamDao.findSubTree(parent, subNodes);
    }

    @Override
    public List<NestedSetsTreeNode> findDirectChildren(List<NestedSetsTreeNode> subNodes) {
        return teamDao.findDirectChildren(subNodes);
    }

    @Override
    public int getChildCount(NestedSetsTreeNode parent) {
        return teamDao.getChildCount(parent);
    }

    @Override
    public List<NestedSetsTreeNode> getChildren(NestedSetsTreeNode parent) {
        return teamDao.getChildren(parent);
    }

    @Override
    public NestedSetsTreeNode getRoot(NestedSetsTreeNode node) {
        return teamDao.getRoot(node);
    }

    @Override
    public NestedSetsTreeNode getParent(NestedSetsTreeNode child) {
        return teamDao.getParent(child);
    }

    @Override
    public List<NestedSetsTreeNode> getPath(NestedSetsTreeNode node) {
        return teamDao.getPath(node);
    }

    @Override
    public int getLevel(NestedSetsTreeNode node) {
        return teamDao.getLevel(node);
    }

    @Override
    public int size(NestedSetsTreeNode parent) {
        return teamDao.size(parent);
    }

    @Override
    public boolean isLeaf(NestedSetsTreeNode node) {
        return teamDao.isLeaf(node);
    }

    @Override
    public boolean isEqualToOrChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent) {
        return teamDao.isEqualToOrChildOf(child, parent);
    }

    @Override
    public boolean isChildOf(NestedSetsTreeNode child, NestedSetsTreeNode parent) {
        return teamDao.isChildOf(child, parent);
    }


    @Override
    public List<NestedSetsTreeNode> find(NestedSetsTreeNode parent, Map<String, Object> criteria) {
        return teamDao.find(parent, criteria);
    }


    @Transactional
    @Override
    public NestedSetsTreeNode addChild(NestedSetsTreeNode parent, NestedSetsTreeNode child) throws UniqueConstraintViolationException {
        return teamDao.addChild(parent, child);
    }


//    @Override
//    public void setUniqueTreeConstraint(UniqueTreeConstraint<NestedSetsTreeNode> uniqueTreeConstraint) {
//        teamDao.setUniqueTreeConstraint(uniqueTreeConstraint);
//    }
//
//    @Override
//    public void setCheckUniqueConstraintOnUpdate(boolean b) {
//        teamDao.setCheckUniqueConstraintOnUpdate(b);
//    }
//
//    @Override
//    public void checkUniqueConstraint(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, NestedSetsTreeNode n2) throws UniqueConstraintViolationException {
//        teamDao.checkUniqueConstraint(nestedSetsTreeNode, n1, n2);
//    }

//    @Override
//    public NestedSetsTreeNode addChildAt(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, int i) throws UniqueConstraintViolationException {
//        return teamDao.addChildAt(nestedSetsTreeNode, n1, i);
//    }
//
//    @Override
//    public NestedSetsTreeNode addChildBefore(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1) throws UniqueConstraintViolationException {
//        return teamDao.addChildBefore(nestedSetsTreeNode, n1);
//    }
//
//    @Override
//    public void remove(NestedSetsTreeNode nestedSetsTreeNode) {
//        teamDao.remove(nestedSetsTreeNode);
//    }
//
//    @Override
//    public void move(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1) throws UniqueConstraintViolationException {
//        teamDao.move(nestedSetsTreeNode, n1);
//    }
//
//    @Override
//    public void moveTo(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, int i) throws UniqueConstraintViolationException {
//        teamDao.moveTo(nestedSetsTreeNode, n1, i);
//    }
//
//    @Override
//    public void moveBefore(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1) throws UniqueConstraintViolationException {
//        teamDao.moveBefore(nestedSetsTreeNode, n1);
//    }
//
//    @Override
//    public void moveToBeRoot(NestedSetsTreeNode nestedSetsTreeNode) throws UniqueConstraintViolationException {
//        teamDao.moveToBeRoot(nestedSetsTreeNode);
//    }
//
//    @Override
//    public NestedSetsTreeNode copy(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, NestedSetsTreeNode n2) throws UniqueConstraintViolationException {
//        return teamDao.copy(nestedSetsTreeNode, n1, n2);
//    }
//
//    @Override
//    public NestedSetsTreeNode copyTo(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, int i, NestedSetsTreeNode n2) throws UniqueConstraintViolationException {
//        return teamDao.copyTo(nestedSetsTreeNode, n1, i, n2);
//    }
//
//    @Override
//    public NestedSetsTreeNode copyBefore(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1, NestedSetsTreeNode n2) throws UniqueConstraintViolationException {
//        return teamDao.copyBefore(nestedSetsTreeNode, n1, n2);
//    }
//
//    @Override
//    public NestedSetsTreeNode copyToBeRoot(NestedSetsTreeNode nestedSetsTreeNode, NestedSetsTreeNode n1) throws UniqueConstraintViolationException {
//        return teamDao.copyToBeRoot(nestedSetsTreeNode, n1);
//    }
//
//    @Override
//    public void setCopiedNodeRenamer(CopiedNodeRenamer<NestedSetsTreeNode> copiedNodeRenamer) {
//        teamDao.setCopiedNodeRenamer(copiedNodeRenamer);
//    }
}
