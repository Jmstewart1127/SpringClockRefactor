package com.timeclock.web.ClockBeta.controller;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Material;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MaterialController {

    @RequestMapping(value="/hello/jobs/materials/", method = RequestMethod.GET)
    public ModelAndView showMaterialsPage(ModelAndView modelAndView, @Valid Material material) {
        modelAndView.addObject("material", material);
        modelAndView.setViewName("showmaterials");
        return modelAndView;
    }
}
    