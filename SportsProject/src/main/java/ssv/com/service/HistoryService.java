package ssv.com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssv.com.dto.HistoryMemberDto;
import ssv.com.entity.Team;
import ssv.com.entity.Tournament;
import ssv.com.repository.HistoryRepository;
import ssv.com.repository.TeamRepository;
import ssv.com.repository.TournamentRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private TeamRepository teamRepository;

	public List<HistoryMemberDto> memberHistoryMatchs(int idMember) {
		List<HistoryMemberDto> result = historyRepository.memberHistoryMatchs(idMember);
		for (HistoryMemberDto historyMemberDto : result) {
			historyMemberDto.getSchedules().forEach(s -> {
				s.setNameTour(tournamentRepository.getById(s.getIdTour()).getNameTour());
				s.setNameTeam1(teamRepository.getByID(s.getIdTeam1()).getNameTeam());
				s.setNameTeam2(teamRepository.getByID(s.getIdTeam2()).getNameTeam());
				s.setTeamPlayed(historyMemberDto.getIdTeam());
			});
		}
		return result;
	}

	public List<HistoryMemberDto> playerUpCommingMatchs(int idMember) {
		List<HistoryMemberDto> result = historyRepository.playerUpCommingMatchs(idMember);
		for (HistoryMemberDto historyMemberDto : result) {
			historyMemberDto.getSchedules().forEach(s -> {
				s.setNameTour(tournamentRepository.getById(s.getIdTour()).getNameTour());
				s.setNameTeam1(teamRepository.getByID(s.getIdTeam1()).getNameTeam());
				s.setNameTeam2(teamRepository.getByID(s.getIdTeam2()).getNameTeam());
				s.setTeamPlayed(historyMemberDto.getIdTeam());
			});
		}
		return result;
	}
}





//public List<HistoryMemberDto> memberHistoryMatchs(int idMember) {
//List<HistoryMemberDto> result = historyRepository.memberHistoryMatchs(idMember);
//for (HistoryMemberDto historyMemberDto : result) {
//	historyMemberDto.getTournaments().forEach(s -> {
//		s.getSchedule().forEach(t -> {
//			List<Team> teams = new ArrayList<Team>();
//			teams.add(teamRepository.getByID(t.getIdTeam1()));
//			teams.add(teamRepository.getByID(t.getIdTeam2()));
//			t.setTeam(teams);
//		});
//	});
//}
//
//return result;
//}