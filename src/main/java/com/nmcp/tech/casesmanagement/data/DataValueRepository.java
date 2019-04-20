package com.nmcp.tech.casesmanagement.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Hamza on 2019-02-12.
 */

public interface DataValueRepository extends PagingAndSortingRepository<DataValue, Long> {
    //    Page<Facility> findBy(Pageable page);
    Page<DataValue> findByDataElementCodeAndYearOrderByYear(String dECode, Integer Year, Pageable page);

    Page<DataValue> findByDataElementAndYearAndWeekOrderByYear(String dECode, Integer Year, Integer week, Pageable page);

    Page<DataValue> findByFacilityCodeAndDataElementCodeAndYearOrderByYear(String facilityCode, String dECode,
                                                                           Integer Year, Pageable page);

    Page<DataValue> findByFacilityCodeAndDataElementCodeAndYearAndWeekOrderByYear(String facilityCode,
                                                                                  String dECode, Integer Year,
                                                                                  Integer week, Pageable page);

    //
    //
    @Query("SELECT sum(dv.dataValue) FROM DataValue dv JOIN dv.dataElement d WHERE d.code = ?1 AND dv.year =?2")
//
    long sumByDataElementCodeAndYear(String dECode, Integer Year);

    @Query("SELECT sum(dv.dataValue) FROM DataValue dv JOIN dv.dataElement d WHERE d.code = ?1 AND dv.year =?2 AND dv.week =?3")
//
    long sumByDataElementAndYearAndWeek(String dECode, Integer Year, Integer week);

    @Query("SELECT sum(dv.dataValue) FROM DataValue dv " +
            "JOIN dv.facility f JOIN dv.dataElement d" +
            " WHERE f.code = ?1 AND d.code =?2 AND dv.year =?3")
//
    long sumByFacilityCodeAndDataElementCodeAndYear(String facilityCode, String dECode,
                                                    Integer Year);
//
//    long sumByFacilityCodeAndDataElementCodeAndYearAndWeek (String facilityCode,
//                                                               String dECode, Integer Year,
//                                                               Integer week);

    long countByFacilityCodeAndYear(String facilityCode, Integer year);

    long countByFacilityCodeAndWeek(String facilityCode, Integer week);

}
