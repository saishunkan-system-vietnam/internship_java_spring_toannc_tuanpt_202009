package ssv.com.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssv.com.entity.Profile;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
	private Integer id;
	private String username;
	private Profile profile;
}
