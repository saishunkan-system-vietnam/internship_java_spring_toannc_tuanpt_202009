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

	public Team findById(int id) {
		return teamMapper.findById(id);
	}

	public void save(Team team) {
		teamMapper.save(team);
	}


	public void updateTournew(int id) {
		teamMapper.updateTourNew(id);

	}

	public void formatTour(int idTour) {
		teamMapper.formatTour(idTour);

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

	public int searchTotal(String nameSearch, String type) {
		return teamMapper.searchTotal(nameSearch,type);
	}

	public List<Team> search(int page, int pageSize, String nameSearch, String type, String sorts) {


		return teamMapper.search(page,pageSize,nameSearch,type,sorts);
	}

	public List<Team> teamWait(String type) {
		// TODO Auto-generated method stub
		return teamMapper.teamWait(type);
	}

	public void formatTourById(int idTour, int idTeam) {
		teamMapper.formatTourById(idTour,idTeam);
	}

	public void addTour(int idTour, int idTeam) {
		teamMapper.addTour(idTour,idTeam);
	}
}
