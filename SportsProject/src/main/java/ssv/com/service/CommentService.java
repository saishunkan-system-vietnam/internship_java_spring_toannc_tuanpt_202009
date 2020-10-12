package ssv.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssv.com.entity.Comment;
import ssv.com.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;


	public List<Comment> getBySchedule(int idSchedule) {
		// TODO Auto-generated method stub
		return commentRepository.getBySchedule(idSchedule);
	}


	public void addComment(Comment comment) {
		commentRepository.addComment(comment);
		
	}
}
