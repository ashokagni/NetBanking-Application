package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;
import com.i2i.netbankingApplication.service.BranchService;

@Controller
public class BranchController {
	BranchService branchService = new BranchService();
	
	@RequestMapping(value = "/BranchIndex")
	public String login() {
		return "BranchIndex";
	}
	
	@RequestMapping(value = "/addBranch")
	public String getBranch(ModelMap model) {
		model.addAttribute("Branch", new Branch());
		return "AddBranch";
	}
	
	@RequestMapping(value="/insertBranch", method = RequestMethod.POST)
    public String addBranch(@RequestParam("emailId") String emailId, ModelMap message) {  
		try {
			branchService.getBranch(emailId);
            message.addAttribute("Address1", new Address());
            return "AddAddress";
		} catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
    		return "BranchIndex";
        } 
		
    }
	
	@RequestMapping(value="/address", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("address1") Address address, ModelMap message) {  
		try {
			message.addAttribute("message","Branch added suceess. Branch IFSC is :: " + branchService.getAddress(address));
            return "BranchIndex";
		} catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
    		return "BranchIndex";
        }
    }
	
	@RequestMapping(value="/deleteBranchById", method = RequestMethod.GET)
    public String deleteBranch(@RequestParam("ifsc")String ifsc, ModelMap message) throws DataBaseException {
    	try {       
            branchService.deleteBranchById(ifsc);
            message.addAttribute("message", "BRANCH DELETED SUCESSFULLY");
            message.addAttribute("branches", branchService.getAllBranch());
            return "GetBranch";
    	} catch (DataBaseException e) {
    		message.addAttribute("message", e.getMessage()); 
    		message.addAttribute("branches", branchService.getAllBranch());
            return "GetBranch";
        }
    }
	
	@RequestMapping(value = "/GetBranch")
	public String getBranchById() {
		return "GetBranch";
	}
	
	@RequestMapping(value="/getBranch", method = RequestMethod.GET)  
    public ModelAndView viewBranchById (@RequestParam("ifsc")String ifsc, ModelMap message) {
        try {
        	if (ifsc.equals("all") || ifsc.equals("All") || ifsc.equals("ALL")) {
        		return new ModelAndView ("GetBranch", "branches", branchService.getAllBranch());
        	} else  {
        		Branch branch = branchService.getBranchById(ifsc);
        		if (branch != null) {
                    return new ModelAndView("GetBranch","branch", branch);
        		} else {
        			return new ModelAndView("GetBranch","message", "ENTER VALID IFSC ONLY");
        		}
        	}
        } catch (DataBaseException e) {
        	return new ModelAndView("GetBranch","message",  e.getMessage());
        }
    }
	
	@RequestMapping(value="/viewBranchAddress", method = RequestMethod.GET)
    public ModelAndView viewAddressById(@RequestParam("addressId")int addressId, ModelMap message) {
    	try {                     
            return new ModelAndView ("RetrieveAddressById", "address", branchService.getAddressById(addressId)); 
    	} catch (DataBaseException e) {
    		return new ModelAndView ("RetrieveAddressById", "message", e.getMessage());
        }
	}
	
	@RequestMapping(value = "/AddAccount")
	public String getAccount() {
		return "AddAccount";
	}
	
	@RequestMapping(value="/addAccount", method = RequestMethod.POST)  
    public String getAccount(@RequestParam("accountNumber")String accountNumber, @RequestParam("balance")String balance, @RequestParam("accounttype")String accounttype, @RequestParam("ifscode")String ifsc, ModelMap message) throws  DataBaseException {
		try { 
			message.addAttribute("message", branchService.getAccount(accountNumber, Double.parseDouble(balance), accounttype, ifsc));
		}catch(DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
	     	return "BranchIndex";
		}
	}
	
	@RequestMapping(value = "/ViewAccountByBranch")
	public String viewAccountByBranch() {
		return "ViewAccountByBranch";
	}
	
	@RequestMapping(value="/getAccount", method = RequestMethod.GET)  
    public String viewAccountByBranch (@RequestParam("ifsc")String ifsc, ModelMap message) {
		try { 
			System.out.println(ifsc);
			message.addAttribute("accounts", branchService.viewAccountByBranch(ifsc));
		}catch(DataBaseException e) {
			message.addAttribute("message", e.getMessage().toString());
		} finally {
	     	return "ViewAccountByBranch";
		}
	}
	
}