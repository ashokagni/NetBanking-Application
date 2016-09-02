package com.i2i.netbankingApplication.service;

import java.util.List;

import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.model.Branch;

public class BranchService {
    BranchDao branchDao = new BranchDao();
    
    public void getBranch(Branch branch) {
      	int tempIFS;
      	String IFSCode = " ";
      	tempIFS = getLastIFSCode();
        if (tempIFS != 0) {
        	IFSCode = "I2I0BK" + String.valueOf(tempIFS + 1);
        } else {
        	IFSCode = "I2I0BK0"; 
        }
    	branchDao.addBranch(branch);
    }
    
    public List<Branch> getAllBranchs() {
    	return branchDao.retriveBranchs();
    }
    
    public int getLastIFSCode() {
    	int lastIFSC = 0;
    	String IFSC = " ";
        int temp;
    	if (branchDao.retriveBranchs().size() == 0) {
    		return lastIFSC;
    	} else {
    		for (Branch branch : branchDao.retriveBranchs()) {
    		     IFSC = branch.getIFSCode();
                 temp = Integer.parseInt(IFSC.substring(6, IFSC.length()));
                 if (lastIFSC < temp) {
                	 lastIFSC = temp;
                 }
    		}
    		return lastIFSC;
    	}
    }
}
    

