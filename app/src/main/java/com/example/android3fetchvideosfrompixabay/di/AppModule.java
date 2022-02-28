package com.example.android3fetchvideosfrompixabay.di;

import com.example.android3fetchvideosfrompixabay.network.PixabayApi;
import com.example.android3fetchvideosfrompixabay.repository.PixabayRepository;
import com.example.android3fetchvideosfrompixabay.viewmodel.PixabayViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    public PixabayRepository provideRepository(PixabayApi api) {
        return new PixabayRepository(api);
    }

    @Singleton
    @Provides
    public PixabayViewModel provideViewModel(PixabayRepository repository) {
        return new PixabayViewModel(repository);
    }

    @Singleton
    @Provides
    public PixabayApi provideApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(PixabayApi.class);


    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }


}
