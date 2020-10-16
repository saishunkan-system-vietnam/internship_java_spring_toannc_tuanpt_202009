package ssv.com.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.controller.form.PaginateForm;
import ssv.com.controller.form.TeamForm;
import ssv.com.dto.PaginateDto;
import ssv.com.dto.ResponseQuery;
import ssv.com.dto.ScheduleDto;

import ssv.com.dto.TeamDetail;
import ssv.com.dto.TeamDto;
import ssv.com.entity.Profile;
import ssv.com.entity.Team;
import ssv.com.file.UploadFile;
import ssv.com.service.ProfileService;
import ssv.com.service.TeamService;

@RestController
@RequestMapping("/api/v1/team/")
public class TeamController {
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private TeamService teamService;

	@Autowired
	private ProfileService profileService;

	@GetMapping(value = "getAll")
	public ResponseEntity<List<Team>> getAll() {
		return new ResponseEntity<List<Team>>(teamService.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "getByTour/{idTournamet}")
	public ResponseEntity<List<Team>> getByTour(@PathVariable int idTournamet) {
		return new ResponseEntity<List<Team>>(teamService.getByTour(idTournamet), HttpStatus.OK);
	}

	@GetMapping(value = "findDetail/{id}")
	public ResponseEntity<Team> getById(@PathVariable(value = "id") int id) {
		return new ResponseEntity<Team>(teamService.getById(id), HttpStatus.OK);
	}

	@GetMapping(value = "{teamId}")
	public ResponseEntity<Team> findById(@PathVariable(value = "teamId") int id) {
		return new ResponseEntity<Team>(teamService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "createTeam")
	public ResponseQuery<?> createTeam(@ModelAttribute TeamForm teamForm) {
		String path = "";
		try {
			path = UploadFile.saveFile(teamForm.getFile());
			Team team = modelMapper.map(teamForm, Team.class);
			team.setLogo(path);
			if (!teamService.checkTeam(team)) {
				teamService.save(team);
				return ResponseQuery.success("Success",team );
			}
			return ResponseQuery.faild("Name Team Taken", team);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseQuery.faild("Create fail", null);

	}

	@PostMapping(value = "deleteMember")
	public ResponseEntity<String> delteMember(@RequestBody Profile profile, @RequestParam int idTeam) {
		if (teamService.checkMemberTeam(profile, idTeam)) {
			teamService.deleteMember(profile);
			return new ResponseEntity<String>("success", HttpStatus.OK);

		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);

	}


//	@GetMapping(value = "search")
//	public ResponseEntity<TeamDto> search(@RequestParam int page, @RequestParam int pageSize,
//			@RequestParam String nameSearch, @RequestParam String type, @RequestParam String sorts) {
//		if (type == "") {
//			type = "id_schedule";
//		}
//		;
//		return new ResponseEntity<TeamDto>(teamService.search(page, pageSize * 2, nameSearch, type, sorts),
//				HttpStatus.OK);
//
//	}

	@GetMapping(value = "detail")
	public ResponseEntity<TeamDetail> detailTeam(@RequestParam int idTeam, @RequestParam int idTour) {
		return new ResponseEntity<TeamDetail>(teamService.detail(idTeam, idTour), HttpStatus.OK);
	}

	@GetMapping(value = "teamWait")
	public ResponseEntity<List<Team>> teamWait(@RequestParam String type) {
		return new ResponseEntity<List<Team>>(teamService.teamWait(type), HttpStatus.OK);
	}

	@PostMapping(value = "updateMembersInTeam")
	public ResponseQuery<?> update(@RequestBody Team team) {
		profileService.updateMembersInTeam(team);
		return ResponseQuery.success("Update Success", null);
	}

	@PostMapping(value = "updateInfo/{id}")
	public ResponseQuery<?> updateTeamInfo(@PathVariable (value = "id") int id, @ModelAttribute TeamForm teamForm){
		String path = "";
		try {
			path = UploadFile.saveFile(teamForm.getFile());
			Team team = modelMapper.map(teamForm, Team.class);
			team.setLogo(path);
			teamService.updateTeam(id, team);
			return ResponseQuery.success("Update Success", team);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseQuery.success("failed", null);
	}

	@GetMapping(value = "teamTourHistory")
	public ResponseEntity<Team> teamTourHistory(@RequestParam int idTeam, @RequestParam int idTour) {
		return new ResponseEntity<Team>(teamService.teamTourHistory(idTeam, idTour), HttpStatus.OK);
	}

	@GetMapping(value = "search")
	public ResponseQuery<PaginateDto<List<Team>>> search(@RequestBody PaginateForm form){
		PaginateDto<List<Team>> pages = new PaginateDto<List<Team>>();
		List<Team> teams = teamService.search(form);
		pages.setData(teams);
		pages.setCurrentPage(form.getCurrentPage());
		pages.setTotalPage(teamService.getCountAll());
		return ResponseQuery.success("Success", pages);
	}
}
