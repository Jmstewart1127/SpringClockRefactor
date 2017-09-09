package com.timeclock.web.ClockBeta.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.repository.BusinessRepository;
import com.timeclock.web.ClockBeta.service.BusinessService;
import com.timeclock.web.ClockBeta.service.EmailService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
@SessionAttributes("adminName")
public class BusinessController {
	
	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	EmailService emailService;
	
	
	@GetMapping(path="/newbusiness")
	public String newBiz () {
		return "newbusiness";
	}
	
	
	@PostMapping(path="/newbusiness") // Map ONLY GET Requests
	public String addNewBusiness (@RequestParam String adminName, @RequestParam String bizName, 
			@RequestParam String password, @RequestParam String password1) {
		
		if (password.equals(password1)) {
			Business b = new Business();
			b.setAdminName(adminName);
			b.setBizName(bizName);
			b.setPassword(password);
			businessRepository.save(b);
		}
		
		return "welcome";
	}
	
	
//		// Return registration form template
//		@RequestMapping(value="/register", method = RequestMethod.GET)
//		public ModelAndView showRegistrationPage(ModelAndView modelAndView, Business business){
//			modelAndView.addObject("business", business);
//			modelAndView.setViewName("register");
//			return modelAndView;
//		}
//		
//		// Process form input data
//		@RequestMapping(value = "/register", method = RequestMethod.POST)
//		public ModelAndView processRegistrationForm(ModelAndView modelAndView, 
//				@Valid Business business, BindingResult bindingResult, HttpServletRequest request) {
//					
//			// Lookup user in database by id
//			Business businessExists = businessService.findById(business.getId());
//			
//			System.out.println(businessExists);
//			
//			if (businessExists != null) {
//				modelAndView.addObject("alreadyRegisteredMessage", 
//						"Oops!  There is already a user registered with the email provided.");
//				modelAndView.setViewName("register");
//				bindingResult.reject("email");
//			}
//				
//			if (bindingResult.hasErrors()) { 
//				modelAndView.setViewName("register");		
//			} else { // new user so we create user and send confirmation e-mail
//						
//				// Disable user until they click on confirmation link in email
//		    business.setEnabled(false);
//			      
//			    // Generate random 36-character string token for confirmation link
//			    business.setConfirmationToken(UUID.randomUUID().toString());
//			        
//			    businessService.saveBusiness(business);
//					
//				String appUrl = request.getScheme() + "://" + request.getServerName();
//				
//				SimpleMailMessage registrationEmail = new SimpleMailMessage();
//				registrationEmail.setTo(business.getEmail());
//				registrationEmail.setSubject("Registration Confirmation");
//				registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
//						+ appUrl + "/confirm?token=" + business.getConfirmationToken());
//				registrationEmail.setFrom("noreply@domain.com");
//				
//				emailService.sendEmail(registrationEmail);
//				
//				modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + business.getEmail());
//				modelAndView.setViewName("register");
//			}
//				
//			return modelAndView;
//		}
		
		// Process confirmation link
		@RequestMapping(value="/confirm", method = RequestMethod.GET)
		public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
				
			Business business = businessService.findByConfirmationToken(token);
				
			if (business == null) { // No token found in DB
				modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
			} else { // Token found
				modelAndView.addObject("confirmationToken", business.getConfirmationToken());
			}
				
			modelAndView.setViewName("confirm");
			return modelAndView;		
		}
		
		// Process confirmation link
		@RequestMapping(value="/confirm", method = RequestMethod.POST)
		public ModelAndView processConfirmationForm(ModelAndView modelAndView, 
				BindingResult bindingResult, 
				@RequestParam Map requestParams, 
				RedirectAttributes redir) {
					
			modelAndView.setViewName("confirm");
			
			Zxcvbn passwordCheck = new Zxcvbn();
			
			Strength strength = passwordCheck.measure((String) requestParams.get("password"));
			
			if (strength.getScore() < 3) {
				bindingResult.reject("password");
				
				redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

				modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
				System.out.println(requestParams.get("token"));
				return modelAndView;
			}
		
			// Find the user associated with the reset token
			Business business = businessService.findByConfirmationToken((String) requestParams.get("token"));

			// Set new password
			business.setPassword((String) requestParams.get("password"));

			// Set user to enabled
			business.setEnabled(true);
			
			// Save user
			businessService.saveBusiness(business);
			
			modelAndView.addObject("successMessage", "Your password has been set!");
			return modelAndView;		
		}
		
//		@RequestMapping(value="/login", method = RequestMethod.GET)
//		public ModelAndView loginBusiness(ModelAndView modelAndView, Business business) {
//
//			modelAndView.setViewName("login");
//			modelAndView.addObject(business);
//
//			return modelAndView;
//		}
//
//		@RequestMapping(value="/login", method = RequestMethod.POST)
//		public ModelAndView home(ModelAndView modelAndView) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			Business business = businessService.findByEmail(auth.getName());
//			modelAndView.addObject("userName", "Welcome " + business.getBizName() +   " (" + business.getEmail() + ")");
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//			modelAndView.setViewName("adminhome");
//			return modelAndView;
//	}
}
	

