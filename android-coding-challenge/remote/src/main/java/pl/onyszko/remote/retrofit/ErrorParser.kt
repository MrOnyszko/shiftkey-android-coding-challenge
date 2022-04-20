package pl.onyszko.remote.retrofit

import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ErrorParser @Inject constructor(val retrofit: Retrofit) {

  inline fun <reified T> parseErrorBody(throwable: Throwable): T {
    val httpException =
      throwable as? HttpException ?: return T::class.java.newInstance()
    return parseErrorBody(httpException) ?: T::class.java.newInstance()
  }

  inline fun <reified T> parseErrorBody(httpException: HttpException): T {
    val response =
      httpException.response() ?: return T::class.java.newInstance()
    return parseErrorBody(response) ?: T::class.java.newInstance()
  }

  inline fun <reified T> parseErrorBody(response: Response<*>): T {
    if (response.isSuccessful) return T::class.java.newInstance()
    if (response.errorBody() == null) return T::class.java.newInstance()

    return try {
      val type = object : TypeToken<T>() {}.type
      val converter: Converter<ResponseBody, T> =
        retrofit.responseBodyConverter(
          type,
          arrayOfNulls<Annotation>(0)
        )
      converter.convert(response.errorBody()!!)!!
    } catch (e: Exception) {
      T::class.java.newInstance()
    }
  }
}