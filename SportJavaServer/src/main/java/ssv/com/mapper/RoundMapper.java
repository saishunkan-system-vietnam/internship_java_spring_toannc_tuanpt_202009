package ssv.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.Round;

@Mapper	
public interface RoundMapper {

	void createFootball();

	void createTableBall();

	void delete(int idSchedule);

	void uploadSchedule(Round round);


}
