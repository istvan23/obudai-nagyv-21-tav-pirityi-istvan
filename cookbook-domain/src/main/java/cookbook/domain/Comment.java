package cookbook.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String description;
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(Long id, String description, LocalDateTime timestamp) {
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
