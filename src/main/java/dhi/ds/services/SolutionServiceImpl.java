package dhi.ds.services;

import dhi.ds.domain.Solution;
import dhi.ds.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {
    private SolutionRepository solutionRepository;

    @Autowired
    public void setSolutionRepository(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Override
    public Page<Solution> findAllPageable(Pageable pageable) {
        return solutionRepository.findAllByOrderBySolutionName(pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup(String group, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup1OrderBySolutionName(group, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup2(String group, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup2OrderBySolutionName(group, pageable);
    }

    @Override
    public Page<Solution> listSolutionsBySolutionName(String sName, Pageable pageable) {
        return solutionRepository.findAllBySolutionNameIgnoreCaseContainingOrderBySolutionName(sName, pageable);
    }


    @Override
    public Page<Solution> listSolutionsByGroupAndSolutionName(String group, String solutionName, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup1AndSolutionNameIgnoreCaseContainingOrderBySolutionName(group, solutionName, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup2AndSolutionName(String group, String solutionName, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup2AndSolutionNameIgnoreCaseContainingOrderBySolutionName(group, solutionName, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByMaker(String maker, Pageable pageable) {
        return solutionRepository.findAllByMakerIgnoreCaseContainingOrderBySolutionName(maker, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroupAndMaker(String group, String maker, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup1AndMakerIgnoreCaseContainingOrderBySolutionName(group, maker, pageable);
    }

    @Override
    public Page<Solution> listSolutionsByGroup2AndMaker(String group, String maker, Pageable pageable) {
        return solutionRepository.findAllBySolutionGroup2AndMakerIgnoreCaseContainingOrderBySolutionName(group, maker, pageable);
    }

    @Override
    public Iterable<Solution> findAllSolutions() {
        return solutionRepository.findAll();
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
