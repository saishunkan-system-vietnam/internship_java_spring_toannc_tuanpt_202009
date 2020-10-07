package ssv.com.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	private RoundService roundService;

	public List<Schedule> getAll() {
		return scheduleReponsitory.getAll();
	}

	public boolean checkTime(Date timeStart, Date timeEnd, int idTour) {
		Tournament tournament=tournamentService.getById(idTour);
		if(tournamentService.getById(idTour).getTimeEnd().compareTo(timeEnd)>=0) {
			List<Schedule> list = scheduleReponsitory.getByIdTour(idTour);
			if (timeEnd.compareTo(timeStart) > 0) {
				if (list.isEmpty()) {
					return true;
				} else {
					for (Schedule schedule : list) {
						if ((timeStart.compareTo(schedule.getTimeEnd()) >0
								||timeEnd.compareTo(schedule.getTimeStart())<0)
								&&timeStart.compareTo(tournament.getTimeStart())>0
								&&timeEnd.compareTo(tournament.getTimeEnd())<=0) {
							return true;
						}
					}
				}
			}
		}		
		return false;
	}

	public List<Schedule> getByIdTour(int idTour) {
		return scheduleReponsitory.getByIdTour(idTour);
	}

	public Schedule getById(int idSchedule) {
		return scheduleReponsitory.getById(idSchedule);
	}

	public void delete(int idSchedule) {
		Schedule schedule =scheduleReponsitory.getById(idSchedule);
		scheduleReponsitory.delete(idSchedule);
		scheduleReponsitory.setTotalMatch(scheduleReponsitory.sumJoinByTour(schedule.getIdTeam1(), schedule.getIdTour()),schedule.getIdTeam1());
		scheduleReponsitory.setTotalMatch(scheduleReponsitory.sumJoinByTour(schedule.getIdTeam2(), schedule.getIdTour()),schedule.getIdTeam2());
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
		if(scheduleForm.getFileImage()==null&&scheduleForm.getFileVideo()==null) {
			scheduleReponsitory.updateSheduleNotFile(schedule);
		}
		else {
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
		var a=tournamentService.getById(schedule.getIdTour()).getType();
		if(tournamentService.getById(schedule.getIdTour()).getType().equals("football")) {
			roundService.createFootball();
		}
		else {
			roundService.createTableBall();
		}
		scheduleReponsitory.setTotalMatch(scheduleReponsitory.sumJoinByTour(schedule.getIdTeam1(), schedule.getIdTour()),schedule.getIdTeam1());
		scheduleReponsitory.setTotalMatch(scheduleReponsitory.sumJoinByTour(schedule.getIdTeam2(), schedule.getIdTour()),schedule.getIdTeam2());
	}

	public void statusCheck() {
		long millis = System.currentTimeMillis();
		Date date = new java.sql.Date(millis);
		List<Schedule> list = scheduleReponsitory.getAll();
		var a=date;
		for (Schedule schedule : list) {
			var b=schedule.getTimeStart();
			System.out.println(a.compareTo(b)>0);
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
		scheduleReponsitory.deleteData(idTour,idTeam);
		
	}

}
