package dhi.ds.repositories;

import dhi.ds.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolutionRepository extends PagingAndSortingRepository<Solution, Integer> {
    Page<Solution> findAllBySolutionGroup1(String group, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2(String group, Pageable pageable);
    Page<Solution> findAllBySolutionNameIsLike(String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndSolutionNameIsLike(String group, String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndSolutionNameIsLike(String group, String sName, Pageable pageable);

}
