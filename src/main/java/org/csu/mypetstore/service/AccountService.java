package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account getAccount(String username) {
        return accountMapper.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }

    @Transactional
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        accountMapper.insertProfile(account);
        accountMapper.insertSignon(account);
    }

    @Transactional
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
        accountMapper.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountMapper.updateSignon(account);
        }
    }

    public List<Account> getAllAccount()
    {
        return accountMapper.getAllAccount();
    }

    @Transactional
    public void passwordReset(String username)
    {
        Account account = getAccount(username);
        if(account!=null)
        {
            account.setPassword("000000");
            updateAccount(account);
        }
        else
        {
            System.out.println("account is null");
        }
    }

    @Transactional
    public void deleteAccount(String username)
    {
        accountMapper.deleteAccount(username);
        accountMapper.deleteProfile(username);
        accountMapper.deleteSignon(username);
    }

    public List<Account> searchAccount(String keywords)
    {
        return accountMapper.searchAccount("%"+keywords.toLowerCase()+"%");
    }
}
