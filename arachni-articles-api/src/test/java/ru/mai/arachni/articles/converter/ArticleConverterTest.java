package ru.mai.arachni.articles.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mai.arachni.articles.dto.response.article.ArticlePreviewResponse;
import ru.mai.arachni.articles.dto.response.article.ArticleResponse;
import ru.mai.arachni.articles.core.domain.Article;
import ru.mai.arachni.articles.core.domain.Category;
import ru.mai.arachni.articles.core.domain.Creator;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleConverterTest {
    private Article article;
    private ArticleResponse expectedArticleResponse;
    private ArticlePreviewResponse expectedArticlePreviewResponse;
    private ArticleConverter articleConverter;

    @BeforeEach
    void setup() {
        final Long idArticle = 10L;
        final Long idCreator = 5L;
        final String strCreator = "Автор";
        final String title = "Название";
        final String text = "Это какой-то случайный текст";
        final List<Long> idCategories = List.of(0L, 1L);
        final List<String> strCategories = List.of("Категория 1", "Категория 2");
        final LocalDateTime dateTime = LocalDateTime.now();
        final String fileName = "файлик";
        final boolean isCrawled = true;

        articleConverter = new ArticleConverter();

        Creator creator = new Creator(idCreator, strCreator);

        List<Category> categories = List.of(
                new Category(idCategories.get(0), strCategories.get(0)),
                new Category(idCategories.get(1), strCategories.get(1))
        );

        article = new Article(
                idArticle,
                title,
                categories,
                creator,
                dateTime,
                fileName,
                isCrawled
        );

        expectedArticleResponse = new ArticleResponse(
                idArticle,
                title,
                strCategories,
                strCreator,
                dateTime,
                fileName,
                text,
                isCrawled
        );

        expectedArticlePreviewResponse = new ArticlePreviewResponse(
                idArticle,
                title,
                strCategories,
                strCreator,
                dateTime,
                fileName,
                isCrawled
        );
    }

    @Test
    void testConvertArticleToArticleResponse() {
        ArticleResponse actualArticleResponse =
                articleConverter.convertArticleToArticleResponse(article, expectedArticleResponse.getText());
        assertEquals(expectedArticleResponse, actualArticleResponse);
    }

    @Test
    void testConvertArticleToArticlePreviewResponse() {
        ArticlePreviewResponse actualArticlePreviewResponse =
                articleConverter.convertArticleToArticlePreviewResponse(article);
        assertEquals(expectedArticlePreviewResponse, actualArticlePreviewResponse);
    }
}
