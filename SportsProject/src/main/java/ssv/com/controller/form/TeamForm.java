package ssv.com.controller.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamForm {
	private int idTeam;
	private String nameTeam;
	private String type;
	private String description;
	private MultipartFile file;
}
