package dhi.ds.repositories;

import dhi.ds.domain.Solution;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolutionPagingRepository extends PagingAndSortingRepository<Solution, Integer> {
    /*Iterable<Solution> findAllBySolutionNameIsLike(String solutionName);*/
}