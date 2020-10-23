package ssv.com.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.var;
import ssv.com.controller.form.ScheduleForm;
import ssv.com.entity.Schedule;
import ssv.com.entity.Tournament;
import ssv.com.file.UploadFile;
import ssv.com.repository.ScheduleReponsitory;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleReponsitory scheduleReponsitory;

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private RoundService roundService;

	public List<Schedule> getAll() {
		return scheduleReponsitory.getAll();
	}

	public String checkTime(Date timeStart, Date timeEnd, int idTour) {
		Tournament tournament = tournamentService.getById(idTour);
		if (tournament.getTimeEnd().compareTo(timeEnd) >= 0 && tournament.getTimeStart().compareTo(timeStart) <= 0) {
			List<Schedule> list = scheduleReponsitory.getByIdTour(idTour);
			if (timeEnd.compareTo(timeStart) > 0) {
				if (list.isEmpty()) {
					return null;
				} else {
					for (Schedule schedule : list) {
						if ((timeStart.compareTo(schedule.getTimeEnd()) <= 0
								&& timeStart.compareTo(schedule.getTimeStart()) >= 0)
								|| ((timeEnd.compareTo(schedule.getTimeEnd()) <= 0
										&& timeEnd.compareTo(schedule.getTimeStart()) >= 0))) {
							return "Out of range  "+schedule.getTimeStart()+"and" + schedule.getTimeEnd();
						}
					}
				}
			}
		}
		return "Past tournament time";
	}

	public List<Schedule> getByIdTour(int idTour) {
		return scheduleReponsitory.getByIdTour(idTour);
	}

	public Schedule getById(int idSchedule) {
		return scheduleReponsitory.getById(idSchedule);
	}

	public void delete(int idSchedule) {
		scheduleReponsitory.delete(idSchedule);
	}

	public void updateShedule(ScheduleForm scheduleForm) throws Exception {
		Schedule schedule = new Schedule();
		schedule.setTitle(scheduleForm.getTitle());
		schedule.setScoreTeam1(scheduleForm.getScoreTeam1());
		schedule.setScoreTeam2(scheduleForm.getScoreTeam2());
		schedule.setDescription(scheduleForm.getDescription());
		schedule.setIdTeam1(scheduleForm.getIdTeam1());
		schedule.setIdTeam2(scheduleForm.getIdTeam2());
		schedule.setIdSchedule(scheduleForm.getIdSchedule());
		if (schedule.getScoreTeam1() > schedule.getIdTeam2()) {
			schedule.setIdwinner(schedule.getIdTeam1());
		} else {
			schedule.setIdwinner(schedule.getIdTeam2());
		}
		schedule.setDescription(scheduleForm.getDescription());
		if (scheduleForm.getFileImage() == null && scheduleForm.getFileVideo() == null) {
			scheduleReponsitory.updateSheduleNotFile(schedule);
		} else {
			schedule.setImage(UploadFile.saveFile(scheduleForm.getFileImage()));
			schedule.setVideo(UploadFile.saveVideo(scheduleForm.getFileVideo()));
			scheduleReponsitory.updateShedule(schedule);
		}
	}

	public void editShedule(Schedule schedule) {
		scheduleReponsitory.editShedule(schedule);
	}

	public void createSchedule(Schedule schedule) {
		scheduleReponsitory.create(schedule);
		if (tournamentService.getById(schedule.getIdTour()).getType().equals("Football")) {
			roundService.createFootball();
		} else if (tournamentService.getById(schedule.getIdTour()).getType().equals("TableTennis")) {
			roundService.createTableBall();
		} else {
			roundService.createBaskestBall();
		}
	}

	public void statusCheck() {
		long millis = System.currentTimeMillis();
		Date date = new java.sql.Date(millis);
		List<Schedule> list = scheduleReponsitory.getAll();
		var a = date;
		for (Schedule schedule : list) {
			var b = schedule.getTimeStart();
			System.out.println(a.compareTo(b) > 0);
			if (schedule.getTimeStart().compareTo(date) <= 0 && schedule.getTimeEnd().compareTo(date) > 0) {
				scheduleReponsitory.checkStatus(schedule.getIdSchedule(), 1);
			} else if (schedule.getTimeEnd().compareTo(date) <= 0) {
				scheduleReponsitory.checkStatus(schedule.getIdSchedule(), 2);
			} else {
				scheduleReponsitory.checkStatus(schedule.getIdSchedule(), 0);
			}
		}
	}

	public void deleteByTour(int idTour) {
		scheduleReponsitory.deleteByTour(idTour);
	}

	public List<Schedule> upcomingMatch() {
		return scheduleReponsitory.upcomingMatch();
	}

	public void deleteData(int idTour, int idTeam) {
		scheduleReponsitory.deleteData(idTour, idTeam);

	}

	public List<Schedule> relate(int idTeam1, int idTeam2, int idTour) {
		// TODO Auto-generated method stub
		return scheduleReponsitory.relate(idTeam1, idTeam2, idTour);
	}

	// 3 trận đấu gần nhất trong giải
	public List<Schedule> getRecently(int idTeam, int idTour) {
		return scheduleReponsitory.getRecently(idTeam, idTour);
	}

	public List<Schedule> profileSchedule(int idMember) {
		List<Schedule> list = scheduleReponsitory.profileSchedule(idMember);
		if (!list.isEmpty()) {
			for (Schedule schedule : list) {
				int idTeam = schedule.getTeam().get(0).getIdTeam();
				if (schedule.getIdTeam1() != idTeam) {
					var a = teamService.getById(schedule.getIdTeam1());
					schedule.getTeam().add(teamService.getById(schedule.getIdTeam1()));
				} else {
					schedule.getTeam().add(teamService.getById(schedule.getIdTeam2()));
				}
			}
		}

		return list;
	}

	public int totalGoalds(int idTeam, int idTour) {
		List<Schedule> schedules=scheduleReponsitory.findTeamTournament(idTeam,idTour);
		int total=0;
		for (Schedule schedule : schedules) {
			if(schedule.getTeam().get(0).getIdTeam()==idTeam) {
				total=total+schedule.getScoreTeam1();
			}
			if(schedule.getTeam().get(1).getIdTeam()==idTeam) {
				total=total+schedule.getScoreTeam2();
			}
		}
		return total;
	}

	public int totalGoalsType(int idTeam) {
		List<Schedule> schedules=scheduleReponsitory.findTeam(idTeam);
		int total=0;
		for (Schedule schedule : schedules) {
			if(schedule.getTeam().get(0).getIdTeam()==idTeam) {
				total=total+schedule.getScoreTeam1();
			}
			if(schedule.getTeam().get(1).getIdTeam()==idTeam) {
				total=total+schedule.getScoreTeam2();
			}
		}
		return total;
	}

}
