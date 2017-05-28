package guru.springframework.repositories;

import guru.springframework.domain.Solution;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolutionPagingRepository extends PagingAndSortingRepository<Solution, Integer> {
    /*Iterable<Solution> findAllBySolutionNameIsLike(String solutionName);*/
}