package com.vironit.correctapp.mvp.presentation.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.interactor.interfaces.NewsInteractor;
import com.vironit.correctapp.mvp.model.repository.db.converter.ArticleToArticleDBConverter;
import com.vironit.correctapp.mvp.model.repository.db.entity.ArticleDB;
import com.vironit.correctapp.mvp.model.repository.dto.Article;
import com.vironit.correctapp.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.INewsView;
import com.vironit.correctapp.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class NewsPresenter extends BasePaginationPresenter<INewsView> {

    @Inject
    NewsInteractor mNewsInteractor;

    @Inject
    Context context;

    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        super.loadData(totalItemCount, lastItemId);

            addPaginationDisposable(mNewsInteractor.getNews(totalItemCount + 1 / getItemsCountPerPage(), getItemsCountPerPage())
                    .observeOn(mUIScheduler)
                    .doOnSuccess(dataElement -> {
                        setNextPageAllow(dataElement.getArticles());
                        getViewState().addDataList(dataElement.getArticles());
                    })

//                .map((Function<List<Article>, List<ArticleDB>>) articles -> {
//                    List<ArticleDB> articlesDB = new ArrayList<>();
//                    for (Article article : articles) {
//                        articlesDB.add(ArticleToArticleDBConverter.articlesToArticlesDB(article));
//                    }
//                    ArticleDB[] resultList = new ArticleDB[articlesDB.size()];
//                    articlesDB.toArray(resultList);
//                    return articlesDB;
//                })

                    // .flatMap(dataElement -> mNewsInteractor.addNewsToDB(convertArticle(dataElement.getArticles())))
                    //.flatMap(dataElement -> mNewsInteractor.deleteNews(convertArticle(dataElement.getArticles())))
                    .doFinally(() -> getViewState().hidePaginationProgress())
                    .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                            this));
    }

    @Override
    protected int getItemsCountPerPage() {
        return 7;
    }

    @Override
    public void detachView(INewsView view) {
        super.detachView(view);
        refreshData();
    }

    private ArticleDB[] convertArticle(List<Article> articleList) {
        List<ArticleDB> articlesDB = new ArrayList<>();
        for (Article article : articleList) {
            articlesDB.add(ArticleToArticleDBConverter.articlesToArticlesDB(article));
        }
        ArticleDB[] resultList = new ArticleDB[articlesDB.size()];
        resultList = articlesDB.toArray(resultList);
        return resultList;
    }

    private List<Article> mapArticlesDB(List<ArticleDB> articlesDBList) {
        List<Article> resultList = new ArrayList<>();
        for (ArticleDB articleDB : articlesDBList) {
            resultList.add(ArticleToArticleDBConverter.articlesDBToArticl(articleDB));
        }
        return resultList;
    }
}
