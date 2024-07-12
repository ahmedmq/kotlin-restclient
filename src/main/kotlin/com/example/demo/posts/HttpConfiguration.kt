package com.example.demo.posts

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class HttpConfiguration {
    @Bean
    fun restClient(builder: RestClient.Builder): RestClient {
        return builder
            .baseUrl("https://jsonplaceholder.typicode.com")
            .build()
    }
}