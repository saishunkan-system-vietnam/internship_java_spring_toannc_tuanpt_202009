package ssv.com.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ssv.com.entity.Schedule;
import ssv.com.repository.ScheduleReponsitory;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleReponsitory scheduleReponsitory;



	public List<Schedule> getAll() {
		// TODO Auto-generated method stub
		return scheduleReponsitory.getAll();
	}



	public boolean checkTime(Date timeStart, Date timeEnd, int idTour) {
		List<Schedule> list=scheduleReponsitory.getByIdTour(idTour);
		if(timeEnd.compareTo(timeStart)>0) {
			if(timeStart.compareTo(scheduleReponsitory.getById(list.size()-1).getTimeEnd())>0) {
				return true;
			}
		}
		return false;
	}



	public List<Schedule> getByIdTour(int idTour) {
		// TODO Auto-generated method stub
				return scheduleReponsitory.getByIdTour(idTour);
	}



	public Schedule getById(int idSchedule) {
		// TODO Auto-generated method stub
		return scheduleReponsitory.getById(idSchedule);
	}



	public void createTour(Schedule schedule, int idTour) {
		 scheduleReponsitory.create(schedule,idTour);
		
	}



	public void delete(int idSchedule) {
		scheduleReponsitory.delete(idSchedule);
		
	}



	public void updateShedule(int idShedule) {
			scheduleReponsitory.updateShedule(idShedule);
	}



	public void editShedule(int idShedule) {
		scheduleReponsitory.editShedule(idShedule);
		
	}


}
