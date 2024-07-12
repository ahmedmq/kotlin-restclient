package com.example.demo.posts

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class PostClient(private val restClient: RestClient) {

    fun findAllPosts(): List<Post> {
        return restClient.get()
            .uri("/posts")
            .retrieve()
            .body<List<Post>>()!!
    }
}