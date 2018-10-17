package com.vironit.correctapp.mvp.presentation.view.implementation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.vironit.correctapp.R;
import com.vironit.correctapp.mvp.model.repository.dto.maps.MyItem;
import com.vironit.correctapp.mvp.model.repository.dto.maps.MyItemMarkerRender;
import com.vironit.correctapp.mvp.presentation.presenter.MapPresenter;
import com.vironit.correctapp.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IMapView;

import butterknife.OnClick;

public class MapFragment extends BaseFragment<MapPresenter> implements IMapView,OnMapReadyCallback{

    @InjectPresenter
    MapPresenter mMapPresenter;

    private ClusterManager<MyItem> mClusterManager;
    private GoogleMap myMap;
    private MapView mMapView;
    private View rootView;

    @OnClick(R.id.btn_show_maps)
    void showMap(){
    }

    @Override
    protected MapPresenter getPresenter() {
        return mMapPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_map;
    }

    public static MapFragment newInstance(){
        return new MapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        MapsInitializer.initialize(this.getActivity());
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return rootView;
    }

    @Override
    public void showMaps() {
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mClusterManager = new ClusterManager<MyItem>(this.getActivity(),googleMap);

        final MyItemMarkerRender renderer = new MyItemMarkerRender(this.getActivity(), googleMap, mClusterManager);
        mClusterManager.setRenderer(renderer);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        addItems();

        LatLng someCity = new LatLng(51.503186, -0.126446);
        //googleMap.addMarker(new MarkerOptions().position(someCity)
        //        .title("Marker in Somewhere"))
        //          .setDraggable(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(someCity));

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem myItem) {
                Toast.makeText(getContext(), "Cluster item click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override public boolean onClusterClick(Cluster<MyItem> cluster) {
                Toast.makeText(getContext(), "Cluster click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 51.5145160;
        double lng = -0.1270060;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng,"Title" + i,"Snippet" + i);
            mClusterManager.addItem(offsetItem);
        }
        mClusterManager.cluster();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
