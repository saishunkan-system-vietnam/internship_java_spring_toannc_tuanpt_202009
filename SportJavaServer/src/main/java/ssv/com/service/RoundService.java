package ssv.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssv.com.entity.Round;
import ssv.com.repository.RoundRepository;

@Service
public class RoundService {
	@Autowired
	private RoundRepository roundRepository;

	public void createFootball() {
		roundRepository.createFootball();
		
	}

	public void createTableBall() {
		roundRepository.createTableBall();
		
	}

	public void delete(int idSchedule) {
		roundRepository.delete(idSchedule);
		
	}

	public String uploadSchedule(Round round) {
		 roundRepository.uploadSchedule(round);
		 return "thành công";
	}

	

}
