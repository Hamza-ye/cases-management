package com.nmcp.tech.casesmanagement.data.teams;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Hamza on 2019-03-29.
 */

public interface TeamRepository extends TeamRepositoryCustom, JpaRepository<Team, Long> {

    //    Page<Team> findByNameLikeIgnoreCase(String name, Pageable page);
//
//    int countByNameLikeIgnoreCase(String name);
//
    Optional<Team> findByCode(String code);
//    Optional<Team> findByIdString(String uid);
//    Page<Team> findBy(Pageable page);
}