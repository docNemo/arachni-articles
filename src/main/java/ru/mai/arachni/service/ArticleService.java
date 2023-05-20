package ru.mai.arachni.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.mai.arachni.converter.ArticleConverter;
import ru.mai.arachni.domain.Article;
import ru.mai.arachni.dto.response.ArticleResponse;
import ru.mai.arachni.exception.ArachniError;
import ru.mai.arachni.exception.ArachniException;
import ru.mai.arachni.repository.ArticleRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Transactional(readOnly = true)
    public ArticleResponse getArticle(final Long idArticle) {
        Optional<Article> article = articleRepository.findById(idArticle);
        if (article.isEmpty()) {
            throw new ArachniException(
                    ArachniError.ARTICLE_NOT_FOUND,
                    "id_article: " + idArticle
            );
        }
        return articleConverter.convertArticleToArticleResponse(article.get());
    }

    @Transactional
    public void deleteArticle(final Long idArticle) {
        articleRepository.deleteById(idArticle);
    }
}
