package ssv.com.controller;

import java.io.File;
import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.multipart.MultipartFile;

import ssv.com.controller.form.TeamForm;
import ssv.com.dto.ScheduleDto;
import ssv.com.dto.TeamDto;
import ssv.com.entity.Profile;
import ssv.com.entity.Team;
import ssv.com.file.UploadFile;
import ssv.com.service.TeamService;

@RestController
@RequestMapping("/api/v1/team/")
public class TeamController {
	 ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private TeamService teamService;

	@GetMapping(value="getAll")
	public ResponseEntity<List<Team>> getAll(){
		return new ResponseEntity<List<Team>>(teamService.getAll(),HttpStatus.OK);
	}
	@GetMapping(value="getByTour/{idTournamet}")
	public ResponseEntity<List<Team>> getByTour(@PathVariable int idTournamet){
		return new ResponseEntity<List<Team>>(teamService.getByTour(idTournamet),HttpStatus.OK);
	}

	@GetMapping(value="getById")
	public ResponseEntity<Team> getById(@RequestParam int id){
		return new ResponseEntity<Team>(teamService.getById(id),HttpStatus.OK);
	}
	@PostMapping(value="createTeam")
	public ResponseEntity<String> createTeam(@ModelAttribute TeamForm teamForm ){
		String path="";
		try {
			path = UploadFile.saveFile(teamForm.getFile());
			Team team=modelMapper.map(teamForm, Team.class);
			team.setLogo(path);
			if(!teamService.checkTeam(team)) {
				teamService.save(team);
			return new ResponseEntity<String>("thanh cong",HttpStatus.OK);
			}
			return new ResponseEntity<String>("trung ten",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("loi",HttpStatus.OK);

	}
	@PostMapping(value="addMember")
	public ResponseEntity<String> addMember(@RequestBody Profile profile,@RequestParam int idTeam){
		if(teamService.checkMember(profile,idTeam)){
			teamService.addMember(profile,idTeam);
			return new ResponseEntity<String>("thanh cong",HttpStatus.OK);

		}
		return new ResponseEntity<String>("fail",HttpStatus.OK);

	}

	@PostMapping(value="deleteMember")
	public ResponseEntity<String> delteMember(@RequestBody Profile profile,@RequestParam int idTeam){
		if(teamService.checkMemberTeam(profile,idTeam)) {
			teamService.deleteMember(profile);
			return new ResponseEntity<String>("thanh cong",HttpStatus.OK);

		}
		return new ResponseEntity<String>("fail",HttpStatus.OK);

	}
	@GetMapping(value="search")
	public ResponseEntity<TeamDto> search(@RequestParam int page,@RequestParam int pageSize,@RequestParam String nameSearch,@RequestParam String type,@RequestParam String sorts){
		if(type=="") {
			type="id_schedule";
		};
		return new ResponseEntity<TeamDto>(teamService.search(page,pageSize*2,nameSearch,type,sorts),HttpStatus.OK);


	}



}
