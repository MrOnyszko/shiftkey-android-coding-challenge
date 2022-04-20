package pl.onyszko.foundation.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.onyszko.foundation.DateProvider
import pl.onyszko.foundation.DateProviderImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class FoundationModule {
  @Binds
  abstract fun bindDateProvider(
    dateProviderImpl: DateProviderImpl
  ): DateProvider
}
