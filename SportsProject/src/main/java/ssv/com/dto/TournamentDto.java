package ssv.com.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentDto {
	private String nameTour;
	private Date timeEnd;
	private Date timeStart;
	private String type;
	private String title;
	private int[] listIdTeam;
}
