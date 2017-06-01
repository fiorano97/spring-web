package dhi.ds.controllers;

import dhi.ds.domain.FileInfo;
import dhi.ds.domain.Pager;
import dhi.ds.domain.Solution;
import dhi.ds.domain.UploadFile;
import dhi.ds.services.FileService;
import dhi.ds.services.SolutionService;
import dhi.ds.util.MediaUtils;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class SolutionController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    private FileService fileService;
    private SolutionService solutionService;

    @Autowired
    Environment env;

    @Autowired
    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        fileService.setRootLocation(env.getProperty("path.uploadedFiles"));
        this.fileService = fileService;
    }

    @RequestMapping("solution/{id}")
    public String showSolution(@PathVariable Integer id, Model model){
        Solution solution = solutionService.getSolutionById(id);
        List<FileInfo> fileInfoList = makeFileInfoList(solution);
        model.addAttribute("solution", solution);
        model.addAttribute("images", solution.getImageId());
        model.addAttribute("files", fileInfoList);
        return "solutionshow";
    }

    @RequestMapping("solution/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Solution solution = solutionService.getSolutionById(id);
        List<FileInfo> fileInfoList = makeFileInfoList(solution);
        model.addAttribute("solution", solution);
        model.addAttribute("images", solution.getImageId());
        model.addAttribute("files", fileInfoList);
        model.addAttribute("type", "edit");
        return "solutionform";
    }

    @RequestMapping("solution/new")
    public String newSolution(Model model){
        model.addAttribute("solution", new Solution());
        model.addAttribute("type", "insert");
        return "solutionform";
    }

    @RequestMapping(value = "solution", method = RequestMethod.POST)
    public String saveProduct(Solution solution,
                              @RequestParam("imageCheck") Optional<List<Integer>> imageCheckList,
                              @RequestParam("fileCheck") Optional<List<Integer>> fileCheckList,
                              @RequestParam("uploadingImageFiles") MultipartFile[] uploadingImageFiles,
                              @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles){

        solution.setImageId(storeFiles(deleteCheckedFiles(solution.getImageId(), imageCheckList), uploadingImageFiles));
        solution.setFileId(storeFiles(deleteCheckedFiles(solution.getFileId(), fileCheckList), uploadingFiles));

        solutionService.saveSolution(solution);

        return "redirect:/solution/" + solution.getId();
    }



    @RequestMapping("/solutions")
    public ModelAndView showPersonsPage(Solution solution,
                                        @RequestParam("searchCondition") Optional<String> searchCondition,
                                        @RequestParam("searchInput") Optional<String> searchInput,
                                        @RequestParam("viewType") Optional<String> viewType,
                                        @RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("solutions");
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        String searchCond = searchCondition.orElse("0");
        String searchIn = searchInput.orElse("");
        Page<Solution> solutions = null;
        String solutionName, maker = null;
        if(viewType.isPresent() && !viewType.get().toString().equals("")) {
            if(viewType.get().toString().equals("AssetValueView")) {

                if (solution != null && searchIn != null && !searchIn.equals("")) {
                    if(searchCond.equals("0")) {
                        solutionName = "%" + searchIn + "%";
                        solutions = solutionService.listSolutionsByGroup2AndSolutionName(solution.getSolutionGroup2(), solutionName, new PageRequest(evalPage, evalPageSize));
                    } else {
                        maker = "%" + searchIn + "%";
                        solutions = solutionService.listSolutionsByGroup2AndMaker(solution.getSolutionGroup2(), maker, new PageRequest(evalPage, evalPageSize));
                    }
                } else {
                    solutions = solutionService.listSolutionsByGroup2(solution.getSolutionGroup2(), new PageRequest(evalPage, evalPageSize));
                }
            } else {
                if (solution != null && searchIn != null && !searchIn.equals("")) {
                    if(searchCond.equals("0")) {
                        solutionName = "%" + searchIn + "%";
                        solutions = solutionService.listSolutionsByGroupAndSolutionName(solution.getSolutionGroup1(), solutionName, new PageRequest(evalPage, evalPageSize));
                    } else {
                        maker = "%" + searchIn + "%";
                        solutions = solutionService.listSolutionsByGroupAndMaker(solution.getSolutionGroup1(), maker, new PageRequest(evalPage, evalPageSize));
                    }
                } else {
                    solutions = solutionService.listSolutionsByGroup(solution.getSolutionGroup1(), new PageRequest(evalPage, evalPageSize));
                }
            }
            modelAndView.addObject("viewType", viewType.get().toString());
        } else {
            if (solution != null && searchIn != null && !searchIn.equals("")) {
                if(searchCond.equals("0")) {
                    solutionName = "%" + searchIn + "%";
                    solutions = solutionService.listSolutionsBySolutionName(solutionName, new PageRequest(evalPage, evalPageSize));
                } else {
                    maker = "%" + searchIn + "%";
                    solutions = solutionService.listSolutionsByMaker(maker, new PageRequest(evalPage, evalPageSize));
                }
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

        modelAndView.addObject("searchCondition", searchCond);
        modelAndView.addObject("searchInput", searchIn);
        modelAndView.addObject("solutions", solutions);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("count", solutions.getTotalElements());
        return modelAndView;
    }


    @GetMapping("/files/{fileId}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable Integer fileId) {
        try {
            UploadFile uploadedFile = fileService.load(fileId);
            HttpHeaders headers = new HttpHeaders();

            Resource resource = fileService.loadAsResource(uploadedFile.getSaveFileName());
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
    private List<FileInfo> makeFileInfoList(Solution solution) {
        List<FileInfo> fileInfoList = new LinkedList<>();
        for(int i = 0; i < solution.getFileId().size(); i++) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(solution.getFileId().get(i));
            fileInfo.setFileName(fileService.load(solution.getFileId().get(i)).getFileName());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }

    private List<Integer> storeFiles(List<Integer> fileList, MultipartFile[] uploadingFiles) {
        UploadFile uploadedFile = null;
        for(MultipartFile uploadingFile : uploadingFiles) {

            if (!uploadingFile.getOriginalFilename().equals("")) {
                try {
                    uploadedFile = fileService.store(uploadingFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fileList.add(uploadedFile.getId());
            }
        }
        return fileList;
    }

    private  List<Integer> deleteCheckedFiles(List<Integer> fileIdList, Optional<List<Integer>> fileCheckList) {
        if(fileCheckList.isPresent()) {
            List<Integer> chList = fileCheckList.get();
            for(int i =0; i < chList.size(); i++){
                for(int j=0; j < fileIdList.size(); j++) {
                    if(fileIdList.get(j).intValue() == chList.get(i).intValue()) {
                        fileIdList.remove(j);
                        break;
                    }
                }
            }
        }
        return fileIdList;
    }

}
