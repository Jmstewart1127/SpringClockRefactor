package com.timeclock.web.ClockBeta.controller;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.service.JobsService;
import com.timeclock.web.ClockBeta.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    JobsService jobsService;

    @RequestMapping(value="/hello/jobs/materials/", method = RequestMethod.GET)
    public ModelAndView showMaterialsPage(ModelAndView modelAndView, Material material) {
        modelAndView.addObject("material", material);
        modelAndView.setViewName("showmaterials");
        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/materials/update", method = RequestMethod.GET)
    public ModelAndView showUpdateMaterialsPage(ModelAndView modelAndView, Material material) {
        modelAndView.addObject("material", material);
        modelAndView.setViewName("addmaterial");
        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/materials/update",method=RequestMethod.POST)
    public ModelAndView processMaterialsForm(ModelAndView modelAndView,
                                                @Valid Material material,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addmaterial");
        } else {
            modelAndView.setViewName("showmaterials");
            modelAndView.addObject(material);
            materialService.saveMaterial(material);
        }

        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/{id}/materials/", method = RequestMethod.GET)
    public ModelAndView showMaterialsPage(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.addObject("material", materialService.findByJobId(id));
        modelAndView.setViewName("showmaterials");
        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/{id}/materials/update", method = RequestMethod.GET)
    public ModelAndView showUpdateMaterialsPageForJob(ModelAndView modelAndView, Material material, Jobs jobs) {
        modelAndView.addObject("material", material);
        modelAndView.addObject("jobs", jobs);
        modelAndView.setViewName("addmaterial");
        return modelAndView;
    }

    @RequestMapping(value="/hello/jobs/{id}/materials/update",method=RequestMethod.POST)
    public ModelAndView processJobMaterialsForm(ModelAndView modelAndView,
                                                @PathVariable int id,
                                                BindingResult bindingResult,
                                                Jobs jobs,
                                                Material material) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addmaterial");
        } else {
            modelAndView.setViewName("showmaterials");
            modelAndView.addObject("material", materialService.findByJobId(id));
            modelAndView.addObject("totalCost", materialService.totalPriceOfAllJobMaterials(id));
            int quantity = material.getQuantity();
            double price = material.getPrice();
            double totalPrice = materialService.calculateTotalPrice(quantity, price);
            double currentMaterialCost = jobsService.findMaterialCostById(id);
            double newCost = currentMaterialCost + totalPrice;
            jobsService.updateMaterialCost(id, newCost);
            Material m = new Material();
            m.setJobId(id);
            m.setPurchasedFrom(material.getPurchasedFrom());
            m.setPoNumber(material.getPoNumber());
            m.setPartName(material.getPartName());
            m.setPrice(material.getPrice());
            m.setQuantity(material.getQuantity());
            m.setTotalPrice(totalPrice);
            materialService.saveMaterial(m);
        }

        return modelAndView;
    }
}
    