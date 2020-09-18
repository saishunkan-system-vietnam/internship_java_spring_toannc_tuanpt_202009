package ssv.com.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	private int idschedule;
	private Date timeEnd;
	private Date timeStart;
	private int idTeam1;
	private int idTeam2;
	private int scoreTeam1;
	private int scoreTeam2;
	private String address;
	private int idTour;
	private int idwinner;
	private String video;
	private int status;
	private String description;
}
