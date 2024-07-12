package com.example.demo.posts

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostServiceTest {

    private lateinit var mockPostClient: PostClient
    private lateinit var postService: PostService

    @BeforeEach
    fun setUp() {
        mockPostClient = mockk()
        postService = PostService(mockPostClient)
    }

    @Test
    fun `should invoke the post client`() {
        every { mockPostClient.findAllPosts() } returns listOf()


        postService.posts()


        verify(exactly = 1) { mockPostClient.findAllPosts() }
    }

    @Test
    fun `should return posts`() {
        every { mockPostClient.findAllPosts() } returns listOf(Post(1, 1, "title", "body"))


        val actual = postService.posts()


        assertThat(actual.size, equalTo(1))
        assertThat(actual[0].id, equalTo(1))
        assertThat(actual[0].userId, equalTo(1))
        assertThat(actual[0].title, equalTo("title"))
        assertThat(actual[0].body, equalTo("body"))
    }
}