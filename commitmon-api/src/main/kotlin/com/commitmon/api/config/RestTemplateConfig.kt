package com.commitmon.api.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset
import java.time.Duration

@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder
            .requestFactory { BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()) }
            .setConnectTimeout(Duration.ofSeconds(10000L))
            .setReadTimeout(Duration.ofSeconds(10000L))
            .additionalMessageConverters(StringHttpMessageConverter(Charset.forName("UTF-8")))
            .build()
    }
}

@Service
class ApiAdvice<T>(
    private val restTemplate: RestTemplate
) {
    fun get(url: String, headers: HttpHeaders, clazz: Class<T>): ResponseEntity<T> {
        return callApiEndPoint(url, HttpMethod.GET, headers, null, clazz)
    }

    private fun callApiEndPoint(url: String, httpMethod: HttpMethod, httpHeaders: HttpHeaders, body: Any?, clazz: Class<T>): ResponseEntity<T> {
        return restTemplate.exchange(url, httpMethod, HttpEntity(body, httpHeaders), clazz)
    }
}