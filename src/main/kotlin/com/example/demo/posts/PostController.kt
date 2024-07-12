package com.example.demo.posts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PostController(private val postService: PostService) {

    @GetMapping("/posts")
    fun posts(): List<Post> {
        return postService.posts()
    }
}

data class Post(val id: Int, val userId: Int, val title: String, val body: String)