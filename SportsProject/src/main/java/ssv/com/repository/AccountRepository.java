package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Account;
import ssv.com.mapper.AccountMapper;

@Repository
public class AccountRepository {
	
	@Autowired
	private AccountMapper accountMapper;

	public List<Account> findAll() {
		
		return accountMapper.findAll() ;
	}

	public void add(Account acount) {
		accountMapper.add(acount);
		
	}

	public void replacePass(Account account) {
		accountMapper.replace(account);
		
	}

	public void delete(int id) {
		accountMapper.delete(id);
		
	}

	public List<Account> pageUser(int page, int pagesize,String name,String nametype) {
		return accountMapper.pageUser(page*pagesize,pagesize,name,nametype);
	}

	public List<Account> findName(String name) {
		
		return accountMapper.findName(name);
	}

	

}
