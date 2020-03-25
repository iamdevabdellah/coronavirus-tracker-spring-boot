package com.corona.coronavirustracker.controllers;

import com.corona.coronavirustracker.models.LocationStats;
import com.corona.coronavirustracker.services.CoronaVirusDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  CoronaVirusDataService coronaVirusDataService;

  @GetMapping("/")
  public String home(Model model) {
    List<LocationStats> allstats = coronaVirusDataService.getAllStats();
    int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
    int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
    model.addAttribute("locationStats", allstats);
    model.addAttribute("totalReportedCases", totalReportedCases);
    model.addAttribute("totalNewCases", totalNewCases);

    return "home";
  }
}
