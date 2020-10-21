package ssv.com.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ssv.com.controller.form.PaginateForm;
import ssv.com.controller.form.TeamForm;
import ssv.com.dto.Rank;
import ssv.com.dto.ScheduleDto;
import ssv.com.dto.TeamDetail;
import ssv.com.dto.TeamDto;
import ssv.com.entity.Profile;
import ssv.com.entity.Schedule;
import ssv.com.entity.Team;
import ssv.com.file.UploadFile;
import ssv.com.mapper.TeamMapper;
import ssv.com.repository.ScheduleReponsitory;
import ssv.com.repository.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private ScheduleReponsitory scheduleReponsitory;

	@Autowired
	private ModelMapper modelMapper;

	public List<Team> getAll() {
		List<Team> list = teamRepository.getAll();
		for (Team team : list) {
			if (team.getIdTour() != 0) {
				team.setTourName(tournamentService.getById(team.getIdTour()).getNameTour());
			}
			team.setTotalmatch(scheduleReponsitory.sum(team.getIdTeam()));
			team.setTotalwin(scheduleReponsitory.sumWin(team.getIdTeam()));
		}
		return list;
	}

	public Team getById(int id) {
		Team team = teamRepository.getByID(id);
		if (team.getIdTour() != 0) {
			team.setTourName(tournamentService.getById(team.getIdTour()).getNameTour());
		}		team.setTotalmatch(scheduleReponsitory.sum(team.getIdTeam()));
		team.setTotalwin(scheduleReponsitory.sumWin(team.getIdTeam()));
		return team;
	}

	public Team findById(int id) {
		Team team = teamRepository.findById(id);
		if(team.getIdTour()!=0) {
			team.setTourName(tournamentService.getById(team.getIdTour()).getNameTour());

		}

		team.setTotalmatch(scheduleReponsitory.sum(team.getIdTeam()));
		team.setTotalwin(scheduleReponsitory.sumWin(team.getIdTeam()));
		return team;
	}

	public void save(Team team) {
		teamRepository.save(team);

	}

	public void updateTourNew(int id) {
		teamRepository.updateTournew(id);

	}

	public void formatTour(int idTour) {
		teamRepository.formatTour(idTour);
	}

	public boolean checkTeam(Team team) {
		for (Team teamExits : teamRepository.getAll()) {
			if (teamExits.getNameTeam().equals(team.getNameTeam())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkMember(Profile profile, int idTeam) {
		if (teamRepository.getByID(idTeam).getIdTour() == 0 && profile.getIdTeam() == 0) {
			return true;
		}
		return false;
	}

	public void addMember(Profile profile, int idTeam) {
		teamRepository.addMember(profile, idTeam);
	}

	public void deleteMember(Profile profile) {
		teamRepository.deleteMember(profile);
	}

	public boolean checkMemberTeam(Profile profile, int idTeam) {
		if (teamRepository.getByID(idTeam).getIdTour() == idTeam && teamRepository.getByID(idTeam).getIdTour() == 0) {
			return true;
		}
		return false;
	}

	public void updateTotalMatch() {
		List<Team> teams = teamRepository.getAll();
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
		TeamDto dto = new TeamDto();
		dto.setTotal(teamRepository.searchTotal(nameSearch, type));
		dto.setPage(page);
		dto.setPageSize(pageSize);
		if (dto.getTotal() % pageSize == 0) {
			dto.setTotalPage(dto.getTotal() / pageSize);
		} else {
			dto.setTotalPage((dto.getTotal() / pageSize) + 1);
		}
		dto.setTeam(teamRepository.search((page - 1) * pageSize, pageSize, nameSearch, type, sorts));
		return dto;
	}

	public TeamDetail detail(int idTeam, int idTour) {
		TeamDetail detail = new TeamDetail();
		detail.setSum(scheduleReponsitory.sum(idTeam));
		detail.setSumWin(scheduleReponsitory.sumWin(idTeam));
		detail.setSumJoinByTour(scheduleReponsitory.sumJoinByTour(idTeam, idTour));
		detail.setSumWinJoinByTour(scheduleReponsitory.sumWinJoinByTour(idTeam, idTour));
		if (detail.getSum() == 0) {
			detail.setRate(0);
		} else {
			detail.setRate(detail.getSumWin() * 100 / detail.getSum());

		}
		return detail;
	}

	public List<Team> teamWait(String type) {
		// TODO Auto-generated method stub
		return teamRepository.teamWait(type);
	}

	public void formatTourById(int idTour, int idTeam) {
		teamRepository.formatTourById(idTour, idTeam);

	}

	public Team teamTourHistory(int idTeam, int idTour) {
		Team team=teamRepository.teamTourHistory(idTeam, idTour);
		for (Profile profile : team.getProfile()) {
			profile.setId(profileService.findByEmail(profile.getEmail()).getId());
		};
		return team;
	}

	public void addTour(int idTour, int idTeam) {

		teamRepository.addTour(idTour, idTeam);

	}

	public void formatTotalMatch(int idTour) {
		teamRepository.formatTotalMatch(idTour);

	}

	public void updateTeam(int id, Team team) {
		teamRepository.updateTeam(id, team);
	}

	public List<Team> getAllByType(String type) {
		// TODO Auto-generated method stub
		return teamRepository.getAllByType(type);
	}

	public double rank(int i) {
		if(scheduleReponsitory.sum(i)==0) {
			return 0;
		}
		return Double.parseDouble(
				new DecimalFormat("##.##").format((scheduleReponsitory.sumWin(i) * 1.0) / scheduleReponsitory.sum(i)));
	}

	public double rankByTour(int idTeam, int idTour) {
		if (scheduleReponsitory.sumJoinByTour(idTeam, idTour) == 0) {
			return 0;
		}

		return Double
				.parseDouble(new DecimalFormat("##.##").format((scheduleReponsitory.sumWinJoinByTour(idTeam, idTour)
						* 1.0 / scheduleReponsitory.sumJoinByTour(idTeam, idTour))));
	}

	public List<Team> search(PaginateForm form) {
		return teamRepository.search(form);
	}

	public Integer getCountAll() {
		return teamRepository.getCountAll();
	}
	//
	public Team findTeamByTour(int idTeam, int idTour) {
		Team team =teamRepository.findById(idTeam);
		team.setTotalmatch(scheduleReponsitory.sumJoinByTour(idTeam, idTour));
		team.setTotalwin(scheduleReponsitory.sumWinJoinByTour(idTeam, idTour));
		return team;
	}
}
