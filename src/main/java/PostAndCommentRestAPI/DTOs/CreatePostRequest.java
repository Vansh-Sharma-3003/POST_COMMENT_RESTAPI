package PostAndCommentRestAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {
    @NotBlank(message = "Title Cannot Be Empty")
    @Size(min = 3 , message = "Title must be at lest 3 Characters long")
    private String title;

    @NotBlank(message = "Content Cannot Be Empty")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
