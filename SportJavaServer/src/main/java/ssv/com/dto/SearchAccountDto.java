package ssv.com.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Account;

@Getter
@Setter
public class SearchAccountDto {
	private List<Account> account;
	private int page;
	private int pageSize;
	private int totalPage;
	private int total;

	
}
