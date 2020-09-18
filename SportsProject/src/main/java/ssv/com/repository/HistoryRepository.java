package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.History;
import ssv.com.entity.Profile;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;
import ssv.com.mapper.HistoryMapper;

@Repository
public class HistoryRepository {


	@Autowired
	private  HistoryMapper historyMapper;

	public void addTournament(Long idMember, int idTeam) {
		historyMapper.addTournament( idMember, idTeam);

	}

	public void deleteTournament(int idTour) {
		historyMapper.deleteTournament(idTour);

	}

	public void addTeamTournament(Long idMember, int idTour, int idTeam) {
		historyMapper.addTeamTournament(idMember,idTour,idTeam);

	}

	public void deleteTeamTournament(int idTour, int idTeam) {

		historyMapper.deleteTeamTournament(idTour,idTeam);

	}






}


