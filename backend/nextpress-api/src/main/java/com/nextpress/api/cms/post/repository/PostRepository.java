package com.nextpress.api.cms.post.repository;

import com.nextpress.api.cms.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
