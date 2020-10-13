package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Schedule;
import ssv.com.mapper.ScheduleMapper;

@Repository
public class ScheduleReponsitory {


	@Autowired
	private ScheduleMapper scheduleMapper;

	public List<Schedule> getAll() {
		return scheduleMapper.getAll();
	}

	public List<Schedule> getByIdTour(int idTour) {
		return scheduleMapper.getByIdTour(idTour);
	}

	public Schedule getById(int idSchedule) {
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

	public void checkStatus(int j, int i) {
		scheduleMapper.checkStatus(j,i);	
	}

	public int sum(int idTeam) {
		return scheduleMapper.sum(idTeam);
	}

	public int sumWin(int idTeam) {
		return scheduleMapper.sumWin(idTeam);
	}

	public int sumJoinByTour(int idTeam, int idTour) {
		return scheduleMapper.sumJoinByTour(idTeam,idTour);
	}
	public int sumWinJoinByTour(int idTeam, int idTour) {
		return scheduleMapper.sumWinJoinByTour(idTeam,idTour);
	}

	public void deleteByTour(int idTour) {
		scheduleMapper.deleteByTour(idTour);
		
	}

	public void updateSheduleNotFile(Schedule schedule) {
		scheduleMapper.updateSheduleNotFile(schedule);
		
	}

	public List<Schedule> upcomingMatch() {
		return scheduleMapper.upcomingMatch();
	}

	public void deleteData(int idTour, int idTeam) {
		scheduleMapper.deleteData(idTour,idTeam);
	}

	public void updateTotalMatch(Schedule schedule) {
		scheduleMapper.updateTotalMatch(schedule);
		
	}

	public void setTotalMatch(int sumJoinByTour, int idTeam) {
		scheduleMapper.setTotalMatch(sumJoinByTour,idTeam);
		
	}

	public List<Schedule> relate(int idTeam1, int idTeam2, int idTour) {
		// TODO Auto-generated method stub
		return scheduleMapper.relate(idTeam1,idTeam2,idTour);
	}

	public List<Schedule> getRecently(int idTeam, int idTour) {
		// TODO Auto-generated method stub
		return scheduleMapper.getRecently(idTeam,idTour);
	}

	

}
