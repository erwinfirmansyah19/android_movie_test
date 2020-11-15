package id.hellobill.hellobilltrackingsales.rest.api

import com.google.gson.GsonBuilder
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object  ApiClient {

    val BASE_URL = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null

    var gson = GsonBuilder()
        .setLenient()
        .create()

    // Install the all-trusting trust manager

    //FOR SUPPORT HIT HTTPS
    var spec: ConnectionSpec? = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2)
        .cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
        .build()

    //FOR HIT API
    val client: Retrofit
        get() {
            val client = OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec) as List<ConnectionSpec>)
                .build()
            client.connectTimeoutMillis
            client.readTimeoutMillis
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()

            }
            return retrofit!!
        }

}