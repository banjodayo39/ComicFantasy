package com.example.comicfantasy.util;

import io.reactivex.Single;
import retrofit2.Response;


public class MockedDataRespSingle<T> {

    public Single<Response<DataResp<T>>> mockedSingleError() {
        return Single.create(e -> e.onError(new Throwable("Generic Error")));
    }

    public Single<Response<ListResp<T>>> mockedListSinglError() {
        return Single.create(e -> e.onError(new Throwable("Generic Error")));
    }

    public Single<Response<T>> mockedGenericSingleError() {
        return Single.create(e -> e.onError(new Throwable("Generic error")));
    }

    public Single<T> mockedGeneralSingleError() {
        return Single.create(e -> e.onError(new Throwable("Generic error")));
    }

}

