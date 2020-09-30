package ssv.com.controller.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileForm {
	private String name;
	private String email;
	private String phone;
	private String age;
	private String gender;
	private String address;
	private MultipartFile file;
}
