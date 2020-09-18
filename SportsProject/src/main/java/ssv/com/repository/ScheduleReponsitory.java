package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Schedule;
import ssv.com.mapper.ScheduleMapper;

@Repository
public class ScheduleReponsitory {


	@Autowired
	private ScheduleMapper scheduleMapper;

	public List<Schedule> getAll() {
		// TODO Auto-generated method stub
		return scheduleMapper.getAll();
	}

	public List<Schedule> getByIdTour(int idTour) {
		// TODO Auto-generated method stub
		return scheduleMapper.getByIdTour(idTour);
	}

	public Schedule getById(int idSchedule) {
		// TODO Auto-generated method stub
		return scheduleMapper.getById(idSchedule);
	}

	public void create(Schedule schedule, int idTour) {
		scheduleMapper.create(schedule, idTour);

	}

	public void delete(int idSchedule) {
		scheduleMapper.delete(idSchedule);

	}

	public void updateShedule(int idShedule) {
		scheduleMapper.updateShedule(idShedule);

	}

	public void editShedule(int idShedule) {
		scheduleMapper.editShedule(idShedule);

	}
}
