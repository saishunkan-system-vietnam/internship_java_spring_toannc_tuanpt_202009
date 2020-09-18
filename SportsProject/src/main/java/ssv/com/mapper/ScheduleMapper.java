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

	public void create(Schedule schedule, int idTournament);

	public void delete(int idSchedule);

	public void updateShedule(int idShedule);

	public void editShedule(int idShedule);

}
