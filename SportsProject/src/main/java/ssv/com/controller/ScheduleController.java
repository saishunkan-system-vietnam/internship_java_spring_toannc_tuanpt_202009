package ssv.com.controller;



import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import ssv.com.controller.form.ScheduleForm;
import ssv.com.dto.ScheduleDto;
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

	@GetMapping(value="getById/{idSchedule}")
	public ResponseEntity<Schedule> getById(@PathVariable int idSchedule){
		return new ResponseEntity<Schedule>(scheduleService.getById(idSchedule),HttpStatus.OK);
	}

	@GetMapping(value="getByIdTour")
	public ResponseEntity<List<Schedule>> getByIdTour(@RequestParam int idTour){
		return new ResponseEntity<List<Schedule>>(scheduleService.getByIdTour(idTour),HttpStatus.OK);
	}

	@PostMapping(value = "create")
	public ResponseEntity<String> create(@RequestBody Schedule schedule){

		if(scheduleService.checkTime(schedule.getTimeStart(),schedule.getTimeEnd(),schedule.getIdTour())) {
			scheduleService.createSchedule(schedule);
//			teamService.updateTotalMatch();
			return new ResponseEntity<String>("create",HttpStatus.OK);

		}
		return new ResponseEntity<String>("trung lich",HttpStatus.OK);
	}

	@DeleteMapping(value="delete/{idSchedule}")
	public ResponseEntity<String> delete(@PathVariable int idSchedule){

		if(scheduleService.getById(idSchedule).getStatus()==0 ) {
			scheduleService.delete(idSchedule);
//			teamService.updateTotalMatch();
			return new ResponseEntity<String>("xoa thanh cong", HttpStatus.OK);
		}
		return new ResponseEntity<String>("xoa khong thanh cong", HttpStatus.OK);

	}
	@PostMapping(value = "updateShedule")
	public ResponseEntity<String> updateShedule(@ModelAttribute ScheduleForm scheduleForm) throws IllegalStateException, IOException{
		if(scheduleService.getById(scheduleForm.getIdSchedule()).getStatus()==2) {
			scheduleService.updateShedule(scheduleForm);
			return new ResponseEntity<String>("update thanh cong", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Chưa kết thúc không thể update", HttpStatus.OK);

	}
	@PostMapping(value = "editShedule")
	public ResponseEntity<String> editShedule(@RequestBody Schedule schedule){
		if(scheduleService.getById(schedule.getIdSchedule()).getStatus()==0 ) {
			scheduleService.editShedule(schedule);
			return new ResponseEntity<String>("edit", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Trận đấu đã được diễn ra", HttpStatus.OK);
	}
	@GetMapping(value="search")
	public ResponseEntity<ScheduleDto> search(@RequestParam int page,@RequestParam int pageSize,@RequestParam String nameSearch,@RequestParam String type,@RequestParam String sorts){
		if(type=="") {
			type="id_schedule";
		};	
		return new ResponseEntity<ScheduleDto>(scheduleService.search(page,pageSize*2,nameSearch,type,sorts),HttpStatus.OK);
		
		
	}
	@GetMapping(value="status")
	public void statusCheck() {
		scheduleService.statusCheck();
	}
	
	

}
