package PostAndCommentRestAPI.Repo;

import PostAndCommentRestAPI.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long>{
    List<Comment> findAllByPostId(Long postId);
  /*  public List<Comment> findCommentsForPost(Long post_id);{

    }*/
}
