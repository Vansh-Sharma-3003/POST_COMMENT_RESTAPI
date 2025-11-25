package PostAndCommentRestAPI.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
//@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Lob // Use @Lob for large text field
    @Column(nullable = false)
    private String contant;

    // mapping
    // mappedBy = "post" tells JPA that the 'post' field
    // in the Comment class manages this relationship.
    // cascade = CascadeType.ALL: If we delete a post, delete all its comments.
    // orphanRemoval = true: If we remove a comment from this list, delete it from the DB.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference // This side is the "manager"
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
