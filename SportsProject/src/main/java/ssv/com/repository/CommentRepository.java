package ssv.com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssv.com.entity.Comment;
import ssv.com.mapper.CommentMapper;

@Repository
public class CommentRepository {
	@Autowired
	private CommentMapper commentMapper;

	public List<Comment> getBySchedule(int idSchedule) {
		// TODO Auto-generated method stub
		return commentMapper.getBySchedule(idSchedule);
	}

	public void addComment(Comment comment) {
		 commentMapper.addComment(comment);
		
	}
}
