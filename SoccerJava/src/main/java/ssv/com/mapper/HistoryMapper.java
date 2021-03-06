package ssv.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.History;

@Mapper
public interface HistoryMapper {

	void create(History history);

	void delete(int idTournament);

	void deleteTeam(int idTeam, int idTournament);

	List<History> historyMember( int idTour, int idTeam);

	List<History> getToursByTeam(int idTeam);

	List<Integer> getByTeam(int idTeam);
}
