package dhi.ds.repositories;

import dhi.ds.configuration.RepositoryConfiguration;
import dhi.ds.domain.Solution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class SolutionRepositoryTest {

    private SolutionRepository solutionRepository;

    @Autowired
    public void setSolutionRepository(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Test
    public void testSaveProduct(){
        
        Solution solution = new Solution();
        solution.setSolutionName("SBOC");
        solution.setEquipment("BOILER");
        solution.setComponent("SootBlower");
        solution.setCustomerBenefit1("Efficiency");
        solution.setCustomerBenefit2("Fuel Saving");
        solution.setFunction("Improve tube heat Transfer");
        solution.setMaker("파워엔지니어링");
        solution.setSupplier("파워엔지니어링");
        solution.setDevelopmentType("상용솔루션");
        solution.setSolutionType("HW+SW");
        solution.setReference("당진");
        solution.setSource("태안ITB가격입찰, 하동R&D");

        assertNull(solution.getId()); //null before save

        solutionRepository.save(solution);

        assertNotNull(solution.getId()); //not null after save

        //fetch from DB
        Solution fetchedSolution = solutionRepository.findOne(solution.getId());

        //should not be null
        assertNotNull(fetchedSolution);

        //should equal
        assertEquals(solution.getId(), fetchedSolution.getId());
        assertEquals(solution.getSolutionName(), fetchedSolution.getSolutionName());

        //update description and save
        fetchedSolution.setFunction("Improve All");
        solutionRepository.save(fetchedSolution);

        //get from DB, should be updated
        Solution fetchedUpdatedSolution = solutionRepository.findOne(fetchedSolution.getId());
        assertEquals(fetchedSolution.getFunction(), fetchedUpdatedSolution.getFunction());

        //verify count of products in DB
        long solutionCount = solutionRepository.count();
        assertEquals(solutionCount, 1);

        //get all products, list should only have one
        Iterable<Solution> solutions = solutionRepository.findAll();

        int count = 0;

        for(Solution s : solutions){
            count++;
        }

        assertEquals(count, 1);
    }
}
