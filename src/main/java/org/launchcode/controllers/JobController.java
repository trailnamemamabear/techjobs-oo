package org.launchcode.controllers;

import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        model.addAttribute("employer", jobData.findAll().get(id).getEmployer());
        model.addAttribute("name", jobData.findAll().get(id).getName());
        model.addAttribute("position", jobData.findAll().get(id).getPositionType());
        model.addAttribute("skill", jobData.findAll().get(id).getCoreCompetency());
        model.addAttribute("location", jobData.findAll().get(id).getLocation());

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        if (errors.hasErrors()) {
            System.out.println("Errors are " + errors.getAllErrors().toString());
            return "new-job";

        Job newJob = new Job;

        newJob.setName(jobForm.getName());
        newJob.setEmployer(jobData.getEmployers().findById(jobForm.getEmployerId()));
        newJob.setLocation(jobData.getLocations().findById(jobForm.getLocationId()));
        newJob.setPositionType(jobData.getPositionTypes().findById(jobForm.getPositionTypeId()));
        newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));

        model.addAttribute("employer", jobData.findAll().get(id).getEmployer());
        model.addAttribute("name", jobData.findAll().get(id).getName());
        model.addAttribute("position", jobData.findAll().get(id).getPositionType());
        model.addAttribute("skill", jobData.findAll().get(id).getCoreCompetency());
        model.addAttribute("location", jobData.findAll().get(id).getLocation());

        return "job-detail";

    }
}
