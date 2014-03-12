package com.flatsoft.base.dagger;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import dagger.Module;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@Module(injects = {OkHttpClient.class, Picasso.class})
public class ModulesTestModule {}
