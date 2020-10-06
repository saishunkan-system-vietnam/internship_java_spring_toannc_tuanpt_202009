package ssv.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.entity.Round;
import ssv.com.service.RoundService;

@RestController
@RequestMapping("/api/v1/round/")
public class RoundController {
	@Autowired
	private RoundService roundService;

	@PostMapping(value="uploadSchedule")
	public ResponseEntity<?> uploadSchedule(@RequestBody Round round ){
		return new ResponseEntity<String>(roundService.uploadSchedule(round),HttpStatus.OK);
	}

}
