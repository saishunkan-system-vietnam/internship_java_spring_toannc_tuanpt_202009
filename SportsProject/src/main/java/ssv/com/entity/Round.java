package ssv.com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Round {
	private int idRound;
	private String roundName;
	private int roundScore1;
	private int roundScore2;
	private String description;
	private int roundSchedule;

}
