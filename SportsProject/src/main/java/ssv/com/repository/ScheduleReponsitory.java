package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import lombok.var;
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

	public void create(Schedule schedule) {
		scheduleMapper.create(schedule);

	}

	public void delete(int idSchedule) {
		scheduleMapper.delete(idSchedule);

	}

	public void updateShedule(Schedule schedule) {

		scheduleMapper.updateShedule(schedule);

	}

	public void editShedule(Schedule schedule) {
		scheduleMapper.editShedule(schedule);

	}

	public List<Schedule> search(int page, int pageSize, String nameSearch, String type, String sorts) {
		return scheduleMapper.search(page,pageSize,nameSearch,type,sorts);
	}

	public int searchTotal(String nameSearch, String type) {
		return scheduleMapper.searchTotal(nameSearch,type);
	}

	public void checkStatus(int j, int i) {
		scheduleMapper.checkStatus(j,i);
		
	}


	public int sum(int idTeam) {
		// TODO Auto-generated method stub
		return scheduleMapper.sum(idTeam);
	}

	public int sumWin(int idTeam) {
		// TODO Auto-generated method stub
		return scheduleMapper.sumWin(idTeam);
	}

	public int sumJoinByTour(int idTeam, int idTour) {
		// TODO Auto-generated method stub
		return scheduleMapper.sumJoinByTour(idTeam,idTour);
	}
	public int sumWinJoinByTour(int idTeam, int idTour) {
		// TODO Auto-generated method stub
		return scheduleMapper.sumWinJoinByTour(idTeam,idTour);
	}


}
