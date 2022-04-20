package com.shiftkey.codingchallenge.hilt

import android.content.Context
import androidx.core.os.ConfigurationCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import pl.onyszko.domain.hilt.DomainModule
import pl.onyszko.foundation.hilt.FoundationModule
import pl.onyszko.remote.hilt.RemoteModule
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
  includes = [
    FoundationModule::class,
    DomainModule::class,
    RemoteModule::class,
  ]
)
class AppModule {

  @Singleton
  @Provides
  @ShortDate
  fun provideShortDateFormatter(
    @ApplicationContext context: Context
  ): DateTimeFormatter {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(context.locale())
  }

  @Singleton
  @Provides
  @FormatDayName
  fun provideFormatDayName(
    @ApplicationContext context: Context
  ): DateTimeFormatter {
    return DateTimeFormatter.ofPattern("EEEE").withLocale(context.locale())
  }

  @Singleton
  @Provides
  @DefaultCoroutineDispatcher
  fun providesDefaultCoroutineDispatcher(): CoroutineDispatcher =
    Dispatchers.Default
}


private fun Context.locale(): Locale? =
  ConfigurationCompat.getLocales(this.resources.configuration).get(0)
