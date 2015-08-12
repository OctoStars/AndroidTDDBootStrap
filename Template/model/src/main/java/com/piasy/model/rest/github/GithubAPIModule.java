package com.piasy.model.rest.github;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Piasy{github.com/Piasy} on 15/7/23.
 */
@Module(includes = com.piasy.model.rest.RestModule.class)
public class GithubAPIModule {

    @Provides
    GithubAPI provideGithubAPI(RestAdapter restAdapter) {
        return restAdapter.create(GithubAPI.class);
    }

}