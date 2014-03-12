package com.companyname.appname.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
* Created by adelnizamutdinov on 12/03/2014
*/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheDir {}
