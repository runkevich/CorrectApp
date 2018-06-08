package com.vironit.correctapp.mvp.model.repository.dto.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.vironit.correctapp.R;



public class MyItemMarkerRender extends DefaultClusterRenderer<MyItem> {

    private final IconGenerator mClusterIconGenerator;
    private final Context mContext;

    public MyItemMarkerRender(Context context, GoogleMap map,
                              ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;
        mClusterIconGenerator = new IconGenerator(mContext.getApplicationContext());
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<MyItem> cluster) {
        return super.shouldRenderAsCluster(cluster);

    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        final BitmapDescriptor markerDescriptor =
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
        markerOptions.icon(markerDescriptor).snippet(item.getTitle());
    }


    @Override
    protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
        mClusterIconGenerator.setBackground(
                ContextCompat.getDrawable(mContext, R.drawable.ic_local_taxi_black_24dp));

        mClusterIconGenerator.setTextAppearance(R.style.amu_Bubble_TextAppearance_Dark);

        final Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }
}
//1) один ко многим связь
//2) adapter - list<long>
//3) inner class
//4) index