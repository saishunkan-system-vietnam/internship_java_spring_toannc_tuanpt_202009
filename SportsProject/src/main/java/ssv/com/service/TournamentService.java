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
	private ScheduleService scheduleService;
	@Autowired
	private ProfileService profileService;

	@Autowired
	private HistoryRepository historyRepository;

	public String add(TournamentDto tournamentDto) {
		if (tournamentDto.getTimeEnd().compareTo(tournamentDto.getTimeStart()) > 0) {
			Tournament tournament = modelMapper.map(tournamentDto, Tournament.class);
			for (Tournament tournament2 : tournamentRepository.getAll()) {
				if(tournament2.getNameTour()==tournamentDto.getNameTour()) {
					return "Trung ten giai dau";
				}
			}
			
			for (int i = 0; i < tournamentDto.getListIdTeam().length; i++) {
				if (teamService.getById(tournamentDto.getListIdTeam()[i]) == null) {
					return "Team khong co thanh vien";
				}
			}
			tournamentRepository.add(tournament);
			for (int i = 0; i < tournamentDto.getListIdTeam().length; i++) {

				teamService.updateTourNew(tournamentDto.getListIdTeam()[i]);
				profileService.newTour(tournamentDto.getListIdTeam()[i]);
			}
			return "create";
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
		scheduleService.deleteByTour(idTour);
		tournamentRepository.delete(idTour);
		teamService.formatTour(idTour);
		historyRepository.deleteTournament(idTour);

	}

	public void updateInfo(Tournament tournament) {
		tournamentRepository.updateInfo(tournament);

	}


	public String addTeam(int idTour, int idTeam) {
		if (teamService.getById(idTeam).getIdTour() == 0) {
			teamService.addTour(idTour,idTeam);
			for (Profile profile : teamService.getById(idTeam).getProfile()) {
				historyRepository.addTeamTournament(profile.getId(), idTour, idTeam);
			}
			return "Add thanh cong";
		}
		return "Đã có Tour";
	}

	public void updateStatus(int idTour, int status) {
		tournamentRepository.updateStatus(idTour, status);

	}

	public List<Tournament> getTourAction() {
		// TODO Auto-generated method stub
		return tournamentRepository.getTourAction();

	}

	public String deleteTeam(int idTeam) {
		if (tournamentRepository.getById(teamService.getById(idTeam).getIdTour()).getStatus() == 0) {
			int idTour = teamService.getById(idTeam).getIdTour();
			teamService.formatTourById(idTour,idTeam);
			historyRepository.deleteTeamTournament(idTour, idTeam);
			return "Xóa thành công";
		}
		return "Giải đấu đang được diễn ra hoặc đã kết thúc";
	}

	public String edit(Tournament tournament) {
		if(tournamentRepository.getById(tournament.getIdTour()).getStatus()!=0) {
			return "Giải đấu đang diễn ra";
		}
			tournamentRepository.edit(tournament);
		return "edit";
	}
}
