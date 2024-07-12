package com.example.demo.posts

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class PostControllerTest {

    private lateinit var mockPostService : PostService
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockPostService = mockk()

        mockMvc = MockMvcBuilders.standaloneSetup(PostController(mockPostService))
            .build()
    }

    @Test
    fun `should return 200 when fetching posts`() {
        every { mockPostService.posts() } returns listOf()


        mockMvc.perform(get("/api/v1/posts"))
            .andExpect(status().isOk)
    }

    @Test
    fun `should return posts`() {
        val id = 12
        every { mockPostService.posts() } returns listOf(Post(id,id,"title", "body"))

        val expectedJson : String = """
            [{
                "id": $id,
                "userId": $id,
                "title": "title",
                "body": "body"
            }]
        """.trim()


        mockMvc.perform(get("/api/v1/posts"))
            .andExpect(content().json(expectedJson))
    }

    @Test
    fun `should invoke service`() {
        every { mockPostService.posts() } returns listOf()


        mockMvc.perform(get("/api/v1/posts"))


        verify(exactly = 1) { mockPostService.posts() }
    }
}