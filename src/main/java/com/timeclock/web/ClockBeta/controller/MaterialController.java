package com.timeclock.web.ClockBeta.controller;

import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MaterialController {

    @Autowired
    MaterialService materialService;

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
}
    