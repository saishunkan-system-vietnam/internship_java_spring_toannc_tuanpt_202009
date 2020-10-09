package ssv.com.entity;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {
	private int idTour;
	private String nameTour;
	private int status;
	private Date timeEnd;
	private Date timeStart;
	private String type;
	private String description;
	private String title;
	private List<Schedule> schedule;
	private List<Team> team;
	private int winner;
}
