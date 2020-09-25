package ssv.com.controller.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamForm {
	private int idTeam;
	private String nameTeam;
	private String type;
	private String description;
	private String logo;
	private int idTeam1;
	private int idTeam2;
	private MultipartFile file;

}
