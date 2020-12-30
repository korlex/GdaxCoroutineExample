package com.example.gdaxcoroutineexample.api

import java.security.KeyManagementException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NativeSSLContext {
    private class NaiveTrustManager : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate>? = null
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
    }

    companion object {

        fun getSSLContext(protocol: String): SSLContext {
            val sslContext = SSLContext.getInstance(protocol)
            try {
                sslContext.init(null, arrayOf<TrustManager>(NaiveTrustManager()), null)
                return sslContext
            } catch (e: KeyManagementException) {
                throw Exception("Failed to initialize an SSLContext.", e)
            }
        }
    }
}