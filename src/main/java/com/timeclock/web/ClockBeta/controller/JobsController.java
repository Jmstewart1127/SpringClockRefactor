package com.timeclock.web.ClockBeta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.service.JobsService;

@Controller
public class JobsController {
	
	@Autowired
	JobsService jobsService;

	@Autowired
	MaterialService materialService;
	
	@RequestMapping(value="/hello/business/{id}/newjob", method = RequestMethod.GET)
	public ModelAndView showNewJobForm(ModelAndView modelAndView, Jobs jobs, Business business) {
		modelAndView.addObject("jobs", jobs);
		modelAndView.addObject("business", business);
		modelAndView.setViewName("newjob");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/hello/business/{id}/newjob", method = RequestMethod.POST)
	public ModelAndView processJobRegistrationForm(ModelAndView modelAndView, 
			@PathVariable int id, Jobs jobs, Business business, BindingResult bindingResult, HttpServletRequest request) {


		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("newuser");		
		} else {
			jobs.setBizId(id);
			jobs.setAddressArray(jobsService.splitAddress(jobs.getJobAddress()));
		    jobs.setAmountCharged(jobs.getAmountDue());
		    jobsService.saveJobs(jobs);
		    
			modelAndView.addObject(jobs.getJobName());
			modelAndView.setViewName("newjobadded");
		}
			
		return modelAndView;
	}
	
	@RequestMapping(value="/hello/jobpayments", method = RequestMethod.GET)
	public ModelAndView showJobUpdateForm(ModelAndView modelAndView, Jobs jobs) {
		modelAndView.addObject("jobs", jobs);
		modelAndView.setViewName("updatejobstatus");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/hello/jobpayments", method = RequestMethod.POST)
	public ModelAndView processJobUpdateForm(ModelAndView modelAndView, 
			@Valid Jobs jobs, BindingResult bindingResult, HttpServletRequest request) {
				
		Jobs jobExists = jobsService.findById(jobs.getId());
		
		System.out.println(jobExists);
		
		if (jobExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", 
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("updatejobstatus");		
		} else { 
			String customerName = jobs.getCustomerName();
			double amountPaid = jobs.getAmountPaid();
			System.out.println(customerName + " name");
			System.out.println(amountPaid + " paid");
		    jobsService.addPayment(customerName, amountPaid);   
		    
			modelAndView.setViewName("newjobadded");
		}
			
		return modelAndView;
	}
	
    @RequestMapping(path="/hello/showjobs", method = RequestMethod.GET)
    public ModelAndView showJobs() {
        ModelAndView mav = new ModelAndView("showjobs");
        mav.addObject("jobs", jobsService.findAll());
        
        return mav;
    }
    
	@RequestMapping(value="/hello/jobs/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateJobsPage(ModelAndView modelAndView, @PathVariable int id) {
		Jobs jobs = jobsService.findById(id);
		modelAndView.addObject("jobs", jobs);
		modelAndView.setViewName("updatejobs");
        return modelAndView;
	}
    
    @RequestMapping(value="/hello/jobs/{id}/update",method=RequestMethod.POST)
	public ModelAndView processJobEditForm(ModelAndView modelAndView,
			@PathVariable int id, @Valid Jobs jobs, BindingResult bindingResult,
			HttpServletRequest request) {
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("updatejobstatus");		
		} else { 
 
			modelAndView.setViewName("showjobs");
			jobsService.saveJobs(jobs);
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
	public ModelAndView showUpdateMaterialsPage(ModelAndView modelAndView, @PathVariable int id, Material material) {
		modelAndView.addObject("material", material);
		modelAndView.setViewName("addmaterial");
		return modelAndView;
	}

	@RequestMapping(value="/hello/jobs/{id}/materials/update",method=RequestMethod.POST)
	public ModelAndView processJobMaterialsForm(ModelAndView modelAndView,
        @PathVariable int id, BindingResult bindingResult, Material material,
        HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("addmaterial");
		} else {
			modelAndView.setViewName("showmaterials");
			modelAndView.addObject(material);
			material.setJobId(id);
			int quantity = material.getQuantity();
			double price = material.getPrice();
			double totalPrice = materialService.calculateTotalPrice(quantity, price);
			double currentMaterialCost = jobsService.findMaterialCostById(id);
			double newCost = currentMaterialCost + totalPrice;
			jobsService.updateMaterialCost(id, newCost);
			material.setTotalPrice(totalPrice);
			Material mat = new Material(id, material.getPoNumber(), material.getPartName(), material.getQuantity(), material.getPrice(), totalPrice);
			materialService.saveMaterial(mat);
		}

		return modelAndView;
	}
   
	@RequestMapping(value="/hello/jobs/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteJob(ModelAndView modelAndView, @Valid Jobs jobs, @PathVariable int id) {
		modelAndView.addObject(jobs);
		modelAndView.setViewName("showjobs");
		jobsService.deleteJob(id);
        return modelAndView;
	}    

	
}
