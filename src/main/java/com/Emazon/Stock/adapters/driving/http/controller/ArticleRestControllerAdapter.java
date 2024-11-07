package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.utilities.ArticleControllerConstants;
import com.Emazon.Stock.domain.api.IArticleServicePort;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleResponseMapper;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddArticleRequest;
import com.Emazon.Stock.adapters.driving.http.dto.response.ArticleResponse;
import com.Emazon.Stock.domain.model.Article;
import com.Emazon.Stock.domain.utilities.PagedResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/article")
public class ArticleRestControllerAdapter {

    private final IArticleServicePort articleService;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;
    private final ICategoryServicePort categoryServicePort;
    private final IBrandServicePort brandServicePort;

    public ArticleRestControllerAdapter(
            IArticleServicePort articleService,
            IArticleRequestMapper articleRequestMapper,
            IArticleResponseMapper articleResponseMapper,
            ICategoryServicePort categoryServicePort,
            IBrandServicePort brandServicePort) {
        this.articleService = articleService;
        this.articleRequestMapper = articleRequestMapper;
        this.articleResponseMapper = articleResponseMapper;
        this.categoryServicePort = categoryServicePort;
        this.brandServicePort = brandServicePort;
    }

    @PostMapping("/")
    @Operation(summary = ArticleControllerConstants.OPERATION_SUMMARY_ADD_ARTICLE,
            description = ArticleControllerConstants.OPERATION_DESCRIPTION_ADD_ARTICLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_201,
                    description = ArticleControllerConstants.RESPONSE_CODE_201_DESCRIPTION),
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_400,
                    description = ArticleControllerConstants.RESPONSE_CODE_400_DESCRIPTION),
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_409,
                    description = ArticleControllerConstants.RESPONSE_CODE_409_DESCRIPTION)
    })
    public ResponseEntity<String> addArticle(@Valid @RequestBody AddArticleRequest articleRequest) {
        Article article = articleRequestMapper.addRequestToArticle(articleRequest, categoryServicePort, brandServicePort);
        articleService.saveArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ArticleControllerConstants.ARTICLE_CREATED_MESSAGE);
    }

    @GetMapping("/paged")
    @Operation(summary = ArticleControllerConstants.OPERATION_SUMMARY_GET_PAGED_ARTICLES,
            description = ArticleControllerConstants.OPERATION_DESCRIPTION_GET_PAGED_ARTICLES)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_200,
                    description = ArticleControllerConstants.RESPONSE_CODE_200_DESCRIPTION),
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_400,
                    description = ArticleControllerConstants.RESPONSE_CODE_400_DESCRIPTION),
            @ApiResponse(responseCode = ArticleControllerConstants.RESPONSE_CODE_404,
                    description = ArticleControllerConstants.RESPONSE_CODE_404_DESCRIPTION)
    })
    public ResponseEntity<PagedResult<ArticleResponse>> getPagedArticles(
            @Parameter(description = ArticleControllerConstants.PARAM_PAGE_DESCRIPTION)
            @RequestParam(value = ArticleControllerConstants.PAGE_PARAM,
                    defaultValue = ArticleControllerConstants.DEFAULT_PAGE) Integer page,
            @Parameter(description = ArticleControllerConstants.PARAM_SIZE_DESCRIPTION)
            @RequestParam(value = ArticleControllerConstants.SIZE_PARAM,
                    defaultValue = ArticleControllerConstants.DEFAULT_SIZE) Integer size,
            @Parameter(description = ArticleControllerConstants.PARAM_SORT_BY_DESCRIPTION)
            @RequestParam(value = ArticleControllerConstants.SORT_BY_PARAM,
                    defaultValue = ArticleControllerConstants.DEFAULT_SORT_BY) String sortBy,
            @Parameter(description = ArticleControllerConstants.PARAM_ASCENDING_DESCRIPTION)
            @RequestParam(value = ArticleControllerConstants.ASCENDING_PARAM,
                    defaultValue = ArticleControllerConstants.DEFAULT_SORT_DIRECTION) boolean ascending) {

        PagedResult<Article> pagedArticles = articleService.getPagedArticles(page, size, sortBy, ascending);
        PagedResult<ArticleResponse> response = articleResponseMapper.toArticleResponsePagedResult(pagedArticles);
        return ResponseEntity.ok(response);
    }
}