package com.flatstack.android.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.flatstack.android.MainActivity;
import com.flatstack.android.fragments.MainFragment;
import com.flatstack.android.qualifiers.CacheDir;
import com.flatstack.android.utils.DatabaseHelper;
import com.flatstack.android.utils.Persistence;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import de.devland.esperandro.serialization.JacksonSerializer;

@Module(injects = {
    MainActivity.class,
    MainFragment.class
})
public class AppDaggerModule {
  final @NotNull Application application;

  public AppDaggerModule(@NotNull Application application) {
    this.application = application;
  }

  @Provides Context provideContext() { return application; }

  @Provides @CacheDir File provideCacheDir(Context context) {
    return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        ? context.getExternalCacheDir()
        : context.getCacheDir();
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(@CacheDir File cacheDir) {
    OkHttpClient okHttpClient = new OkHttpClient();
    try {
      okHttpClient.setCache(new Cache(cacheDir, 20 * 1024 * 1024));
    } catch (IOException ignored) {
    }
    return okHttpClient;
  }

  @Provides @Singleton Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
    return new Picasso.Builder(context)
        .downloader(new OkHttpDownloader(okHttpClient))
        .build();
  }

  @Provides @Singleton ObjectMapper provideJackson() {
    final ObjectMapper om = new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

    // ISO 8601
    om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US));

    final SimpleModule sm = new SimpleModule();
    sm.setDeserializerModifier(new BeanDeserializerModifier() {
      @Override public JsonDeserializer<Enum> modifyEnumDeserializer(
          DeserializationConfig config,
          JavaType type,
          BeanDescription beanDesc,
          JsonDeserializer<?> deserializer) {
        return new JsonDeserializer<Enum>() {
          @Override public Enum deserialize(JsonParser jp,
                                            DeserializationContext ctxt) throws IOException {
            @SuppressWarnings("unchecked")
            final Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
            return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase(Locale.US));
          }
        };
      }
    });
    sm.addSerializer(Enum.class, new StdSerializer<Enum>(Enum.class) {
      @Override public void serialize(Enum value,
                                      JsonGenerator jgen,
                                      SerializerProvider provider) throws IOException {
        jgen.writeString(value.name().toLowerCase(Locale.US));
      }
    });
    om.registerModule(sm);
    return om;
  }

  @Provides @Singleton Persistence providePreferences(Context context,
                                                      ObjectMapper objectMapper) {
    Esperandro.setSerializer(new JacksonSerializer(objectMapper));
    return Esperandro.getPreferences(Persistence.class, context);
  }

  @Provides @Singleton DatabaseHelper provideDatabaseHelper(Context context) {
    return new DatabaseHelper(context);
  }
}
