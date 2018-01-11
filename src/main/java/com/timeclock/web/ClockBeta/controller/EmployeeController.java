package com.timeclock.web.ClockBeta.controller;

import javax.validation.Valid;

import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.model.Employee;
import com.timeclock.web.ClockBeta.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.timeclock.web.ClockBeta.service.EmployeeService;

@Controller
@SessionAttributes("user")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	BusinessService businessService;

	@Autowired
	UserAuthDetails userAuthDetails;

	// Show employees by business id
    @RequestMapping(path="/hello/business/{id}/employees", method = RequestMethod.GET)
    public ModelAndView showEmployees(ModelAndView modelAndView, Employee employee, Business business, @PathVariable int id) {
    	modelAndView.setViewName("showemployees");
        modelAndView.addObject("employee", employeeService.findEmployeeByBusinessId(id));
        modelAndView.addObject("business", businessService.findById(id));
        return modelAndView;
    }
    
    // Show all employees
    @RequestMapping(value="/hello/employees", method = RequestMethod.GET)
    public ModelAndView showBusinesses(ModelAndView modelAndView, Employee employee, Business business, Authentication auth) {
        modelAndView.setViewName("showemployees");
        modelAndView.addObject("employee", employeeService.findAllEmployeesByAdmin(auth));
        return modelAndView;
    }

    // Add employees to business
	@RequestMapping(path="/hello/business/{id}/adduser", method = RequestMethod.GET)
	public ModelAndView showNewUserForm(
			ModelAndView modelAndView,
			@PathVariable int id,
			Employee employee,
			Business business) {
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("newuser");
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/hello/business/{id}/adduser", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(
			ModelAndView modelAndView,
			Business business,
			@Valid Employee employee,
			BindingResult bindingResult,
			@PathVariable int id) {

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("newuser");
		} else {
		    Employee e = new Employee();
			System.out.println(employee.getEmployeeName());
		    e.setEmployeeName(employee.getEmployeeName());
			e.setBusinessId(id);
			e.setPayRate(employee.getPayRate());
		    employeeService.saveEmployee(e);
			modelAndView.setViewName("showemployees");
		}
		return this.showEmployees(modelAndView, employee, business, id);
	}

	// Reset pay period
	@RequestMapping(value = "/hello/business/{id}/reset", method = RequestMethod.GET)
	public ModelAndView resetPayPeriod(
			ModelAndView modelAndView,
			Business business,
			@Valid Employee employee,
			@PathVariable int id,
			Authentication auth) {
    	modelAndView.setViewName("showbusinesses");
		modelAndView.addObject("business", businessService.findByLoggedInUserId(auth));
		employeeService.resetPayPeriod(id);
		return modelAndView;
	}

	// Show homepage employee in form
	@RequestMapping(path="/", method = RequestMethod.GET)
	public ModelAndView showClockForm(ModelAndView modelAndView, Employee employee) {
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("timeclock");
		return modelAndView;
	}

	// Process employee time clock form
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView processClockForm(ModelAndView modelAndView, @Valid Employee employee) {
		modelAndView.setViewName("timeclockupdate");
		int userId = employee.getId();
		Boolean isClocked = employeeService.findIsClockedInById(userId);
		
		if (isClocked) {
			employeeService.clockOut(userId);
			return modelAndView;
		} else {
			employeeService.clockIn(userId);
			return modelAndView;
		}
	}
	
	// Clock in form for 'showemployees' view
	@RequestMapping(value = "/hello/employees/{id}/clockin", method = RequestMethod.POST)
	public ModelAndView processClockFormAdmin(
			ModelAndView modelAndView,
			@Valid Employee employee,
			Business business,
			@PathVariable int id) {
		Boolean isClockedIn = employeeService.findIsClockedInById(id);
		if (isClockedIn) {
			employeeService.clockOut(id);
			return this.showEmployees(modelAndView, employee, business, employeeService.findBusinessIdById(id));
		} else {
			employeeService.clockIn(id);
			return this.showEmployees(modelAndView, employee, business, employeeService.findBusinessIdById(id));
		}
	}
	
	// Show update employee form
	@RequestMapping(value="/hello/employee/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateEmployeePage(ModelAndView modelAndView, @PathVariable int id) {
		Employee employee = employeeService.findEmployeeNameById(id);
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("updateemployee");
        return modelAndView;
	}
    
	// Process edit employee form
    @RequestMapping(value="/hello/employee/{id}/update",method=RequestMethod.POST)
	public ModelAndView processEmployeeEditForm(
			ModelAndView modelAndView,
			@Valid Employee employee,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("updatejobstatus");		
		} else {
			modelAndView.setViewName("showemployees");
			employeeService.saveEmployee(employee);
		}
		return modelAndView;
	}
	
    // Delete user
	@RequestMapping(value = "/hello/employee/{id}/delete", method = RequestMethod.POST)
	public ModelAndView processDeleteFormAdmin(
			ModelAndView modelAndView,
			@Valid Employee employee,
			Business business,
			@PathVariable int id) {

		int bizId = employeeService.findBusinessIdById(id); // Store bizId before deleting user
		Employee user = employeeService.findEmployeeNameById(id);
		employeeService.delete(user);
		return this.showEmployees(modelAndView, employee, business, bizId);
	}

}
