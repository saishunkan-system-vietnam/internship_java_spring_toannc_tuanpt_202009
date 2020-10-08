package ssv.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import ssv.com.entity.Schedule;

@Mapper
public interface ScheduleMapper {

	public List<Schedule> getAll();

	public List<Schedule> getByIdTour(int idTour);

	public Schedule getById(int idSchedule);

	public void create(Schedule schedule);

	public void delete(int idSchedule);

	public void updateShedule(Schedule schedule);

	public void editShedule(Schedule schedule);

	public void checkStatus(int j, int i);

	public int sum(int idTeam);

	public int sumWin(int idTeam);

	public int sumJoinByTour(int idTeam, int idTour);

	public int sumWinJoinByTour(int idTeam, int idTour);

	public void deleteByTour(int idTour);

	public void updateSheduleNotFile(Schedule schedule);

	public List<Schedule> upcomingMatch();

	public void deleteData(int idTour, int idTeam);

	public void updateTotalMatch(Schedule schedule);

	public void setTotalMatch(int sumJoinByTour, int idTeam);

	public List<Schedule> relate(int idTeam1, int idTeam2, int idTour);



}
