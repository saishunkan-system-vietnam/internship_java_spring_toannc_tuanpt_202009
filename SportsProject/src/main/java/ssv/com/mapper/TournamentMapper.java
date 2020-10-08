package ssv.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.Team;
import ssv.com.entity.Tournament;

@Mapper
public interface TournamentMapper {

	public void add(Tournament tournament);

	public List<Tournament> getAll();

	public Tournament getById(int idTour);

	public void delete(int idTour);

	public void updateStatus(int idTour, int status);

	public List<Tournament> getTourAction();

	public void edit(Tournament tournament);

	public List<Tournament> getByStatus(int status, String type);

	public List<Tournament> getByType(String type);


	
}
