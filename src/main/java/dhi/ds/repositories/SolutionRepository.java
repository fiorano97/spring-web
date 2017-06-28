package dhi.ds.repositories;

import dhi.ds.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SolutionRepository extends PagingAndSortingRepository<Solution, Integer> {
    Page<Solution> findAllByOrderBySolutionName(Pageable pageable);
    Page<Solution> findAllBySolutionGroup1OrderBySolutionName(String group, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2OrderBySolutionName(String group, Pageable pageable);
    Page<Solution> findAllBySolutionNameIgnoreCaseContainingOrderBySolutionName(String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndSolutionNameIgnoreCaseContainingOrderBySolutionName(String group, String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndSolutionNameIgnoreCaseContainingOrderBySolutionName(String group, String sName, Pageable pageable);
    Page<Solution> findAllByMakerIgnoreCaseContainingOrderBySolutionName(String maker, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndMakerIgnoreCaseContainingOrderBySolutionName(String group, String maker, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndMakerIgnoreCaseContainingOrderBySolutionName(String group, String maker, Pageable pageable);
}
