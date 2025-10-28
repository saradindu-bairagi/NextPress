package com.nextpress.api.cms.post.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Post {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String status;
    private Long authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
