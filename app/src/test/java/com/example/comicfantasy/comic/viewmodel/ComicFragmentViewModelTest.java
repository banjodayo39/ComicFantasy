package com.example.comicfantasy.comic.viewmodel;


import com.example.comicfantasy.data.remote.ComicResults;
import com.example.comicfantasy.data.repo.ComicRepository;
import com.example.comicfantasy.util.ListResp;
import com.example.comicfantasy.util.SchedulerProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComicFragmentViewModelTest {

    private ListResp<ComicResults> resultListResp;

    @Mock
    private ComicRepository repo;

    @Mock
    private SchedulerProvider provider;

    private ComicFragmentViewModel comicFragmentViewModel;

    @Test
    public void getAllComic_data(){

        /*Observable<ListResp<ComicResults>> resp = Observable.just()
        when(repo.getComicList()).thenReturn(resp);

        viewModel.getBanks();
        verify(repo).getBanks();

        assertEquals(resp, repo.getBanks());*/
    }
}
