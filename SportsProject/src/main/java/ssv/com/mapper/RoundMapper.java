package ssv.com.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper	
public interface RoundMapper {

	void createFootball();

	void createTableBall();

	void delete(int idSchedule);

}
