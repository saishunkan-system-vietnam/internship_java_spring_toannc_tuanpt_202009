package ssv.com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Round;
import ssv.com.mapper.RoundMapper;

@Repository
public class RoundRepository {
	@Autowired
	private RoundMapper roundMapper;

	public void createFootball() {
		roundMapper.createFootball();

	}

	public void createTableBall() {
		roundMapper.createTableBall();

	}

	public void delete(int idSchedule) {
		roundMapper.delete(idSchedule);

	}

	public void uploadSchedule(Round round) {
		roundMapper.uploadSchedule(round);

	}

	public void createBaskestBall() {
		roundMapper.createBaskestBall();

	}

}
