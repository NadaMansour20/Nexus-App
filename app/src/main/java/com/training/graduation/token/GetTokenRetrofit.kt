package com.training.graduation.token
import com.training.graduation.following.WebhookApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuild {

    companion object {

        private var retrofit: Retrofit? = null

        private fun built_retrofit(): Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Constent.jassTokenApi)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }

            return retrofit!!

        }

        fun get_api():TokenApi {
            return built_retrofit().create( TokenApi ::class.java)

        }

    }
}
