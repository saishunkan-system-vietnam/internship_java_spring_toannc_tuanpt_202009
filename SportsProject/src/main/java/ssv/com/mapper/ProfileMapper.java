package ssv.com.mapper;

import java.util.List;
import java.util.Optional;

import ssv.com.entity.Account;
import ssv.com.entity.Profile;

public interface ProfileMapper {
	public Long saveProfile(Profile profile);

	public Profile findByEmail(String email);

	public Optional<Profile> findById(int id);

	public List<Profile> findAllProfile();

	public Long deleteProfile(Integer id);

	public List<Account> getMembersEmailByRole();

	public List<Account> pageProfile(int page, int pagesize,String name,String nametype);

	public List<Profile> getByIdTeam(int idTeam);
	public List<Profile> getMembers();
}
