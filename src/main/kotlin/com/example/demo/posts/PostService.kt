package com.example.demo.posts

import org.springframework.stereotype.Service

@Service
class PostService(private val postClient: PostClient) {

    fun posts(): List<Post> {
        return postClient.findAllPosts()
    }
}
