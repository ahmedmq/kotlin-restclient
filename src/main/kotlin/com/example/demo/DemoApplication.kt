package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestClient

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@Bean
fun restClient(builder: RestClient.Builder): RestClient {
	return builder
		.baseUrl("https://jsonplaceholder.typicode.com")
		.build()
}