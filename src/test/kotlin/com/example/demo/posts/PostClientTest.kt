package com.example.demo.posts

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.client.ExpectedCount.once
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestClient

@RestClientTest(PostClient::class)
@Import(HttpConfiguration::class)
class PostClientTest {

    @Autowired
    private lateinit var mockServer: MockRestServiceServer

    @Autowired
    private lateinit var postClient: PostClient

    @BeforeEach
    fun setUp() {
        mockServer.reset()
    }

    @Test
    fun `should call mock server and return fetched posts`() {
        val json = """
            [{
                "id": 1,
                "userId": 1,
                "title": "title",
                "body": "body"
            }]
        """.trimIndent()

        mockServer.expect(once(), requestTo("https://jsonplaceholder.typicode.com/posts"))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON))


        val allPosts = postClient.findAllPosts()


        mockServer.verify()

        assertThat(allPosts.size, equalTo(1))
        assertThat(allPosts[0], equalTo(Post(1,1,"title","body")))
    }
}