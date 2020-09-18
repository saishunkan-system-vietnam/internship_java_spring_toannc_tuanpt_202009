package ssv.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssv.com.entity.Profile;
import ssv.com.entity.Team;
import ssv.com.entity.Tournament;
import ssv.com.repository.HistoryRepository;
import ssv.com.repository.TeamRepository;
import ssv.com.repository.TournamentRepository;

@Service
public class TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private TeamService teamService;

	@Autowired
	private HistoryRepository historyRepository;

	public void add(Tournament tournament) {
		if (tournament.getTimeEnd().compareTo(tournament.getTimeStart()) > 0) {
			tournamentRepository.add(tournament);
			for (Team teamExit : tournament.getTeam()) {
				teamService.updateTourNew(teamExit.getIdTeam());
				for (Profile profile : teamService.getById(teamExit.getIdTeam()).getProfile()) {
					historyRepository.addTournament(profile.getId(), teamExit.getIdTeam());
				}
			}
		} else {
			
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

	public void deleteTeam(int idTour, Team team) {
		teamService.formatTour(team.getIdTeam());
		historyRepository.deleteTeamTournament(idTour, team.getIdTeam());

	}

	public void updateStatus(int idTour, int status) {
		tournamentRepository.updateStatus(idTour, status);

	}

}
