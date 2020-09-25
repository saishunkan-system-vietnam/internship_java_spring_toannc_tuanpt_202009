package ssv.com.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.val;
import ssv.com.controller.form.ScheduleForm;
import ssv.com.dto.ScheduleDto;
import ssv.com.entity.Schedule;
import ssv.com.file.UploadFile;
import ssv.com.repository.ScheduleReponsitory;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleReponsitory scheduleReponsitory;



	public List<Schedule> getAll() {
		return scheduleReponsitory.getAll();
	}



	public boolean checkTime(Date timeStart, Date timeEnd, int idTour) {
		List<Schedule> list=scheduleReponsitory.getByIdTour(idTour);
		if(timeEnd.compareTo(timeStart)>0) {
			if(list.isEmpty()) {
				return true;
			}
			else {
				if(timeStart.compareTo(list.get(list.size()-1).getTimeEnd())>0) {
					return true;
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
		Schedule schedule =new Schedule();
		schedule.setScoreTeam1(scheduleForm.getScoreTeam1());
		schedule.setScoreTeam2(scheduleForm.getScoreTeam2());
		schedule.setDescription(scheduleForm.getDescription());
		schedule.setIdTeam1(scheduleForm.getIdTeam1());
		schedule.setIdTeam2(scheduleForm.getIdTeam2());
		schedule.setIdSchedule(scheduleForm.getIdSchedule());

		if(schedule.getScoreTeam1()>schedule.getIdTeam2()) {
			schedule.setIdwinner(schedule.getIdTeam1());
		}
		else {
			schedule.setIdwinner(schedule.getIdTeam2());
		}
		schedule.setDescription(scheduleForm.getDescription());
		schedule.setVideo(UploadFile.saveVideo(scheduleForm.getFile()));
		scheduleReponsitory.updateShedule(schedule);
		
		
	
	}



	public void editShedule(Schedule schedule) {
		scheduleReponsitory.editShedule(schedule);
		
	}



	public ScheduleDto search(int page, int pageSize, String nameSearch, String type) {
		ScheduleDto dto=new ScheduleDto();
		dto.setTotal(scheduleReponsitory.searchTotal(nameSearch,type).size());
		dto.setPage(page);
		dto.setPageSize(pageSize);
		if(dto.getTotal()%pageSize==0) {
			dto.setTotalPage(dto.getTotal()/pageSize);
		}
		else {
			dto.setTotalPage((dto.getTotal()/pageSize)+1);
		}
		dto.setSchedules((scheduleReponsitory.search((page-1)*pageSize,pageSize,nameSearch,type)));
		return dto;
	}



	public void createSchedule(Schedule schedule) {
		 scheduleReponsitory.create(schedule);
		
	}


}
