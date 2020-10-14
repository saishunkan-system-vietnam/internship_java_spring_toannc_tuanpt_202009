package ssv.com.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;

@Getter
@Setter
public class Rank {
	private String name;
	public double rank;
	public int numberRank;
	public int idTeam;
	public List<Schedule> list;
	public Team team;
	
}
