package ssv.com.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssv.com.entity.Schedule;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
	private List<Schedule> schedules;
	private int page;
	private int pageSize;
	private int totalPage;
	private int total;
}
