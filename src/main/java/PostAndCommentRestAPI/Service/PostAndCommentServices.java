package PostAndCommentRestAPI.Service;


import PostAndCommentRestAPI.DTOs.CommentResponse;
import PostAndCommentRestAPI.DTOs.CreateCommentRequest;
import PostAndCommentRestAPI.DTOs.CreatePostRequest;
import PostAndCommentRestAPI.DTOs.PostResponse;
import PostAndCommentRestAPI.Model.Comment;
import PostAndCommentRestAPI.Model.Post;
import PostAndCommentRestAPI.Repo.CommentRepo;
import PostAndCommentRestAPI.Repo.PostRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostAndCommentServices {

    private CommentRepo commentRepo;
    private PostRepo postRepo;

    public PostAndCommentServices(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    public PostResponse createPost(@Valid CreatePostRequest request) {
        Post post=new Post();
        post.setTitle(request.getTitle());
        post.setContant(request.getContent());

        Post savedPost=postRepo.save(post);

        return convertToPostResponse(savedPost);
    }


    public List<PostResponse> getAllPosts() {
        List<Post> posts=postRepo.findAll();

        return posts.stream()
                .map(this::convertToPostResponse)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long postId) {
        Post post= postRepo.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found with id : "+postId));

        return convertToPostResponse(post);
    }

    public String deletePostById(Long postId) {
        postRepo.deleteById(postId);
        return "Post Deleted..";
    }

    public CommentResponse createComment(Long postId, @Valid CreateCommentRequest request) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found with id : "+postId));

        Comment comment=new Comment();
        comment.setAuthor(request.getAuthor());
        comment.setContent(request.getContent());
        comment.setPost(post);

        Comment savedComment= commentRepo.save(comment);

        return convertToCommentResponse(savedComment);
    }

    public List<CommentResponse> getCommentsForPost(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found with id : "+postId));

        return post.getComments().stream()
                .map(this::convertToCommentResponse)
                .collect(Collectors.toList());
    }

    //-------  HELPER METHODS FOR MAPING ----------

    private PostResponse convertToPostResponse(Post post){
        PostResponse response=new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContant());

        if (post.getComments() !=null){
            List<CommentResponse> commentResponses=post.getComments().stream()
                    .map(this::convertToCommentResponse)
                    .collect(Collectors.toList());
            response.setComments(commentResponses);
        }
        return response;
    }

    private CommentResponse convertToCommentResponse(Comment comment){

        CommentResponse response=new CommentResponse();
        response.setId(comment.getId());
        response.setAuthor(comment.getAuthor());
        response.setContent(comment.getContent());

        return response;
    }


}
