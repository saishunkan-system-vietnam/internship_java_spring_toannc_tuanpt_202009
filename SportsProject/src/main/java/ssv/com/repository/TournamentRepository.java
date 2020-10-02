package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.val;
import ssv.com.entity.Team;
import ssv.com.entity.Tournament;
import ssv.com.mapper.TournamentMapper;

@Repository
public class TournamentRepository {
	
	@Autowired
	private TournamentMapper tournamentMapper;

	public void add(Tournament tournament) {
		 tournamentMapper.add(tournament);
	}

	public List<Tournament> getAll() {
		return tournamentMapper.getAll();
	}

	public Tournament getById(int idTour) {
		return tournamentMapper.getById(idTour);
	}

	public void delete(int idTour) {
		tournamentMapper.delete(idTour);
		
	}

	
	public void updateStatus(int idTour, int status) {
		tournamentMapper.updateStatus(idTour,status);
		
	}

	public List<Tournament> getTourAction() {
		// TODO Auto-generated method stub
		return tournamentMapper.getTourAction();
	}

	public void edit(Tournament tournament) {
		tournamentMapper.edit(tournament);
		
	}



}
