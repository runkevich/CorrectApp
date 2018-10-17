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

//                    .map(new io.reactivex.functions.Function<Data, Object>()  {
//                        @Override
//                        public Object apply(Data data) throws Exception {
//                            List<ArticleDB> articlesDB = new ArrayList<>();
//
//                            for (Article article : data.getArticles()) {
//                                articlesDB.add(ArticleToArticleDBConverter.articlesToArticlesDB(article));
//                            }
//                            ArticleDB[] resultList = new ArticleDB[articlesDB.size()];
//                            articlesDB.toArray(resultList);
//                            return articlesDB;
//                        }
//                    })

                    // .flatMap(dataElement -> mNewsInteractor.addNewsToDB(convertArticle(dataElement.getArticles())))
                    .flatMap(dataElement -> mNewsInteractor.deleteNews(convertArticle(dataElement.getArticles())))
                    .doFinally(() -> getViewState().hidePaginationProgress())
                    .subscribe(list -> AppLog.logPresenter(this, "OOOOOKKKKK"),
                            this));
    }

    @Override
    protected int getItemsCountPerPage() {
        return 7;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refreshData();
    }

    private ArticleDB[] convertArticle(List<Article> articleList) {
        List<ArticleDB> articlesDB = new ArrayList<>();
        for (Article article : articleList) {
            articlesDB.add(ArticleToArticleDBConverter.articlesToArticlesDB(article));
        }
        ArticleDB[] articleDBS = new ArticleDB[articlesDB.size()];
        articleDBS = articlesDB.toArray(articleDBS);
        return articleDBS;
    }

    private List<Article> convertArticleDB(List<ArticleDB> articleDBList) {
        List<Article> articles = new ArrayList<>();
        for (ArticleDB articleDB : articleDBList) {
            articles.add(ArticleToArticleDBConverter.articlesDBToArticl(articleDB));
        }
        return articles;
    }
}
