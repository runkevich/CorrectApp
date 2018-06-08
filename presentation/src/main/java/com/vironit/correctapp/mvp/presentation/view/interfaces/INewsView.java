package com.vironit.correctapp.mvp.presentation.view.interfaces;

import com.vironit.correctapp.mvp.model.repository.dto.Article;

/*@StateStrategyType(SkipStrategy.class)*/
public interface INewsView extends IBasePaginationView,IAddListData<Article> {

    //void showNews();
    //void dataReceived(boolean isAfterRefresh, @Nullable Data data);
}
