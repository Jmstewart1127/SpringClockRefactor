package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timeclock.web.ClockBeta.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	EmployeeService employeeService;
	
	/*
	 * Displays All Employee Data by Business ID
	 */
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping("/rest/employees/{id}")
	public Iterable<Employee> showEmployeesByBizId(@PathVariable int id) {
		return employeeService.findEmployeeByBusinessId(id);
	}

	/*
	* Gets Employee By ID
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping("/rest/get/employee/{id}")
	public Iterable<Employee> getEmployee(@PathVariable int id) {
		return employeeService.findById(id);
	}
	
	/*
	* Get All Employees By Admin ID
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping("/rest/get/all/employees/{id}")
	public Iterable<Employee> getEmployees(@PathVariable int id) {
		return employeeService.findAllEmployeesByAdminId(id);
	}

	/*
	* Employee Clock In (Web)
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/web/clock/in/{id}")
	public void handleClockInOut(@PathVariable int id) {
		employeeService.handleClockInOut(id);
	}

	/*
 	* Employee Clock In (Mobile) without job id for apps not yet updated
 	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/in/{id}")
	public void clockIn(@PathVariable int id) {
		employeeService.clockIn(id);
	}

	/*
	* Employee Clock Out (Mobile) without job id for apps not yet updated
 	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/out/{id}")
	public void clockOut(@PathVariable int id) {
		employeeService.clockOut(id);
	}

	/*
    * Employee Clock In (Mobile) With Job ID
    */
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/in/{id}/{jobId}")
	public void clockIn(@PathVariable int id, @PathVariable int jobId) {
		employeeService.clockInAtJob(id, jobId);
	}

	/*
	* Employee Clock Out (Mobile) With Job ID
 	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/out/{id}/{jobId}")
	public void clockOut(@PathVariable int id, @PathVariable int jobId) {
		employeeService.clockOutFromJob(id, jobId);
	}

	/*
	* Refresh Employee Status
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/status/refresh/{id}")
	public void refreshEmployeeStatusWithJobId(@PathVariable int id) {
		employeeService.refreshClockAndAddLabor(id);
	}

	/*
	* Delete User By ID
	*/
	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
	@RequestMapping(value="/rest/clock/delete/{id}")
	public void deleteEmployeeById(@PathVariable int id) {
		employeeService.deleteById(id);
	}

	/*
	* Refresh Employee Status
	*/
//	@CrossOrigin(origins = {"http://localhost:3000", "https://spring-clock-ui.herokuapp.com"})
//	@RequestMapping(value="/rest/status/refresh/{id}")
//	public void refreshClock(@PathVariable int id) {
//		clockService.refreshClock(id);
//	}
	
}
