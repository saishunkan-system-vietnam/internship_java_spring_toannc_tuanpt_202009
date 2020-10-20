package ssv.com.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.var;
import ssv.com.dto.Rank;
import ssv.com.dto.RankTeamsByType;
import ssv.com.dto.TournamentDto;
import ssv.com.dto.ToursByType;
import ssv.com.entity.History;
import ssv.com.entity.Profile;
import ssv.com.entity.Schedule;
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
	private ScheduleService scheduleService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private HistoryRepository historyRepository;

	public String add(TournamentDto tournamentDto) {
		
		if (tournamentDto.getTimeEnd().compareTo(tournamentDto.getTimeStart()) > 0) {
			Tournament tournament = modelMapper.map(tournamentDto, Tournament.class);
			for (Tournament tournament2 : tournamentRepository.getByType(tournamentDto.getType())) {
				if (tournament2.getNameTour().equalsIgnoreCase(tournamentDto.getNameTour())) {
					return "Namesake";
				}
			}
			for (int i = 0; i < tournamentDto.getListIdTeam().length; i++) {
				if (teamService.getById(tournamentDto.getListIdTeam()[i]) == null) {
					return "The group has no members";
				}
			}
			tournamentRepository.add(tournament);
			for (int i = 0; i < tournamentDto.getListIdTeam().length; i++) {

				teamService.updateTourNew(tournamentDto.getListIdTeam()[i]);
				profileService.newTour(tournamentDto.getListIdTeam()[i]);
			}
			return "create";
		} else {
			return "time error";
		}
	}

	public List<Tournament> getAll() {
		return tournamentRepository.getAll();
	}

	public Tournament getById(int idTour) {
		return tournamentRepository.getById(idTour);
	}

	public void delete(int idTour) {
		scheduleService.deleteByTour(idTour);
		tournamentRepository.delete(idTour);
		teamService.formatTour(idTour);
		historyRepository.deleteTournament(idTour);
	}

	public String addTeam(int idTour, int idTeam) {
		if (teamService.getById(idTeam).getIdTour() == 0) {
			teamService.addTour(idTour, idTeam);
			for (Profile profile : teamService.getById(idTeam).getProfile()) {
				historyRepository.addTeamTournament(profile.getId(), idTour, idTeam);
			}
			return "More success";
		}
		return "had tournaments";
	}

	public void updateStatus(int idTour, int status) {
		if (status == 2) {
			teamService.formatTour(idTour);
			var a=rankByTour(idTour).get(0).idTeam;
			tournamentRepository.updateWinner(idTour, rankByTour(idTour).get(0).idTeam);
		}
		tournamentRepository.updateStatus(idTour, status);

	}

	public List<Tournament> getTourAction() {
		return tournamentRepository.getTourAction();
	}

	public String deleteTeam(int idTeam) {
		if (tournamentRepository.getById(teamService.getById(idTeam).getIdTour()).getStatus() == 0) {
			int idTour = teamService.getById(idTeam).getIdTour();
			teamService.formatTourById(idTour, idTeam);
			scheduleService.deleteData(idTour, idTeam);
			historyRepository.deleteTeamTournament(idTour, idTeam);
			return "deleted successfully";
		}
		return "Tournaments are in progress or ended";
	}

	public String edit(Tournament tournament) {
		if (tournamentRepository.getById(tournament.getIdTour()).getStatus() != 0) {
			return "Tournaments are going on";
		}
		tournamentRepository.edit(tournament);
		return "edit";
	}

	public List<Tournament> getByStatus(int status, String type) {
		return tournamentRepository.getByStatus(status, type);
	}

	public List<Tournament> getByType(String type) {
		return tournamentRepository.getByType(type);
	}

	public List<Rank> rank(String type) {
		List<Rank> list = new ArrayList<Rank>();
		for (Team team : teamService.getAllByType(type)) {
			Rank rank = new Rank();
			rank.setName(team.getNameTeam());
			double numberRank = teamService.rank(team.getIdTeam());
			rank.setRank(numberRank);
			rank.setIdTeam(team.getIdTeam());
			rank.setTeam(teamService.findById(team.getIdTeam()));
			list.add(rank);
			if(list.size()>4) {
				break;
			}
		}
		;
		Collections.sort(list, new Comparator<Rank>() {

			@Override
			public int compare(Rank s1, Rank s2) {
				if (s1.rank == s2.rank)
					return 0;
				else if (s1.rank > s2.rank)
					return -1;
				else
					return 1;
			}
		});
		

		return list;
	}

	public List<Rank> rankByTour(int idTour) {
		List<Rank> list = new ArrayList<Rank>();
		List<History> listHistory = new ArrayList<History>();
		listHistory = historyRepository.getTeamByTour(idTour);
		for (History history : listHistory) {
			Rank rank = new Rank();
			rank.setName(teamService.getById(history.getIdTeam()).getNameTeam());
			double numberRank = teamService.rankByTour(history.getIdTeam(), idTour);
			rank.setList(scheduleService.getRecently(history.getIdTeam(),idTour));
			rank.setRank(numberRank);
			rank.setIdTeam(history.getIdTeam());
			rank.setTeam(teamService.findTeamByTour(rank.getIdTeam(),idTour));
			rank.setTotalGoals(scheduleService.totalGoalds(rank.getIdTeam(),idTour));
			list.add(rank);
		}
		Collections.sort(list, new Comparator<Rank>() {

			@Override
			public int compare(Rank s1, Rank s2) {
				if (s1.rank == s2.rank)
					return 0;
				else if (s1.rank > s2.rank)
					return -1;
				else
					return 1;
			}
		});
		return list;
	}

	public List<ToursByType> getToursByType() {
		List<ToursByType> listsTours=new ArrayList<ToursByType>();
		List<String> lists=tournamentRepository.getTypeSport();
		for (String type : lists) {
			List<Tournament> listTour=tournamentRepository.getByType(type);
			ToursByType toursByType=new ToursByType();
			toursByType.setTournament(listTour);
			toursByType.setType(type);
			for(Tournament tour : listTour) {
				tour.getSchedule().forEach(s -> {
					s.setNameTeam1(teamRepository.getByID(s.getIdTeam1()).getNameTeam());
					s.setNameTeam2(teamRepository.getByID(s.getIdTeam2()).getNameTeam());
				});
			}
			listsTours.add(toursByType);
		}
		return listsTours;
	}

	public List<RankTeamsByType> rankAll() {
		List<RankTeamsByType> listTeam=new ArrayList<RankTeamsByType>();
		List<String> lists=tournamentRepository.getTypeSport();
		for (String type : lists) {
			RankTeamsByType rankTeamsByType=new RankTeamsByType();
			rankTeamsByType.setType(type);
			rankTeamsByType.setList(rank(type));
			listTeam.add(rankTeamsByType);

		}
		return listTeam;
	}

	public List<Tournament> getTourByTeam(int idTeam) {
		List<Tournament> list=tournamentRepository.getTourByTeam(idTeam);
		for (Tournament tournament : list) {
			for (Schedule schedule : tournament.getSchedule()) {
				List<Team> listTeam=new ArrayList<Team>();
				listTeam.add(teamService.getById(schedule.getIdTeam1()));
				listTeam.add(teamService.getById(schedule.getIdTeam2()));
				schedule.setTeam(listTeam);
			}
		}
		return list;
	}

	public List<Tournament> getFixtures(int idTeam) {
		List<Tournament> list=tournamentRepository.getFixtures(idTeam);
		for (Tournament tournament : list) {
			for (Schedule schedule : tournament.getSchedule()) {
				List<Team> listTeam=new ArrayList<Team>();
				listTeam.add(teamService.getById(schedule.getIdTeam1()));
				listTeam.add(teamService.getById(schedule.getIdTeam2()));
				schedule.setTeam(listTeam);
			}
		}
		return list;
	}

}
