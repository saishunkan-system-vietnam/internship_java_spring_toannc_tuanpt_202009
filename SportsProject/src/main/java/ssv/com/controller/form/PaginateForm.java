package ssv.com.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginateForm {

	private Integer limit = 10;
	private Integer currentPage = 1;

	public Integer getOffset() {
		return limit * (currentPage - 1);
	}
}
