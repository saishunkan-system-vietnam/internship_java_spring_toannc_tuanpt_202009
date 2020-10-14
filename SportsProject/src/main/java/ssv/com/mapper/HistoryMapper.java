package ssv.com.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ssv.com.dto.HistoryMemberDto;
import ssv.com.entity.History;

@Mapper
public interface HistoryMapper {
	void addTournament(Long idMember, int idTeam);

	void deleteTournament(int idTour);

	void addTeamTournament(Long idMember, int idTour, int idTeam);

	void deleteTeamTournament(int idTour, int idTeam);

	List<History> getTeamByTour(int idTour);

	List<HistoryMemberDto> memberHistoryMatchs(int idMember);

}
