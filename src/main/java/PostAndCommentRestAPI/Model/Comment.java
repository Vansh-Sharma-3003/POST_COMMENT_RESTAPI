package PostAndCommentRestAPI.Model;

import jakarta.persistence.*;

@Entity
//@Table(name ="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Lob
    @Column(nullable = false)
    private String content;

    // mapping
    // @ManyToOne: Many comments can belong to one post.
    // @JoinColumn: Specifies the foreign key column in this table.
    @ManyToOne(fetch = FetchType.LAZY) // LAZY fetching is efficient
    @JoinColumn(name = "post_id", nullable = false)
    //@JsonBackReference // This side "refers back" and will be skipped in JSON
    private Post post;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
