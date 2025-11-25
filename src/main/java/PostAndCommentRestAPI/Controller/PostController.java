package PostAndCommentRestAPI.Controller;


import PostAndCommentRestAPI.DTOs.CommentResponse;
import PostAndCommentRestAPI.DTOs.CreateCommentRequest;
import PostAndCommentRestAPI.DTOs.CreatePostRequest;
import PostAndCommentRestAPI.DTOs.PostResponse;
import PostAndCommentRestAPI.Service.PostAndCommentServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostAndCommentServices postAndCommentServices;

    public PostController(PostAndCommentServices postAndCommentServices) {
        this.postAndCommentServices = postAndCommentServices;
    }

    // --- Post Endpoints ---

    // CREATE: POST /api/posts
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        // ... logic to save post
        PostResponse response= postAndCommentServices.createPost(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ (All): GET /api/posts
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        // ... logic to get all posts
        List<PostResponse> responses = postAndCommentServices.getAllPosts();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // READ (One): GET /api/posts/{postId}

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        // ... logic to find one post
        PostResponse response=postAndCommentServices.getPostById(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Long postId){
        String message=postAndCommentServices.deletePostById(postId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    // --- Nested Comment Endpoints ---

    // CREATE Comment for a Post: POST /api/posts/{postId}/comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @Valid @RequestBody CreateCommentRequest request) {
        // ... logic to find post by postId, then create and associate the comment
        CommentResponse response= postAndCommentServices.createComment(postId,request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    // READ all Comments for a Post: GET /api/posts/{postId}/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsForPost(@PathVariable Long postId) {
        // ... logic to find post and return its comments
        List<CommentResponse> responses=postAndCommentServices.getCommentsForPost(postId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


}
