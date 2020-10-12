package ssv.com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginateDto<T> {
	private Integer totalPage;
	private Integer currentPage;
	private T data;
}
