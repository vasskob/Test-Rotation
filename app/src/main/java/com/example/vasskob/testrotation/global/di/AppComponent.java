package com.example.vasskob.testrotation.global.di;

import com.example.vasskob.testrotation.global.MyApplication;
import com.example.vasskob.testrotation.data.di.DataComponent;
import com.example.vasskob.testrotation.data.di.DataScope;
import com.example.vasskob.testrotation.presentation.view.common.di.ActivityBuilder;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@DataScope
@Component(
        dependencies = DataComponent.class,
        modules = {
                AppModule.class,
                AndroidInjectionModule.class,
                ActivityBuilder.class
        })
public interface AppComponent {

    void inject(MyApplication application);
}
