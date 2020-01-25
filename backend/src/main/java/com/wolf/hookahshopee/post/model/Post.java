package com.wolf.hookahshopee.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", unique = true, nullable = false)
    private Long id;

    @Column(name = "UUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "NAME", columnDefinition = "NVARCHAR(200)", nullable = false)
    private String name;

    @Column(name = "IMAGE_NAME", columnDefinition = "VARCHAR(100)")
    private String imageName;

    @Column(name = "HTML_CONTENT", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String htmlContent;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
