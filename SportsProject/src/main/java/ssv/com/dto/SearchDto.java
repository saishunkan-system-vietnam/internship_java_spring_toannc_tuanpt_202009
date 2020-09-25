package ssv.com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
	private int page;
	private int pageSize;
	private String typeSearch;
	private String nameSearch;
	private String sort;
}
