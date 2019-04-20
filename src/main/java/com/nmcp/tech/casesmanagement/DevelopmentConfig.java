package com.nmcp.tech.casesmanagement;

import com.nmcp.tech.casesmanagement.data.DataElementRepository;
import com.nmcp.tech.casesmanagement.data.DataValueRepository;
import com.nmcp.tech.casesmanagement.data.orgunits.Orgunit;
import com.nmcp.tech.casesmanagement.data.orgunits.OrgunitRepository;
import com.nmcp.tech.casesmanagement.data.teams.Team;
import com.nmcp.tech.casesmanagement.data.teams.TeamRepository;
import fri.util.database.jpa.tree.nestedsets.NestedSetsTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//import java.util.Arrays;

//@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    private static Logger logger = LoggerFactory.getLogger(DevelopmentConfig.class);

    //  @Bean
//  public CommandLineRunner dataLoader(FacilityRepository facRepo,
//        DataElementRepository dIRepo, DataValueRepository dVRepo) { // user repo for ease of testing with a built-in user
    @Bean
    public CommandLineRunner dataLoader(OrgunitRepository orgunitRepository,
                                        DataElementRepository dIRepo, DataValueRepository dVRepo,
                                        TeamRepository teamRepository) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Orgunit orgunit = new Orgunit("Yemen", "YEM");
                orgunit.setIdString("H7CSmziHM6a");
                final NestedSetsTreeNode yemen = orgunitRepository.createRoot(orgunit);
                final NestedSetsTreeNode TeamT1001 = teamRepository.createRoot(new Team("Tree1-001"));
                final NestedSetsTreeNode TeamT2001 = teamRepository.createRoot(new Team("Tree2-001"));
//                orgunitRepository.

                final NestedSetsTreeNode parent = orgunitRepository.findByCode("YEM").get();
                final NestedSetsTreeNode taiz = orgunitRepository.addChild(parent, new Orgunit("Taiz", "15"));
                final NestedSetsTreeNode aden = orgunitRepository.addChild(parent, new Orgunit("Aden", "22"));
                final NestedSetsTreeNode TeamT1002 = teamRepository.addChild(TeamT1001, new Team("Tree1-002"));
                final NestedSetsTreeNode TeamT1003 = teamRepository.addChild(TeamT1001, new Team("Tree1-003"));
                final NestedSetsTreeNode TeamT10021 = teamRepository.addChild(TeamT1002, new Team("Tree1-0021"));
                final NestedSetsTreeNode TeamT2002 = teamRepository.addChild(TeamT2001, new Team("Tree2-002"));
                final List<NestedSetsTreeNode> childrenOfWalter = teamRepository.getRoots();
                logger.info("Team1001 size = 3, = {} ", teamRepository.size(childrenOfWalter.get(0)));
                logger.info("Team2001 size = 1, = {} ", teamRepository.size(childrenOfWalter.get(1)));

                logger.info("Team1001 size = 2, = {} ", teamRepository.size(TeamT1001));
                logger.info("Roots size = 2, = {} ", teamRepository.getRoots());
                Team team = (Team) teamRepository.getRoots().get(0);
                Team team2 = teamRepository.findByCode("Tree1-001").get();
                Team updatedTeam2 = (Team) teamRepository.getRoot(team2);

                logger.info("Roots[0].code  Tree1-001 = {} ", team.getCode());
                logger.info("Roots[0].prent  Tree1-001 = {} ", team.getParent());
                logger.info("Roots[1] Tree2-001 ={}", teamRepository.getRoots().get(1).getId());
                logger.info("level Team2001 size = 1, = {} ", teamRepository.getLevel(TeamT10021));
                logger.info("size Team1001 = {} ", teamRepository.size(team));
                logger.info("childcount Team2 = {} ", teamRepository.getChildCount(team2));


//                logger.info("Children of TeamT2001 size = {} ", childrenOfWalter.get(0).equals(TeamT1002));
                logger.info("Children of TeamT2001 size = {} ", childrenOfWalter.size());

//          NestedSetsTreeNode TeamT2002 = teamRepository.addChild( TeamT2001,new Team("Tree2-002"));
//          final NestedSetsTreeNode parent2 = orgunitRepository.findByCode("15").get();
//          final NestedSetsTreeNode Salah = orgunitRepository.addChild(parent2, new Orgunit("Salah", "1501"));


//                DataElement rptested = new DataElement();
//                rptested.setCode("RDTTested");
//                rptested.setName("RDT Tested");
//                DataElement suspected = new DataElement();
//                suspected.setCode("suspected");
//                suspected.setName("suspected Cases");
//                DataElement rppostive = new DataElement();
//                rppostive.setCode("RDTPositiv");
//                rppostive.setName("RDT Positiv");
//                DataElement rptestpf = new DataElement();
//                rptestpf.setCode("RDTPf");
//                rptestpf.setName("RDT Pf");
//                DataElement rptestpv = new DataElement();
//                rptestpv.setCode("RDTPv");
//                rptestpv.setName("RDT Pv");
//                DataElement rptestother = new DataElement();
//                rptestother.setCode("RDTOther");
//                rptestother.setName("RDT Other");
//                DataElement microtested = new DataElement();
//                microtested.setCode("MicroTested");
//                microtested.setName("Micro Tested");
//                DataElement micropostive = new DataElement();
//                micropostive.setCode("MicroPositive");
//                micropostive.setName("Micro Positive");
//                DataElement micropf = new DataElement();
//                micropf.setCode("MicroPf");
//                micropf.setName("Micro Pf");
//                DataElement micropv = new DataElement();
//                micropv.setCode("MicroPv");
//                micropv.setName("Micro Pv");
//                DataElement micromix = new DataElement();
//                micromix.setCode("MicroMix");
//                micromix.setName("Micro Mix");
//                DataElement microother = new DataElement();
//                microother.setCode("MicroOther");
//                microother.setName("Micro Other");
//                DataElement probalCases = new DataElement();
//                probalCases.setCode("Probable");
//                probalCases.setName("Probable Cases");
//                DataElement admittot = new DataElement();
//                admittot.setCode("Inpatients");
//                admittot.setName("Inpatients");
//                DataElement deathadmited = new DataElement();
//                deathadmited.setCode("MalariaDeath");
//                deathadmited.setName("Malaria Death");
//
//                dIRepo.save(rptested);
//                dIRepo.save(rppostive);
//                dIRepo.save(rptestpf);
//                dIRepo.save(rptestpv);
//                dIRepo.save(rptestother);
//                dIRepo.save(microtested);
//                dIRepo.save(micropostive);
//                dIRepo.save(micropf);
//                dIRepo.save(micropv);
//                dIRepo.save(micromix);
//                dIRepo.save(microother);
//                dIRepo.save(probalCases);
//                dIRepo.save(admittot);
//                dIRepo.save(deathadmited);
//                dIRepo.save(suspected);


            }
        };
    }

}
