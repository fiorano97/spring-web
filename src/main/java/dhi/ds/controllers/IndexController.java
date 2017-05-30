package dhi.ds.controllers;

import dhi.ds.domain.Solution;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/view_sf")
    String swFunctionalView(@ModelAttribute("solution") Solution solution){
        return "view_sf";
    }

    @RequestMapping("/view_av")
    String assetValueView(@ModelAttribute("solution") Solution solution){
        return "view_av";
    }
}
