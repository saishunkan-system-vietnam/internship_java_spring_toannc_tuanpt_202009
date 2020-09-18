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

	public List<Account> pageUser(int page, int pagesize,String name,String nametype);

	public List<Account> findName(String name);

	public List<Account> getMembersEmailByRole();

}
