package cookbook.service.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String description;
    private LocalDateTime timestamp;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String description, LocalDateTime timestamp) {
        this.id = id;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
