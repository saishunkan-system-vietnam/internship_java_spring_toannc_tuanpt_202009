package ssv.com.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import ssv.com.dto.TournamentDto;
import ssv.com.entity.Profile;
import ssv.com.entity.Team;
import ssv.com.entity.Tournament;
import ssv.com.repository.HistoryRepository;
import ssv.com.repository.TeamRepository;
import ssv.com.repository.TournamentRepository;

@Service
public class TournamentService {
	 ModelMapper modelMapper = new ModelMapper();


	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private TeamService teamService;
	
	@Autowired 
	private ProfileService profileService;

	@Autowired
	private HistoryRepository historyRepository;

	public String add(TournamentDto tournamentDto) {
		if (tournamentDto.getTimeEnd().compareTo(tournamentDto.getTimeStart()) > 0) {
			Tournament tournament=modelMapper.map(tournamentDto, Tournament.class);
			tournamentRepository.add(tournament);
			for (int i = 0; i < tournamentDto.getListIdTeam().length; i++) {
				teamService.updateTourNew(tournamentDto.getListIdTeam()[i]);
				profileService.newTour(tournamentDto.getListIdTeam()[i]);
			}
			return "thanh cong";
		} else {
			return "Lỗi về thời gian";
		}
	}

	public List<Tournament> getAll() {
		return tournamentRepository.getAll();
	}

	public Tournament getById(int idTour) {
		return tournamentRepository.getById(idTour);
	}

	public void delete(int idTour) {
		tournamentRepository.delete(idTour);
		teamService.formatTour(idTour);
		historyRepository.deleteTournament(idTour);
		}

	public void updateInfo(Tournament tournament) {
		tournamentRepository.updateInfo(tournament);

	}

	public void addTeam(int idTour, Team team) {
		for (Profile profile : team.getProfile()) {
			historyRepository.addTeamTournament(profile.getId(), team.getIdTeam(), idTour);
		}
	}

	public void updateStatus(int idTour, int status) {
		tournamentRepository.updateStatus(idTour, status);

	}

	public List<Tournament> getTourAction() {
		// TODO Auto-generated method stub
		return tournamentRepository.getTourAction();

}
}
