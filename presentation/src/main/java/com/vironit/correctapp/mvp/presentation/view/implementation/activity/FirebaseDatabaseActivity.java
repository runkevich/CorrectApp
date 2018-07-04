package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vironit.correctapp.R;
import com.vironit.correctapp.constans.FirebaseConstants;
import com.vironit.correctapp.mvp.model.repository.dto.users.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;

    @BindView(R.id.tv_firebase_databse_text)
    TextView mTextView;

    @OnClick(R.id.btn_firebase_database_1)
    void firebase1() {
        RxFirebaseDatabase.observeValueEvent(mDatabaseReference.child(FirebaseConstants.USERS_JSON),
                DataSnapshot::getChildren)
                .map(list -> {
                    List<User> lists = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : list) {
                        lists.add(dataSnapshot.getValue(User.class));
                    }
                    return lists;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    String s = "\n-----" + object.toString();
                    mTextView.append(s);
                }, throwable -> {
                    mTextView.append(throwable.getMessage());
                    Log.i("LOG_TAG", "ERROOOOOOOOOOR");
                });
    }

    @OnClick(R.id.btn_firebase_database_2)
    void firebase2() {
        Single.just(mDatabaseReference.child(FirebaseConstants.USERS_JSON))
                .map(ref -> ref.push().getKey())
                .flatMap(key -> RxFirebaseDatabase.setValue(mDatabaseReference.child(FirebaseConstants.USERS_JSON).child(key),
                        new User("TROLO", "", null, true))
                        .toSingle(() -> true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ref -> Log.i("LOG_TAG", "YEP"),
                        e -> Log.i("LOG_TAG", "NOPE"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        ButterKnife.bind(this);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
