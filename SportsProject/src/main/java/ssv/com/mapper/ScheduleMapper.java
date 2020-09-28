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

	public List<Schedule> search(int page, int pageSize, String nameSearch, String type, String sorts);

	public int searchTotal(String nameSearch, String type);

	public void checkStatus(int j, int i);


}
