package ssv.com.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.dto.Rank;
import ssv.com.dto.RankTeamsByType;
import ssv.com.dto.TournamentDto;
import ssv.com.dto.ToursByType;
import ssv.com.entity.Tournament;
import ssv.com.service.TournamentService;

@RestController
@RequestMapping("/api/v1/tournament/")
@ResponseBody
public class TournamentController {

	@Autowired
	private TournamentService tournamentService;

	@PostMapping(value = "addTournament")
	public ResponseEntity<String> addTournament(@RequestBody TournamentDto tournamentDto) {

		return new ResponseEntity<String>(tournamentService.add(tournamentDto), HttpStatus.OK);

	}

	@GetMapping(value = "getAll")
	public ResponseEntity<List<Tournament>> getAll() {
		return new ResponseEntity<List<Tournament>>(tournamentService.getAll(), HttpStatus.OK);

	}

	// hien thi nhung tour chua bat dau
	@GetMapping(value = "getTourAction")
	public ResponseEntity<List<Tournament>> getTourAction() {
		return new ResponseEntity<List<Tournament>>(tournamentService.getTourAction(), HttpStatus.OK);
	}

	@GetMapping(value = "getById")
	public ResponseEntity<Tournament> getById(@RequestParam int idTour) {
		return new ResponseEntity<Tournament>(tournamentService.getById(idTour), HttpStatus.OK);

	}

	@DeleteMapping(value = "delete/{idTour}")
	public ResponseEntity<String> deleteTor(@PathVariable int idTour) {
		if (tournamentService.getById(idTour).getStatus() == 0) {
			tournamentService.delete(idTour);

			return new ResponseEntity<String>("Remove tournament", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Tournaments are ongoing or ending", HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteTeam")
	public ResponseEntity<?> deleteTeam(@RequestParam int idTeam) {
		return new ResponseEntity<String>(tournamentService.deleteTeam(idTeam), HttpStatus.OK);
	}

	@PostMapping(value = "edit")
	public ResponseEntity<String> updateInfo(@RequestBody Tournament tournament) {
		;
		return new ResponseEntity<String>(tournamentService.edit(tournament), HttpStatus.OK);
	}

	@PostMapping(value = "addTeam/{idTour}/{idTeam}")
	public ResponseEntity<String> addTeam(@PathVariable int idTour, @PathVariable int idTeam) {
		return new ResponseEntity<String>(tournamentService.addTeam(idTour, idTeam), HttpStatus.OK);
	}

	@GetMapping(value = "updateStatus")
	public ResponseEntity<String> updateStatus() {
		Date time = new Date(System.currentTimeMillis());
		int status = 0;
		for (Tournament tournament : tournamentService.getAll()) {
			if (time.compareTo(tournament.getTimeStart()) < 0) {
				status = 0;
				tournamentService.updateStatus(tournament.getIdTour(), status);
			} else if (time.compareTo(tournament.getTimeEnd()) >= 0) {
				status = 2;
				tournamentService.updateStatus(tournament.getIdTour(), status);

			} else {
				status = 1;
				tournamentService.updateStatus(tournament.getIdTour(), status);

			}
		}
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	// lấy Data theo status
	@GetMapping(value = "getByStatus")
	public ResponseEntity<List<Tournament>> getByStatus(@RequestParam int status, @RequestParam String type) {
		return new ResponseEntity<List<Tournament>>(tournamentService.getByStatus(status, type), HttpStatus.OK);
	}

	// Lấy data theo thể loại
	@GetMapping(value = "getByType")
	public ResponseEntity<List<Tournament>> getByType(@RequestParam String type) {
		return new ResponseEntity<List<Tournament>>(tournamentService.getByType(type), HttpStatus.OK);
	}

	// Hiển thị rank theo thể loại
	@GetMapping(value = "rank")
	public ResponseEntity<List<Rank>> rank(@RequestParam String type) {
		return new ResponseEntity<List<Rank>>(tournamentService.rank(type), HttpStatus.OK);
	}

	// Rank theo tour
	@GetMapping(value = "rankByTour")
	public ResponseEntity<List<Rank>> rankByTour(@RequestParam int idTour) {
		return new ResponseEntity<List<Rank>>(tournamentService.rankByTour(idTour), HttpStatus.OK);
	}

	// Hiển thị tất cả các giải đấu theo thể loại
	@GetMapping(value = "getToursByType")
	public ResponseEntity<List<ToursByType>> getToursByType() {
		return new ResponseEntity<List<ToursByType>>(tournamentService.getToursByType(), HttpStatus.OK);
	}

	// Hiển thị rank của tất cả các thể loại
	@GetMapping(value = "rankAll")
	public ResponseEntity<List<RankTeamsByType>> rankAll() {
		return new ResponseEntity<List<RankTeamsByType>>(tournamentService.rankAll(), HttpStatus.OK);
	}

}
