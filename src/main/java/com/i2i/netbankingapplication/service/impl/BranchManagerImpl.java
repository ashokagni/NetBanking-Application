package com.i2i.netbankingapplication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.netbankingapplication.Constants;
import com.i2i.netbankingapplication.dao.BranchDao;
import com.i2i.netbankingapplication.exception.BranchDataException;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;
import com.i2i.netbankingapplication.service.BranchManager;

@Service("branchManager")
public class BranchManagerImpl extends GenericManagerImpl < Branch, Long > implements BranchManager {

    @Autowired
    BranchDao branchDao;

    @Autowired
    public BranchManagerImpl(BranchDao branchDao) {
        super(branchDao);
        this.branchDao = branchDao;
    }

    /**
     * {@inheritDoc}
     */
    public String addBranch(Branch branch) throws DataBaseException {
        branch.setIFSCode(getIFSCode());
        branchDao.insertBranch(branch);
        return "Branch Added Successfully.. IFSCode : " + branch.getIFSCode();
    }

    /**
     * <p>
     *     this method using for create the IFSCode for each Branch and then any Branch deleted in the list 
     *     it is full fill that the cab.
     * </p>
     * @return customerId
     *     specific IFSCode for each Branch. 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    public String getIFSCode() throws DataBaseException {
        long previousValue = Constants.NOTIFICATION_SIZE;
        long currentValue;
        for (Branch branches: branchDao.retrieveBranches()) {
            String ifsc = branches.getIFSCode();
            currentValue = Integer.parseInt(ifsc.substring(Constants.IFSCODE_SUBSTREAM_SIZE, ifsc.length()));
            if (previousValue <= currentValue) {
                long temp = currentValue - previousValue;
                if (temp == Constants.SIZE || temp == Constants.NOTIFICATION_SIZE) {
                    previousValue = currentValue;
                } else {
                    return (Constants.IFSCODE_PROFIX + String.valueOf(currentValue - Constants.INITIAL_SIZE));
                }
            }
        }
        return (Constants.IFSCODE_PROFIX + String.valueOf(previousValue + Constants.INITIAL_SIZE));
    }

    /**
     * {@inheritDoc}
     */
    public List < Branch > getBranches() throws DataBaseException {
        return branchDao.retrieveBranches();
    }

    /**
     * {@inheritDoc}
     */
    public Branch getBranchByIFSCode(String IFSCode) throws DataBaseException {
        return branchDao.retrieveBranchByIFSCode(IFSCode);
    }

    /**
     * {@inheritDoc}
     */
    public void removeBranchByIFSCode(String IFSCode) throws DataBaseException {
        branchDao.removeBranchByIFSCode(IFSCode);
    }

    /**
     * <p>
     *    This method verify the branch IFSCode exist or not.
     *    If branch exist add new account.
     *    Otherwise, return the error message.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account.
     * @param balance
     *     balance of Account.
     * @param accounttype
     *     accountType of Account.
     * @param ifsc
     *     IfsCode of Account.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    public String addAccount(Account account) throws DataBaseException {
        //verify the branch id exist or not.
        branchDao.insertAccount(account);
        return ("ACCOUNT ADDED SUCCESSFULLY");
    }

    public Account getAccountByAccountNumber(String accountNumber) throws DataBaseException {
        System.out.println();
        return branchDao.retrieveAccountByAccountNumber(accountNumber);
    }

    /**
     * <p> 
     *     This method use to view account by branch.
     *     This method get the branch IFSC from branch controller. 
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of Account to use view list of accounts.
     *     
     * @return List.
     *     return the list of accounts.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     * @throws BranchDataException 
     *     It handle all the custom exception.
     */
    public List < Account > viewAccountByBranch(String IFSCode) throws DataBaseException, BranchDataException {
        List < Account > accounts = new ArrayList < Account > ();
        if (null != branchDao.retrieveBranchByIFSCode(IFSCode)) {
            for (Account account: branchDao.retriveAllAccounts()) {
                if (account.getBranch().getIFSCode().equals(IFSCode)) {
                    accounts.add(account);
                }
            }
            return accounts;
        } else {
            throw new BranchDataException("PLEASE ENTER VALID IFSC NUMBER");
        }
    }
}
