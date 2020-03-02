package io.wootlab.data;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.wootlab.data.model.Article;
import io.wootlab.data.model.ArticleWithContent;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class Endpoints {

    @Inject
    private ArticlesService articlesService;

    @Get("articles")
    public HttpResponse<List<Article>> findArticles(){

    }

    @Get("/articles/{url}")
    public HttpResponse<ArticleWithContent> getArticle(String url) throws IOException, ExecutionException, InterruptedException {
        return articlesService.findArticleByUrl(url)
                .map(content -> HttpResponse.ok(content))
                .orElse(HttpResponse.notFound());
    }

}