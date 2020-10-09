package ssv.com.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ssv.com.entity.Tournament;

@Setter
@Getter
public class ToursByType {
	private String type;
	private List<Tournament> tournament;
}
