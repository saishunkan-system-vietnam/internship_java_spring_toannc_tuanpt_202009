package ssv.com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String age;
	private String gender;
	private String address;
	private String type;
	private String avatar;
	private int idTeam;
	private Team team;
}