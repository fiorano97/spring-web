package guru.springframework.controllers;

import guru.springframework.domain.Pager;
import guru.springframework.domain.Solution;
import guru.springframework.domain.UploadFile;
import guru.springframework.services.ImageService;
import guru.springframework.services.SolutionService;
import guru.springframework.util.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class SolutionController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    private Environment env;

    @Autowired
    ImageService imageService;

    private SolutionService solutionService;

    @Autowired
    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @RequestMapping("solution/{id}")
    public String showSolution(@PathVariable Integer id, Model model){
        model.addAttribute("solution", solutionService.getSolutionById(id));
        return "solutionshow";
    }

    @RequestMapping("solution/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Solution solution = solutionService.getSolutionById(id);
        /*UploadFile file = imageService.load(Integer.parseInt(solution.getImageId()));*/

        model.addAttribute("solution", solution);/*
        model.addAttribute("imageName", file.getFileName());*/

        return "solutionform";
    }

    @RequestMapping("solution/new")
    public String newSolution(Model model){
        model.addAttribute("solution", new Solution());
        return "solutionform";
    }

    @RequestMapping(value = "solution", method = RequestMethod.POST)
    public String saveProduct(Solution solution, @RequestParam("uploadImage") MultipartFile uploadImage){
        if(!uploadImage.getOriginalFilename().equals("")) {
            try {
                UploadFile uploadedFile = imageService.store(uploadImage);
                solution.setImageId(uploadedFile.getId()+"");
                solution.setImageName(uploadedFile.getFileName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        solutionService.saveSolution(solution);
        return "redirect:/solution/" + solution.getId();
    }


    @RequestMapping("/solutions")
    public ModelAndView showPersonsPage(Solution solution,
                                        @RequestParam("viewType") Optional<String> viewType,
                                        @RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("solutions");
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<Solution> solutions = null;
        String solutionName = null;
        if(viewType.isPresent() && !viewType.get().toString().equals("")) {
            if(viewType.get().toString().equals("AssetValueView")) {
                if (solution != null && solution.getSolutionName() != null && !solution.getSolutionName().equals("")) {
                    solutionName = "%" + solution.getSolutionName() + "%";
                    solutions = solutionService.listSolutionsByGroup2AndSolutionName(solution.getSolutionGroup2(), solutionName, new PageRequest(evalPage, evalPageSize));
                } else {
                    solutions = solutionService.listSolutionsByGroup2(solution.getSolutionGroup2(), new PageRequest(evalPage, evalPageSize));
                }
            } else {
                if (solution != null && solution.getSolutionName() != null && !solution.getSolutionName().equals("")) {
                    solutionName = "%" + solution.getSolutionName() + "%";
                    solutions = solutionService.listSolutionsByGroupAndSolutionName(solution.getSolutionGroup1(), solutionName, new PageRequest(evalPage, evalPageSize));
                } else {
                    solutions = solutionService.listSolutionsByGroup(solution.getSolutionGroup1(), new PageRequest(evalPage, evalPageSize));
                }
            }
            modelAndView.addObject("viewType", viewType.get().toString());
        } else {
            if (solution != null && solution.getSolutionName() != null && !solution.getSolutionName().equals("")) {
                solutionName = "%" + solution.getSolutionName() + "%";
                solutions = solutionService.listSolutionsBySolutionName(solutionName, new PageRequest(evalPage, evalPageSize));
            } else {
                solutions = solutionService.findAllPageable(new PageRequest(evalPage, evalPageSize));
            }
        }
        Pager pager = null;
        if(solutions.getTotalPages() == 0) {
            pager = new Pager(1, solutions.getNumber(), BUTTONS_TO_SHOW);
        } else {
            pager = new Pager(solutions.getTotalPages(), solutions.getNumber(), BUTTONS_TO_SHOW);
        }

        modelAndView.addObject("solutionName", solutionName);
        modelAndView.addObject("solutions", solutions);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("count", solutions.getTotalElements());
        return modelAndView;
    }


    @GetMapping("/image/{fileId}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable int fileId) {
        try {
            UploadFile uploadedFile = imageService.load(fileId);
            HttpHeaders headers = new HttpHeaders();

            Resource resource = imageService.loadAsResource(uploadedFile.getSaveFileName());
            String fileName = uploadedFile.getFileName();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

            if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
                headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
            } else {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            }

            return ResponseEntity.ok().headers(headers).body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
