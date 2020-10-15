package ssv.com.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;
import ssv.com.entity.Tournament;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryMemberDto {
	private int id_memberHistoryDto;
	private int idMember;
	private int idTeam;
	private Tournament tournaments;
	private List<Schedule> schedules;
}
