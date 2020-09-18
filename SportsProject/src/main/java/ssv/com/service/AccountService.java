package ssv.com.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import ssv.com.entity.Account;
import ssv.com.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	

	  public Account loadUserByUsername(String username) {
	    for (Account account : accountRepository.findAll()) {
	      if (account.getUsername().equals(username)) {
	        return account;
	      }
	    }
	    return null;
	  }


	public boolean checkEmail(String email) {
		 for (Account account : accountRepository.findAll()) {
		      if (account.getEmail().equals(email)) {
		        return false;
		      }
		    }
		    return true;
	}


	public void add(Account acount) {
		accountRepository.add(acount);
		
	}


	public boolean checkLogin(Account account) {
		 for (Account accountExits : accountRepository.findAll()) {
		      if (StringUtils.equals(account.getUsername(), accountExits.getUsername())
		              && BCrypt.checkpw(account.getPassword(), accountExits.getPassword())) {
		        return true;
		      }
		    }
		    return false;
	}


	public void replacePass(Account account) {
		accountRepository.replacePass(account);
		
	}


	public boolean checkid(long id) {
		 for (Account account : accountRepository.findAll()) {
		      if (account.getId()==id) {
		        return true;
		      }
		    }
		    return false;
	}


	public void delete(int id) {
		accountRepository.delete(id);
		
	}


	public List<Account> findAll() {
		return accountRepository.findAll();
	}


	public List<Account> pageUser(int page, int pagesize,String name,String nametype) {
		return accountRepository.pageUser(page,pagesize,name,nametype);
	}


	public boolean checkUser(Account acount) {
		 for (Account account : accountRepository.findAll()) {
		      if (account.getUsername().equals(acount.getUsername())) {
		        return true;
		      }
		    }
		    return false;
	}


	public int total() {
		
		return accountRepository.findAll().size();
	}


	public int total(String name) {
		
		return accountRepository.findName(name).size();
	}
}
