package ssv.com.repository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Profile;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;
import ssv.com.mapper.TeamMapper;

@Repository
public class TeamRepository {

	@Autowired
	private TeamMapper teamMapper;

	public List<Team> getAll() {
		// TODO Auto-generated method stub
		return teamMapper.getAll();
	}

	public Team getByID(int id) {
		// TODO Auto-generated method stub
		return teamMapper.getByID(id);
	}

	public void save(Team team) {
		teamMapper.save(team);
	}


	public void updateTournew(int id) {
		teamMapper.updateTourNew(id);

	}

	public void formatTour(int idTeam) {
		teamMapper.formatTour(idTeam);

	}

	public void addMember(Profile profile, int idTeam) {
		teamMapper.addMember(profile.getId(),idTeam);

	}

	public void updateTour(int idTour, int idTeam) {
		teamMapper.updateTour(idTeam,idTour);

	}

	public void deleteMember(Profile profile) {
		teamMapper.deleteMember(profile.getId());

	}

	public Set<Schedule> selectAllMatchsByTeam(int id){
		return teamMapper.selectAllMatchsByTeam(id);
	}

	public Set<Schedule> selectAllMatchsWinByTeam(int id){
		return teamMapper.selectAllMatchsByTeam(id);
	}

	public List<Team> getByTour(int idTournamet) {
		// TODO Auto-generated method stub
		return teamMapper.getByTour(idTournamet);
	}
}
