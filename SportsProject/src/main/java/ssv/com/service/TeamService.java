package ssv.com.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssv.com.dto.ScheduleDto;
import ssv.com.dto.TeamDto;
import ssv.com.entity.Profile;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;
import ssv.com.mapper.TeamMapper;
import ssv.com.repository.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;



	public List<Team> getAll() {
		return teamRepository.getAll();
	}

	public Team getById(int id) {
		return teamRepository.getByID(id);
	}

	public void save(Team team) {
		teamRepository.save(team);

	}


	public void updateTourNew(int id) {
		teamRepository.updateTournew(id);

	}

	public void formatTour(int idTeam) {
		teamRepository.formatTour(idTeam);
	}

	public boolean checkTeam(Team team) {
		for (Team teamExits : teamRepository.getAll()) {
			if(teamExits.getNameTeam().equals(team.getNameTeam())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkMember(Profile profile, int idTeam) {

		if(teamRepository.getByID(idTeam).getIdTour()==0&&profile.getIdTeam()==0) {
			return true;
		}

		return false;
	}

	public void addMember(Profile profile, int idTeam) {
		teamRepository.addMember(profile,idTeam);
	}

	public void deleteMember(Profile profile) {
		teamRepository.deleteMember(profile);
	}

	public boolean checkMemberTeam(Profile profile, int idTeam) {
		if(teamRepository.getByID(idTeam).getIdTour()==idTeam &&teamRepository.getByID(idTeam).getIdTour()==0) {
			return true;
		}
		return false;
	}

	public void updateTotalMatch(){
		List<Team> teams =  teamRepository.getAll();
		for (Team team : teams) {
			team.setTotalmatch(teamRepository.selectAllMatchsByTeam(team.getIdTeam()).size());
			team.setTotalwin(teamRepository.selectAllMatchsWinByTeam(team.getIdTeam()).size());
			teamRepository.save(team);
		}
	}

	public List<Team> getByTour(int idTournamet) {
		return teamRepository.getByTour(idTournamet);
	}

	public TeamDto search(int page, int pageSize, String nameSearch, String type, String sorts) {
		TeamDto dto=new TeamDto();
		dto.setTotal(teamRepository.searchTotal(nameSearch,type));
		dto.setPage(page);
		dto.setPageSize(pageSize);
		if(dto.getTotal()%pageSize==0) {
			dto.setTotalPage(dto.getTotal()/pageSize);
		}
		else {
			dto.setTotalPage((dto.getTotal()/pageSize)+1);
		}
		dto.setTeam(teamRepository.search((page-1)*pageSize,pageSize,nameSearch,type,sorts));
		return dto;
	}
}
