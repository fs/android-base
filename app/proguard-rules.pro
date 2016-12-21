#your models
#if you store all model under one package then use:
# -keep class your.package.name.models.** { *; }
#if you store models under different models packages then use:
#-keep class **.models.** { *; }

#retrolambda
-dontwarn java.lang.invoke.*

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#picasso
-dontwarn com.squareup.okhttp3.**

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**

#rxjava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontwarn sun.misc.Unsafe

#start gson
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#end of gson

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#debug
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable



