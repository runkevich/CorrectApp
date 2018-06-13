package com.vironit.correctapp.mvp.presentation.adapter.news;

public interface NewsFeedListItem {
    int postType = 1;
    int advertisingType = 2;

    /**
     * Method witch return type of ViewHolder class
     * @return type of ListItem interface parameters
     */
    int getNewsFeedListItemType();
}
