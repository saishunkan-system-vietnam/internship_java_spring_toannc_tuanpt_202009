package ssv.com.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;
import ssv.com.controller.form.ScheduleForm;
import ssv.com.dto.ScheduleDto;
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


	public List<Schedule> getAll() {
		return scheduleReponsitory.getAll();
	}

	public boolean checkTime(Date timeStart, Date timeEnd, int idTour) {

		Tournament tournament=tournamentService.getById(idTour);

		if(service.getById(idTour).getTimeEnd().compareTo(timeEnd)>=0) {
			List<Schedule> list = scheduleReponsitory.getByIdTour(idTour);
			if (timeEnd.compareTo(timeStart) > 0) {
				if (list.isEmpty()) {
					return true;
				} else {

					if (timeStart.compareTo(list.get(list.size() - 1).getTimeEnd()) > 0&&timeStart.compareTo(tournament.getTimeStart())>=0&&timeEnd.compareTo(tournament.getTimeEnd())<=0) {
						return true;
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
		scheduleReponsitory.delete(idSchedule);

	}

	public void updateShedule(ScheduleForm scheduleForm) throws IllegalStateException, IOException {
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
		try {
			schedule.setImage(UploadFile.saveFile(scheduleForm.getFileImage()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schedule.setVideo(UploadFile.saveVideo(scheduleForm.getFileVideo()));
		scheduleReponsitory.updateShedule(schedule);

	}

	public void editShedule(Schedule schedule) {
		scheduleReponsitory.editShedule(schedule);

	}

	public ScheduleDto search(int page, int pageSize, String nameSearch, String type, String sorts) {
		ScheduleDto dto = new ScheduleDto();
		dto.setTotal(scheduleReponsitory.searchTotal(nameSearch, type));
		dto.setPage(page);
		dto.setPageSize(pageSize);
		if (dto.getTotal() % pageSize == 0) {
			dto.setTotalPage(dto.getTotal() / pageSize);
		} else {
			dto.setTotalPage((dto.getTotal() / pageSize) + 1);
		}
		dto.setSchedules((scheduleReponsitory.search((page - 1) * pageSize, pageSize, nameSearch, type, sorts)));
		return dto;
	}

	public void createSchedule(Schedule schedule) {
		scheduleReponsitory.create(schedule);

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

}
