package ssv.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.Account;

@Mapper
public interface  AccountMapper {

	public List<Account> findAll();

	public void add(Account acount);

	public void replace(Account account);

	public void delete(int id);

	public List<Account> getMembersEmailByRole();

	public Account findById(int id);

	public List<Account> search(int page, int pageSize, String name, String type);

	public  List<Account> searchTotal(String name, String type);

}
