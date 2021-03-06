package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Team;
import ssv.com.mapper.TeamMapper;

@Repository
public class TeamRepository {

	@Autowired
	private TeamMapper teamMapper;

	public List<Team> getTeams() {
		return teamMapper.getTeams();
	}

	public Long createTeam(Team team) {
		return teamMapper.createTeam(team);
	}

	public Team getTeamById(int idTeam) {
		return teamMapper.getTeamById(idTeam);
	}

	public List<Team> getTeamNoTournament() {
		return teamMapper.getTeamNoTournament();
	}

	public void newTournament(Integer idTeam, int idTournament) {
		teamMapper.newTournament(idTeam, idTournament);
	}

	public void formatTournament(int idTournament) {
		teamMapper.formatTournament(idTournament);
	}

	public void updateTeam(int id, Team team) {
		teamMapper.updateTeam(id, team);
	}

	public void formatTourTeam(int idTeam) {
		teamMapper.formatTourTeam(idTeam);
	}

	public void createTournament(int idTeam, int idTournament) {
		teamMapper.createTournament(idTeam,idTournament);

	}

	public Team getHistory(int idTour, int idTeam, int idSchedule) {
		// TODO Auto-generated method stub
		return teamMapper.getHistory(idTour,idTeam,idSchedule);
	}
}
