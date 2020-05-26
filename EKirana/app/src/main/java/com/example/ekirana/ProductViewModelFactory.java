package com.example.ekirana;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    Application mContext;
    String mparam;

    public ProductViewModelFactory(@NonNull Application application, String mparam) {
        super(application);
        mContext = application;
        this.mparam = mparam;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductViewModel(mContext,mparam);
    }
}
