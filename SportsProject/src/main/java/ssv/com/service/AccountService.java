package ssv.com.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssv.com.dto.ResponseQuery;
import ssv.com.dto.SearchAccountDto;
import ssv.com.entity.Account;
import ssv.com.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account loadUserByUsername(String username) {
		for (Account account : accountRepository.findAll()) {
			if (account.getUsername().equals(username)) {
				return accountRepository.findById(account.getId());
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
			if (account.getId() == id) {
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

	public boolean checkUser(Account acount) {
		for (Account account : accountRepository.findAll()) {
			if (account.getUsername().equals(acount.getUsername())) {
				return false;
			}
		}
		return true;
	}

	public Account findById(int id) {
		return accountRepository.findById(id);
	}

	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}


	public SearchAccountDto search(int page, int pageSize, String name, String type) {
		SearchAccountDto accountDto = new SearchAccountDto();
		accountDto.setTotal(accountRepository.searchTotal(name, type).size());
		accountDto.setPage(page);
		accountDto.setPageSize(pageSize);
		if (accountDto.getTotal() % pageSize == 0) {
			accountDto.setTotalPage(accountDto.getTotal() / pageSize);
		} else {
			accountDto.setTotalPage((accountDto.getTotal() / pageSize) + 1);
		}

		accountDto.setAccount(accountRepository.search((page - 1) * pageSize, pageSize, name, type));

		return accountDto;
	}

	public Account findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}
}
