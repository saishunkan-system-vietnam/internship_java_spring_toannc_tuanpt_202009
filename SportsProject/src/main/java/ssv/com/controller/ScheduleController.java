package ssv.com.controller;



import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.entity.Schedule;
import ssv.com.service.ScheduleService;
import ssv.com.service.TeamService;
import ssv.com.service.TournamentService;

@RestController
@RequestMapping("/api/v1/schedule/")
@ResponseBody
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private TournamentService tournamentService;

	@Autowired
	private TeamService teamService;

	@GetMapping(value="getAll")
	public ResponseEntity<List<Schedule>> getAll(){
		return new ResponseEntity<List<Schedule>>(scheduleService.getAll(),HttpStatus.OK);
	}

	@GetMapping(value="getById")
	public ResponseEntity<Schedule> getById(@RequestParam int idSchedule){
		return new ResponseEntity<Schedule>(scheduleService.getById(idSchedule),HttpStatus.OK);
	}

	@GetMapping(value="getByIdTour")
	public ResponseEntity<List<Schedule>> getByIdTour(@RequestParam int idTour){
		return new ResponseEntity<List<Schedule>>(scheduleService.getByIdTour(idTour),HttpStatus.OK);
	}

	@PostMapping(value = "create/{idTour}")
	public ResponseEntity<String> create(@RequestBody Schedule schedule,@PathParam(value ="idTour") int idTour){
		
		if(scheduleService.checkTime(schedule.getTimeStart(),schedule.getTimeEnd(),idTour)) {
			scheduleService.createTour(schedule,idTour);
			teamService.updateTotalMatch();
			return new ResponseEntity<String>("thanh cong",HttpStatus.OK);

		}
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}

	@DeleteMapping(value="delete/{idTour}/{idScheldule}")
	public ResponseEntity<String> delete(@PathParam(value = "idScheldule") int idSchedule,@PathParam(value ="idTour" ) int idTour){

		if(scheduleService.getById(idSchedule).getStatus()==0 && tournamentService.getById(idTour).getStatus()==0) {
			scheduleService.delete(idSchedule);
			teamService.updateTotalMatch();
			return new ResponseEntity<String>("xoa thanh cong", HttpStatus.OK);
		}
		return new ResponseEntity<String>("xoa khong thanh cong", HttpStatus.OK);

	}
	@PostMapping(value = "updateShedule")
	public ResponseEntity<String> updateShedule(@RequestParam int idShedule){
		if(tournamentService.getById(idShedule).getStatus()==2) {
			scheduleService.updateShedule(idShedule);
			return new ResponseEntity<String>("update thanh cong", HttpStatus.OK);
		}
		return new ResponseEntity<String>("update that bai", HttpStatus.OK);

	}
	@PostMapping(value = "editShedule")
	public ResponseEntity<String> editShedule(@RequestParam int idShedule){
		if(tournamentService.getById(idShedule).getStatus()==0 ) {
			scheduleService.editShedule(idShedule);
			return new ResponseEntity<String>("edit thanh cong", HttpStatus.OK);
		}
		return new ResponseEntity<String>("edit that bai", HttpStatus.OK);

	}

}
