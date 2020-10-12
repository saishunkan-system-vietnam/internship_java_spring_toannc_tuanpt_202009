package ssv.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.entity.Comment;
import ssv.com.service.CommentService;

@RestController
@RequestMapping("/api/v1/comment/")
@ResponseBody
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value="getBySchedule/{idSchedule}")
	public ResponseEntity<List<Comment>> getAll(@PathVariable int idSchedule){
		return new ResponseEntity<List<Comment>>(commentService.getBySchedule(idSchedule),HttpStatus.OK);
	}
	@PostMapping(value="addComment")
	public void addComment(@RequestBody Comment comment){
		commentService.addComment(comment);
	}
}
