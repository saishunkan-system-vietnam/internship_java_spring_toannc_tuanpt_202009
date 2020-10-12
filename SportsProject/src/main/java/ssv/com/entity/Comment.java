package ssv.com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private int idComment;
	private String text;
	private int idSchedule;
	private String nameProfile;
}
