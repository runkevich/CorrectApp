package com.vironit.correctapp.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.correctapp.App;
import com.vironit.correctapp.mvp.model.repository.db.CorrectDatabase;
import com.vironit.correctapp.mvp.presentation.presenter.base.BaseAppPresenter;
import com.vironit.correctapp.mvp.presentation.view.interfaces.IHomeView;
import com.vironit.correctapp.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class HomePresenter extends BaseAppPresenter<IHomeView> {

    @Inject
    CorrectDatabase mCorrectDatabase;

    public HomePresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void attachView(IHomeView view) {
        super.attachView(view);
        show();
    }

    public void showProfile() {
        getViewState().showProfile();
    }

    public void showNews() {
        getViewState().showNews();
    }

    public void showChat() {
        getViewState().showChat();
    }

    private void show() {
        long i = 1;
        mCorrectDatabase.getCompanyDAO()
                .getAllCompanies()
                .subscribeOn(mIOScheduler)
                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK1"),
                        this);

        mCorrectDatabase.getDepartmentDAO()
                .getAllDepatments()
                .subscribeOn(mIOScheduler)
                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK2"),
                        this);

        mCorrectDatabase.getEmployeeDAO()
                .getAllEmployies()
                .subscribeOn(mIOScheduler)
                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK3"),
                        this);

//        mCorrectDatabase.getEmployeeDAO()
//                .getById(1)
//                .subscribeOn(mIOScheduler)
//                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK3"),
//                        this);

        mCorrectDatabase.getCompanyDepartmentDAO()
                .loadCompanyAndDepartmentDB()
                .subscribeOn(mIOScheduler)
                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK4"),
                        this);

        mCorrectDatabase.getCompanyAndDepartmentAndEmployeeDAO()
                .loadCompanyAndDepartmentAndEmployeeDAO()
                .subscribeOn(mIOScheduler)
                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK5"),
                        this);

//        mCorrectDatabase.getCompanyAndDepartmentAndEmployeeDAO()
//                .getCompanyWithDepartmentsAndEmployeesFromReverse(i)
//                .subscribeOn(mIOScheduler)
//                .subscribe(companyDB -> AppLog.logPresenter(this, "OOOOOKKKKK5"),
//                        this);

    }
}
