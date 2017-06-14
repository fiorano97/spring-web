package dhi.ds.repositories;

import dhi.ds.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SolutionRepository extends PagingAndSortingRepository<Solution, Integer> {
    Page<Solution> findAllByOrderBySolutionName(Pageable pageable);
    Page<Solution> findAllBySolutionGroup1OrderBySolutionName(String group, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2OrderBySolutionName(String group, Pageable pageable);
    Page<Solution> findAllBySolutionNameIsLikeOrderBySolutionName(String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndSolutionNameIsLikeOrderBySolutionName(String group, String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndSolutionNameIsLikeOrderBySolutionName(String group, String sName, Pageable pageable);
    Page<Solution> findAllByMakerIsLikeOrderBySolutionName(String maker, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndMakerIsLikeOrderBySolutionName(String group, String maker, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndMakerIsLikeOrderBySolutionName(String group, String maker, Pageable pageable);

}
