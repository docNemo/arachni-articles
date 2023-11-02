package ru.mai.arachni.articles.dto.request.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {
    @NotBlank
    private String title;

    @NotEmpty
    private List<@NotBlank String> categories;

    @NotBlank
    private String text;

    private String creator;

    private ZonedDateTime creationDate;
}
