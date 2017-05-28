package guru.springframework.repositories;

import guru.springframework.domain.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SolutionRepository extends PagingAndSortingRepository<Solution, Integer> {
    Page<Solution> findAllBySolutionGroup1(String group, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2(String group, Pageable pageable);
    Page<Solution> findAllBySolutionNameIsLike(String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup1AndSolutionNameIsLike(String group, String sName, Pageable pageable);
    Page<Solution> findAllBySolutionGroup2AndSolutionNameIsLike(String group, String sName, Pageable pageable);

}
