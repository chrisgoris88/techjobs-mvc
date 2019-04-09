package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchTerm,
                          @RequestParam String searchType) {
        ArrayList<HashMap<String, String>> searchJobs = new ArrayList<>();

        if (searchType.equals("all")){
            searchJobs = JobData.findByValue(searchTerm);
        }
        else {
            searchJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("title", "results: " + searchJobs.size());
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", searchJobs);

        return "search";

    }

    // TODO #1 - Create handler to process search request and display results

}
