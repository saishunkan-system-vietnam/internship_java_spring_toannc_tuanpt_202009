package ssv.com.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.Schedule;
import ssv.com.entity.Team;

@Mapper
public interface TeamMapper {
	public List<Team> findAllTeams();

	List<Team> getAll();

	Team getByID(int idTeam);

	Team findById(int teamId);

	void save(Team team);

	void updateTourNew(int idTeam);

	void formatTour(int idTour);

	public void deleteTeam(int id);


	public Team findByName(String name);

	public void addMember(Long idProfile, int idTeam);

	public void updateTour(int idTeam, int idTour);

	public void deleteMember(Long idProfile);

	public Set<Schedule> selectAllMatchsByTeam(int idTeam);

	public Set<Schedule> selectAllMatchsWinByTeam(int idTeam);

	public List<Team> getByTour(int idTournament);

	public int searchTotal(String nameSearch, String type);

	public List<Team> search(int page, int pageSize, String nameSearch, String type, String sorts);

	public List<Team> teamWait(String type);


	public void formatTourById(int idTour, int idTeam);

	public void addTour(int idTour, int idTeam);

	public Team teamTourHistory(int idTeam, int idTour);

}
