package com.nmcp.tech.casesmanagement.data.orgunits;

import com.nmcp.tech.casesmanagement.data.common.DbSessionJpaImpl;
import com.nmcp.tech.casesmanagement.data.teams.Team;
import com.nmcp.tech.casesmanagement.data.teams.TeamRepositoryCustomImpl;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeDao;
import fri.util.database.jpa.tree.nestedsets.uniqueconstraints.UniqueWholeTreeConstraintImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Hamza on 2019-04-01.
 */
@Configuration
@ComponentScan(basePackageClasses = {OrgunitRepositoryCustomImpl.class, TeamRepositoryCustomImpl.class})
public class OrgunitsDataConfig {

    //    @Bean
//    @PersistenceContext
//    public DbSession getDbSession(EntityManager entityManager) {
//        return new DbSessionJpaImpl(entityManager);
//    }
//
    @Bean
    @PersistenceContext
    public NestedSetsTreeDao getOrgUnitDao(EntityManager entityManager) {
        NestedSetsTreeDao orgUnitDao = new NestedSetsTreeDao(Orgunit.class, new DbSessionJpaImpl(entityManager));
        orgUnitDao.setUniqueTreeConstraint(new UniqueWholeTreeConstraintImpl(
                new String[][]{{"code"}},
                false));
        return orgUnitDao;
    }

    @Bean
    @PersistenceContext
    public NestedSetsTreeDao getTeamDao(EntityManager entityManager) {
        NestedSetsTreeDao teamDao = new NestedSetsTreeDao(Team.class, new DbSessionJpaImpl(entityManager));
        teamDao.setUniqueTreeConstraint(new UniqueWholeTreeConstraintImpl(
                new String[][]{{"code"}},
                false));
        return teamDao;
    }

}
