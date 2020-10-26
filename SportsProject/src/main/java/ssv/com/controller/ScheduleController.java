package ssv.com.controller;

import java.util.List;

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

import ssv.com.controller.form.ScheduleForm;
import ssv.com.entity.Schedule;
import ssv.com.service.RoundService;
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
	private RoundService roundService;
	@Autowired
	private TournamentService tournamnetService;

	@GetMapping(value = "getAll")
	public ResponseEntity<List<Schedule>> getAll() {
		return new ResponseEntity<List<Schedule>>(scheduleService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "getById/{idSchedule}")
	public ResponseEntity<Schedule> getById(@PathVariable int idSchedule) {
		return new ResponseEntity<Schedule>(scheduleService.getById(idSchedule), HttpStatus.OK);
	}

	@GetMapping(value = "getByIdTour")
	public ResponseEntity<List<Schedule>> getByIdTour(@RequestParam int idTour) {
		return new ResponseEntity<List<Schedule>>(scheduleService.getByIdTour(idTour), HttpStatus.OK);
	}

	@PostMapping(value = "create")
	public ResponseEntity<String> create(@RequestBody Schedule schedule) {
		String check = scheduleService.checkTime(schedule.getTimeStart(), schedule.getTimeEnd(), schedule.getIdTour());
		if (check == null) {
			scheduleService.createSchedule(schedule);
			return new ResponseEntity<String>("create", HttpStatus.OK);
		}
		String result = tournamnetService.getById(schedule.getIdTour()).getTimeStart() + "->"
				+ tournamnetService.getById(schedule.getIdTour()).getTimeEnd();
		return new ResponseEntity<String>(check + "! The time of the tournament is " + result, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{idSchedule}")
	public ResponseEntity<String> delete(@PathVariable int idSchedule) {

		if (scheduleService.getById(idSchedule).getStatus() == 0) {
			roundService.delete(idSchedule);
			scheduleService.delete(idSchedule);
			return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Deletion failed", HttpStatus.OK);

	}

	@PostMapping(value = "updateShedule")
	public ResponseEntity<String> updateShedule(@ModelAttribute ScheduleForm scheduleForm) throws Exception {
		if (scheduleService.getById(scheduleForm.getIdSchedule()).getStatus() == 2) {
			scheduleService.updateShedule(scheduleForm);
			return new ResponseEntity<String>("successfully updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("cannot update", HttpStatus.OK);

	}

	@PostMapping(value = "editShedule")
	public ResponseEntity<String> editShedule(@RequestBody Schedule schedule) {
		String check = scheduleService.checkTimeEdit(schedule.getTimeStart(), schedule.getTimeEnd(), schedule.getIdTour());
		if (check == null) {
			if (scheduleService.getById(schedule.getIdSchedule()).getStatus() == 0) {
				scheduleService.editShedule(schedule);
				return new ResponseEntity<String>("edit", HttpStatus.OK);
			}
			return new ResponseEntity<String>("The match is going on", HttpStatus.OK);
		}
		String result = tournamnetService.getById(schedule.getIdTour()).getTimeStart() + "->"
				+ tournamnetService.getById(schedule.getIdTour()).getTimeEnd();

		return new ResponseEntity<String>(check + "! The time of the tournament is " + result, HttpStatus.OK);

	}

	@GetMapping(value = "status")
	public void statusCheck() {
		scheduleService.statusCheck();
	}

	// hien thi nhung tran dau dien ra som nhat
	@GetMapping(value = "upcomingMatch")
	public ResponseEntity<List<Schedule>> upcomingMatch() {
		return new ResponseEntity<List<Schedule>>(scheduleService.upcomingMatch(), HttpStatus.OK);
	}

	// hiển thị những trận đấu liên quan đến 2 team
	@GetMapping(value = "related")
	public ResponseEntity<List<Schedule>> related(@RequestParam int idTeam1, @RequestParam int idTeam2,
			@RequestParam int idTour) {
		return new ResponseEntity<List<Schedule>>(scheduleService.relate(idTeam1, idTeam2, idTour), HttpStatus.OK);
	}

	// hiển thị lịch trình của member
	@GetMapping(value = "profileSchedule")
	public ResponseEntity<List<Schedule>> profileSchedule(@RequestParam int idMember) {
		return new ResponseEntity<List<Schedule>>(scheduleService.profileSchedule(idMember), HttpStatus.OK);
	}

}
