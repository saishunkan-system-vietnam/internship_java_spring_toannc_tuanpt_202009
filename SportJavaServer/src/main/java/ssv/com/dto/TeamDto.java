package ssv.com.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Team;

@Getter
@Setter
public class TeamDto {
	private List<Team> team;
	private int page;
	private int pageSize;
	private int totalPage;
	private int total;
}
