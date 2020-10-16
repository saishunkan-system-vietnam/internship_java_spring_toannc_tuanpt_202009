package ssv.com.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import ssv.com.controller.form.ProfileForm;
import ssv.com.dto.ResponseQuery;
import ssv.com.entity.Account;
import ssv.com.entity.Profile;
import ssv.com.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@GetMapping("/all")
	public List<Profile> allProfiles() {
		return profileService.findAll();
	}

	@GetMapping("/members")
	public List<Profile> members() {
		return profileService.getMembers();
	}

	@GetMapping("/getAllByTeam/{id}")
	public List<Profile> getMembersByTeam(@PathVariable(value = "id") int id) {
		return profileService.findAllByTeam(id);
	}

	@GetMapping(path = "/{profileId}")
	public Profile getProfileById(@PathVariable(value = "profileId") int profileId) {
		return profileService.findById(profileId);
	}

	@PostMapping("/createUser")
	public ResponseQuery<?> createProfile(@RequestBody Profile profile) {

		Long profile2 = profileService.save(profile);
		if(profile2 != null) {
			return ResponseQuery.success("Success", profile2);
		}
		return ResponseQuery.faild("Failed", null);
	}
	//Edit profile user
	@PostMapping("/update")
	public ResponseQuery<?> updateProfileUser(@ModelAttribute ProfileForm profileForm){
		Account account = profileService.updateProfileUser(profileForm);
		if(account != null) {
			return ResponseQuery.success("Success", account);
		}
		return ResponseQuery.faild("Failed", null);
	}

	@PostMapping("/createMember")
	public ResponseQuery<?> createMember(@ModelAttribute ProfileForm profileForm) throws Exception {
		return profileService.saveMember(profileForm);
	}

	@DeleteMapping("/{profileId}")
	public ResponseEntity<?> deleteProject(@PathVariable Integer profileId) {
		profileService.delete(profileId);
		return new ResponseEntity<String>("Profile with ID: '" + profileId + "' was deleted", HttpStatus.OK);
	}

	@GetMapping(value = "/page/{page}/{pagesize}")
	public ResponseEntity<List<Account>> pageProfile(@PathVariable int page, @PathVariable int pagesize) {
		List<Account> list = new ArrayList<Account>();
		String a = "account_email";
		list = profileService.pageProfile(page, pagesize, "%%", a);
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/search/{page}/{pagesize}/")
	public ResponseEntity<List<Account>> pageProfileSearch(@PathVariable int page, @PathVariable int pagesize,
			@RequestParam String name, @RequestParam String nametype) {
		List<Account> list = new ArrayList<Account>();
		list = profileService.pageProfile(page, pagesize, "%" + name + "%", nametype);
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}
}
