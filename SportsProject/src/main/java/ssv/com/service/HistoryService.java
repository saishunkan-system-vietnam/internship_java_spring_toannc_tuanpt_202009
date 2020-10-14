package ssv.com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssv.com.dto.HistoryMemberDto;
import ssv.com.entity.Team;
import ssv.com.repository.HistoryRepository;
import ssv.com.repository.TeamRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private TeamRepository teamRepository;

//	public List<HistoryMemberDto> memberHistoryMatchs(int idMember) {
//		List<HistoryMemberDto> result = historyRepository.memberHistoryMatchs(idMember);
//		for (HistoryMemberDto historyMemberDto : result) {
//			historyMemberDto.getTournaments().forEach(s -> {
//				s.getSchedule().forEach(t -> {
//					List<Team> teams = new ArrayList<Team>();
//					teams.add(teamRepository.getByID(t.getIdTeam1()));
//					teams.add(teamRepository.getByID(t.getIdTeam2()));
//					t.setTeam(teams);
//				});
//			});
//		}
//
//		return result;
//	}

	public List<HistoryMemberDto> memberHistoryMatchs(int idMember) {
		List<HistoryMemberDto> result = historyRepository.memberHistoryMatchs(idMember);
		return result;
	}
}
