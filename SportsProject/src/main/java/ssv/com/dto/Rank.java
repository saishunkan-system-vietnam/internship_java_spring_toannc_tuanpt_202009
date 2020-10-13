package ssv.com.dto;

import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Schedule;

@Getter
@Setter
public class Rank {
	private String name;
	public double rank;
	public int idTeam;
	public List<Schedule> list;
	
}
