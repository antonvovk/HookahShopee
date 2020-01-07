package com.wolf.hookahshopee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "NAME", columnDefinition = "NVARCHAR(200)", unique = true, nullable = false)
    private String name;

    @Column(name = "IMAGE_NAME", columnDefinition = "VARCHAR(100)")
    private String imageName;

    @Column(name = "HTML_CONTENT", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String htmlContent;
}
