package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 5, message = "Name should at least be 5 characters")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Please provide a valid email id")
    @Size(min = 5, message = "Email should at least be 5 characters")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comment body should at least be 10 characters")
    private String body;
}
