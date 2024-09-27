package com.Emazon.Stock.adapters.driving.http.controller;

import com.Emazon.Stock.adapters.utilities.ArticleControllerConstants;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Emazon.Stock.domain.api.IArticleServicePort;
import com.Emazon.Stock.domain.api.ICategoryServicePort;
import com.Emazon.Stock.domain.api.IBrandServicePort;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleRequestMapper;
import com.Emazon.Stock.adapters.driving.http.mapper.IArticleResponseMapper;
import com.Emazon.Stock.adapters.driving.http.dto.request.AddArticleRequest;
import com.Emazon.Stock.domain.model.Article;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


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
    public ResponseEntity<String> addArticle( @Valid @RequestBody  AddArticleRequest articleRequest) {
        Article article = articleRequestMapper.addRequestToArticle(articleRequest, categoryServicePort, brandServicePort);
        articleService.saveArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ArticleControllerConstants.ARTICLE_CREATED_MESSAGE);
    }
}