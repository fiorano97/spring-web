package guru.springframework.services;

import guru.springframework.domain.Solution;
import guru.springframework.repositories.SolutionPagingRepository;
import guru.springframework.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolutionServiceImpl implements SolutionService {
    private SolutionRepository solutionRepository;

    @Autowired
    public void setSolutionRepository(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Override
    public Page<Solution> findAllPageable(Pageable pageable) {
        return solutionRepository.findAll(pageable);
    }

    @Override
    public Page<Solution> listSolutionsBySolutionName(String sName, Pageable pageable) {
        return solutionRepository.findAllBySolutionNameIsLike(sName, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup(String group, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup1(group, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup2(String group, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup2(group, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroupAndSolutionName(String group, String solutionName, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup1AndSolutionNameIsLike(group, solutionName, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup2AndSolutionName(String group, String solutionName, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup2AndSolutionNameIsLike(group, solutionName, pageable);
    }

    @Override
    public Solution getSolutionById(Integer id) {
        return solutionRepository.findOne(id);
    }

    @Override
    public Solution saveSolution(Solution solution) {
        return solutionRepository.save(solution);
    }
}
