package com.nmcp.tech.casesmanagement.api;

import com.nmcp.tech.casesmanagement.data.DataValue;
import com.nmcp.tech.casesmanagement.data.DataValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hamza on 2019-02-13.
 */

@RestController
@RequestMapping(path = "/dataValues", produces = "application/json")
@CrossOrigin(origins = "*")
public class DataValueRestController {
    DataValueRepository dataValueRepository;
//    Integer pageSize = 20;

    @Autowired
    public DataValueRestController(DataValueRepository dataValueRepository) {
        this.dataValueRepository = dataValueRepository;
    }

    @GetMapping(produces = "application/json")
    public Iterable<DataValue> getDataValues() {
        PageRequest page = PageRequest.of(
                0, 20, Sort.by("createdAt").ascending());
        return dataValueRepository.findAll(page);
    }

//    @GetMapping(path="/sum", produces="application/json")
//    public long sumDataValues(@RequestParam(name = "dataelement")String de,
//                             @RequestParam(name = "year") int year){
//
//        return dataValueRepository.sumByDataElementCodeAndYear(de, year);
//    }

    @GetMapping(path = "/sum", produces = "application/json")
    public long sumDataValues(@RequestParam(name = "facility", required = false) String facilityCode,
                              @RequestParam(name = "dataelement", required = false) String de,
                              @RequestParam(name = "year", required = false) Integer year,
                              @RequestParam(name = "week", required = false) Integer week) {
//        DbSession dbsession = new DbSession(session);
////        return dataValueRepository.sumByDataElementAndYearAndWeek(de,year,week);
//        if (facilityCode == null){
//            if (de != null)
//                return dataValueRepository.sumByDataElementAndYearAndWeek();
//        }
//            return dataValueRepository.sumByDataElementAndYearAndWeek()
        return dataValueRepository.sumByFacilityCodeAndDataElementCodeAndYear(facilityCode, de, year);
    }

//    @GetMapping(produces="application/json")
//    public Iterable<DataElement> getValueByFacilityAndWeek(){
//        PageRequest page = PageRequest.of(
//                0, pageSize, Sort.by("createdAt").ascending());
//        return dataValueRepository.findAll(page);
//    }


//    @GetMapping(path="/dataValues")

}
