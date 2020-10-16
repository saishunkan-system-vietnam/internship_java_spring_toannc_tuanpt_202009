package ssv.com.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssv.com.RandomPass;
import ssv.com.controller.form.ProfileForm;
import ssv.com.dto.MemberInfoDTO;
import ssv.com.dto.ResponseQuery;
import ssv.com.entity.Account;
import ssv.com.entity.Profile;
import ssv.com.entity.Team;
import ssv.com.file.UploadFile;
import ssv.com.repository.AccountRepository;
import ssv.com.repository.HistoryRepository;
import ssv.com.repository.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HistoryRepository historyRepository;

	public Long save(Profile profile) {
		return profileRepository.saveProfile(profile);
	}

	public Profile findById(int id) {
		return profileRepository.findById(id);
	}

	public List<Profile> findAllByTeam(int id) {
		return profileRepository.findAllByTeam(id);
	}

	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

	public Long delete(Integer profileId) {
		return profileRepository.delete(profileId);
	}

	public List<MemberInfoDTO> getMembersEmailByRole() {
		List<MemberInfoDTO> members = profileRepository.getMembersEmailByRole().stream().map(account -> {
			return modelMapper.map(account, MemberInfoDTO.class);
		}).collect(Collectors.toList());
		return members;
	}

	public void saveProfile(Profile profile) {
		try {
			profileRepository.saveProfile(profile);

		} catch (Exception e) {
			ResponseQuery.faild("Create profile failed", profile);
		}

	}

	@Transactional
	public ResponseQuery<?> saveMember(ProfileForm profileForm) throws Exception {
		Random rand = new Random();
		if (profileRepository.getByEmail(profileForm.getEmail()) != null) {
			return ResponseQuery.faild("Email has already exsists", null);
		} else {

			Account account = new Account();
			account.setEmail(profileForm.getEmail());
			String pass = new RandomPass().randomAlphaNumeric(8);
			account.setPassword(BCrypt.hashpw("123456789", BCrypt.gensalt(12)));
			account.setUsername(profileForm.getName().toLowerCase().replace(" ", "") + rand.nextInt(900) + 100);
			account.setRole("ROLE_MEMBER");

			Profile profile = modelMapper.map(profileForm, Profile.class);
			profile.setAvatar(UploadFile.saveFile(profileForm.getFile()));
			accountRepository.add(account);
			profileRepository.saveProfile(profile);

//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(profileForm.getEmail());
//			message.setSubject("User và password");
//			message.setText(account.getUsername() + "-" + pass);
//			this.emailSender.send(message);

			return ResponseQuery.success("Created memeber succcess", profile);
		}
	}

	public List<Account> pageProfile(int page, int pagesize, String name, String nametype) {
//		return accountRepository.pageUser(page,pagesize,name,nametype);
		return null;
	}

	public void newTour(int idTeam) {
		for (Profile profile : profileRepository.getByIdTeam(idTeam)) {
			historyRepository.addTournament(profile.getId(), idTeam);
		}
	}

	public List<Profile> getMembers() {
		return profileRepository.getMembers();

	}

	public void updateMembersInTeam(Team team) {
		profileRepository.updateMembersInTeam(team);
	}

	public Account updateProfileUser(ProfileForm profileForm) {
		Profile profile = modelMapper.map(profileForm, Profile.class);

		try {
			profile.setAvatar(UploadFile.saveFile(profileForm.getFile()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		profileRepository.updateProfileUser(profile);
		Account account = accountRepository.findByUsername(profile.getEmail());
		return account;
	}

	public Profile findByEmail(String email) {
		return profileRepository.getByEmail(email);
	}
}
