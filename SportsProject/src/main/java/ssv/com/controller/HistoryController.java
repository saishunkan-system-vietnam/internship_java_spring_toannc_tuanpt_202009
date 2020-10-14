package ssv.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.dto.HistoryMemberDto;
import ssv.com.dto.ResponseQuery;
import ssv.com.service.HistoryService;

@RestController
@RequestMapping("/api/v1/history/")
public class HistoryController {
	@Autowired
	private HistoryService historyService;

	@GetMapping(value = "/{idMember}")
	public ResponseQuery<?> memberHistoryMatch(@PathVariable int idMember){
		List<HistoryMemberDto> list = historyService.memberHistoryMatchs(idMember);
		return ResponseQuery.success("Success", list);
	}
}
