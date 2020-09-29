package ssv.com.controller.form;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Team;

@Setter
@Getter
public class ScheduleForm {
	private int idSchedule;
	private int scoreTeam1;
	private int scoreTeam2;
	private int idTeam1;
	private int idTeam2;
	private String description;
	private String title;
	private MultipartFile fileImage;
	private MultipartFile fileVideo;



}
