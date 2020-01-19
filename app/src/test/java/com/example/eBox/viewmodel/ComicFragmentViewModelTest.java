package com.example.eBox.viewmodel;


import com.example.eBox.data.remote.ComicResults;
import com.example.eBox.data.repo.ComicRepository;
import com.example.eBox.util.ListResp;
import com.example.eBox.util.SchedulerProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

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
