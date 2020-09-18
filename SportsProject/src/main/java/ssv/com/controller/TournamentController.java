package ssv.com.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.entity.Team;
import ssv.com.entity.Tournament;
import ssv.com.service.TournamentService;

@RestController
@RequestMapping("/api/v1/tournament/")
@ResponseBody
public class TournamentController {

	@Autowired
	private TournamentService tournamentService;

	@PostMapping(value="addTournament")
	public ResponseEntity<String> addTournament(@RequestBody Tournament tournament){
		tournamentService.add(tournament);
		return new ResponseEntity<String>("thanh cong",HttpStatus.OK);

	}

	@GetMapping(value="getAll")
	public ResponseEntity<List<Tournament>> getAll(){
		return new ResponseEntity<List<Tournament>>(tournamentService.getAll(),HttpStatus.OK);

	}

	@GetMapping(value="getById")
	public ResponseEntity<Tournament> getById(@RequestParam int idTour){
		return new ResponseEntity<Tournament>(tournamentService.getById(idTour),HttpStatus.OK);

	}

	@PostMapping(value="delete")
	public ResponseEntity<String> deleteTor(@RequestParam int idTour){
		if(tournamentService.getById(idTour).getStatus()==0) {
			tournamentService.delete(idTour);

			return new ResponseEntity<String>("xoa thanh cong",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Giai dau da ket thuc hoac dang dien ra",HttpStatus.OK);
	}

	@PostMapping(value="updateTournament")
	public ResponseEntity<String> updateInfo(@RequestBody Tournament tournament){
		tournamentService.updateInfo(tournament);
		return new ResponseEntity<String>("update thanh cong",HttpStatus.OK);
	}
	@PostMapping(value="addTeam")
	public ResponseEntity<String> addTeam(@RequestParam int idTour,@RequestBody Team team){
		if(team.getIdTour()==0) {
			tournamentService.addTeam(idTour,team);
		return new ResponseEntity<String>("them thanh cong",HttpStatus.OK);
		}
		return new ResponseEntity<String>("khong hop le",HttpStatus.OK);
	}

	@PostMapping(value="deleteTeam")
	public ResponseEntity<String> deleTeam(@RequestParam int idTour,@RequestBody Team team){
		if(team.getIdTour()==idTour) {
			tournamentService.deleteTeam(idTour,team);
		return new ResponseEntity<String>("xoa thanh cong",HttpStatus.OK);
		}
		return new ResponseEntity<String>("khong hop le",HttpStatus.OK);
	}
	@GetMapping(value="updateStatus")
	public ResponseEntity<String> updateStatus(){
		Date time=new Date(System.currentTimeMillis());
		int status=0;
		for (Tournament tournament : tournamentService.getAll()) {
			if(time.compareTo(tournament.getTimeStart())<0) {
				status=0;
				tournamentService.updateStatus(tournament.getIdTour(),status);
			}
			else if(time.compareTo(tournament.getTimeStart())>=0&&time.compareTo(tournament.getTimeEnd())<0) {
				status=1;
				tournamentService.updateStatus(tournament.getIdTour(),status);

			}
			else {
				status=2;
				tournamentService.updateStatus(tournament.getIdTour(),status);

			}
		}
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}



}
