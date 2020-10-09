package ssv.com.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankTeamsByType {
	private String type;
	private List<Rank> list;
}
