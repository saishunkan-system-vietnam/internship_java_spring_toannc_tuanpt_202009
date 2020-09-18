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
	private int id_history;
	private int id_member;
	private int id_team;
	private int id_tour;
}
