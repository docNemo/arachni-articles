package ru.mai.arachni.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticleListResponse {
    private List<ArticlePreviewResponse> articles;
    private Long count;
}