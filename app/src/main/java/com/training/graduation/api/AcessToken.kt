

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface ZoomApiService {
    @FormUrlEncoded
    @POST("oauth/token")
    fun getAccessToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): Call<AccessTokenResponse>

    @FormUrlEncoded
    @POST("oauth/token")
    fun refreshAccessToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): Call<AccessTokenResponse>
}

data class AccessTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val scope: String
)
