package ssv.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ssv.com.entity.Account;
import ssv.com.entity.Profile;
import ssv.com.exception.ResourceNotFoundException;
import ssv.com.mapper.ProfileMapper;

@Repository
public class ProfileRepository {
	private final ProfileMapper profileMapper;

	public ProfileRepository(ProfileMapper memberMapper) {
		this.profileMapper = memberMapper;
	}

	public Long saveProfile(Profile profile) {
		return profileMapper.saveProfile(profile);
	}

	public Profile findById(int id) {
		return profileMapper.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Not found memberId = %d", id)));
	}

	public List<Profile> findAll() {
		return profileMapper.findAllProfile();
	}

	public Long delete(int profileId) {
		return profileMapper.deleteProfile(profileId);
	}

	public List<Account> getMembersEmailByRole() {
		return profileMapper.getMembersEmailByRole();
	}

	public Profile getByEmail(String email) {
		return profileMapper.findByEmail(email);
	}

	public List<Account> pageUser(int page, int pagesize,String name,String nametype) {
		return profileMapper.pageProfile(page*pagesize,pagesize,name,nametype);
	}
}
