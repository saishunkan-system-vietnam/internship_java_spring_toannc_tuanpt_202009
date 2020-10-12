package ssv.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ssv.com.entity.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> getBySchedule(int idSchedule);

	 void addComment(Comment comment);

}
