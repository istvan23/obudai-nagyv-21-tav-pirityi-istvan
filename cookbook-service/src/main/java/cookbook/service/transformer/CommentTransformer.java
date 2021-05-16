package cookbook.service.transformer;

import cookbook.persistence.entity.Comment;
import cookbook.service.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentTransformer {
    public CommentDTO convertToDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setDescription(comment.getDescription());
        commentDTO.setTimestamp(comment.getTimestamp());
        return commentDTO;
    }

    public Comment convertToEntity(CommentDTO comment){
        Comment commentEntity = new Comment();
        commentEntity.setDescription(comment.getDescription());
        commentEntity.setTimestamp(comment.getTimestamp());
        return commentEntity;
    }
}
