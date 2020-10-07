package ssv.com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
	private int idHistory;
	private int idMember;
	private int idTeam;
	private int idTour;
}
