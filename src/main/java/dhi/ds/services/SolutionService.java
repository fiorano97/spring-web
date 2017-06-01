package dhi.ds.services;


import dhi.ds.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SolutionService {

    Page<Solution> findAllPageable(Pageable pageable);

    Page<Solution> listSolutionsByGroup(String group, Pageable pageable);

    Page<Solution> listSolutionsByGroup2(String group, Pageable pageable);

    Page<Solution> listSolutionsBySolutionName(String solutionName, Pageable pageable);

    Page<Solution> listSolutionsByGroupAndSolutionName(String group, String solutionName, Pageable pageable);

    Page<Solution> listSolutionsByGroup2AndSolutionName(String group, String solutionName, Pageable pageable);

    Page<Solution> listSolutionsByMaker(String maker, Pageable pageable);

    Page<Solution> listSolutionsByGroupAndMaker(String group, String maker, Pageable pageable);

    Page<Solution> listSolutionsByGroup2AndMaker(String group, String maker, Pageable pageable);

    Solution getSolutionById(Integer id);

    Solution saveSolution(Solution solution);
}
