package pl.onyszko.remote.hilt

import com.google.gson.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.onyszko.Config
import pl.onyszko.domain.dataSourceAction.GetAvailableShiftsDataSourceAction
import pl.onyszko.remote.BuildConfig
import pl.onyszko.remote.api.ShiftsApi
import pl.onyszko.remote.dataSourceAction.GetAvailableShiftsDataSourceActionImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit

@Module(
  includes = [
    NetworkModule::class,
    ApiModule::class,
  ]
)
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
  @Binds
  abstract fun bindsGetAvailableShiftsDataSourceAction(
    getAvailableShiftsDataSourceAction: GetAvailableShiftsDataSourceActionImpl
  ): GetAvailableShiftsDataSourceAction
}

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {

  @Provides
  fun providesShiftsApi(
    retrofit: Retrofit
  ): ShiftsApi = retrofit.create(ShiftsApi::class.java)
}

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

  private const val CONNECTION_TIMEOUT = 10L
  private const val READ_TIMEOUT = 10L
  private const val WRITE_TIMEOUT = 30L

  @Provides
  fun providesRemoteConfig(): Config {
    return Config(
      apiUrl = BuildConfig.API_URL,
      debug = BuildConfig.DEBUG,
    )
  }

  @Provides
  fun providesGson(): Gson {
    return GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .registerTypeAdapter(
        OffsetDateTime::class.java,
        JsonSerializer<OffsetDateTime> { src, _, _ -> JsonPrimitive(src.toString()) }
      )
      .registerTypeAdapter(
        OffsetDateTime::class.java,
        JsonDeserializer { json, _, _ ->
          OffsetDateTime.parse(json.asJsonPrimitive.asString)
        }
      )
      .registerTypeAdapter(
        LocalDate::class.java,
        JsonSerializer<LocalDate> { src, _, _ -> JsonPrimitive(src.toString()) }
      )
      .registerTypeAdapter(
        LocalDate::class.java,
        JsonDeserializer { json, _, _ ->
          LocalDate.parse(json.asJsonPrimitive.asString)
        }
      )
      .create()
  }

  @Provides
  fun providesOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
      .build()
  }

  @Provides
  fun providesRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient,
    config: Config
  ): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create(gson))
      .baseUrl(config.apiUrl)
      .client(okHttpClient)
      .build()
  }
}