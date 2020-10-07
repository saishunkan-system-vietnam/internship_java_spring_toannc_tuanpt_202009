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

		return accountMapper.findAll();
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

	public Account findById(int id) {
		return accountMapper.findById(id);
	}

	public List<Account> search(int page, int pageSize, String name, String type) {
		return accountMapper.search(page, pageSize, name, type);
	}

	public List<Account> searchTotal(String name, String type) {
		return accountMapper.searchTotal(name, type);
	}

}
