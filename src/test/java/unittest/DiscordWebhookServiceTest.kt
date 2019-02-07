package unittest

import com.fasterxml.jackson.databind.ObjectWriter
import com.github.shynixn.discordwebhook.contract.ConnectionService
import com.github.shynixn.discordwebhook.contract.DiscordWebhookService
import com.github.shynixn.discordwebhook.entity.DiscordAuthor
import com.github.shynixn.discordwebhook.entity.DiscordEmbed
import com.github.shynixn.discordwebhook.entity.DiscordField
import com.github.shynixn.discordwebhook.entity.DiscordPayload
import com.github.shynixn.discordwebhook.extension.decimal
import com.github.shynixn.discordwebhook.extension.timestampIso8601
import com.github.shynixn.discordwebhook.impl.DiscordWebhookServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.awt.Color
import java.time.LocalDateTime
import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.client.Invocation
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

/**
 * Created by Shynixn 2018.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2018 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
class DiscordWebhookServiceTest {
    /**
     * Given
     *      a minimal discord message
     * When
     *      sendDiscordPayload is called
     * Then
     *    post should be called.
     */
    @Test
    fun sendDiscordPayload_MinimumMessagePayload_ShouldPostMessage() {
        // Arrange
        val sampleUrl = "ThisIsNotARealUrl"
        val payload = DiscordPayload()
        payload.embeds.add(DiscordEmbed("BasicMessage"))
        val connectionService = MockedConnectionService()
        val classUnderTest = createWithDependencies(connectionService)

        // Act
        classUnderTest.sendDiscordPayload(sampleUrl, payload)

        // Assert
        Assertions.assertTrue(connectionService.postCalled)
    }

    /**
     * Given
     *      a big discord message
     * When
     *      sendDiscordPayload is called
     * Then
     *    post should be called.
     */
    @Test
    fun sendDiscordPayload_BigMessagePayload_ShouldPostMessage() {
        // Arrange
        val sampleUrl = "ThisIsNotARealUrl"
        val payload = DiscordPayload("Pikachu", "PikachuImage.png")
        payload.embeds.add(DiscordEmbed("BasicMessage", Color.RED.decimal, DiscordAuthor("PokeThing", "SomeUrlIGuess.com", "SomeIconUrl.com")
                , "This is a description.", LocalDateTime.now().timestampIso8601))
        payload.embeds[0].fields.add(DiscordField("field1", "This is a field"))

        val connectionService = MockedConnectionService()
        val classUnderTest = createWithDependencies(connectionService)

        // Act
        classUnderTest.sendDiscordPayload(sampleUrl, payload)

        // Assert
        Assertions.assertTrue(connectionService.postCalled)
    }

    /**
     * Given
     *      an empty discord message
     * When
     *      sendDiscordPayload is called
     * Then
     *    exception should be thrown.
     */
    @Test
    fun sendDiscordPayload_EmptyMessage_ShouldThrowException() {
        // Arrange
        val sampleUrl = "ThisIsNotARealUrl"
        val payload = DiscordPayload("Pikachu", "PikachuImage.png")

        val connectionService = MockedConnectionService()
        val classUnderTest = createWithDependencies(connectionService)

        // Act
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            classUnderTest.sendDiscordPayload(sampleUrl, payload)
        }
    }

    companion object {
        fun createWithDependencies(connectionService: ConnectionService = MockedConnectionService()): DiscordWebhookService {
            return DiscordWebhookServiceImpl(connectionService)
        }
    }

    class MockedConnectionService(var postCalled: Boolean = false) : ConnectionService {
        /**
         * Creates a new connection client.
         */
        override fun createClient(): Client {
            val client = Mockito.mock(Client::class.java)
            val webTarget = Mockito.mock(WebTarget::class.java)
            val invocationBuilder = Mockito.mock(Invocation.Builder::class.java)

            Mockito.`when`(client.target(Mockito.anyString())).thenReturn(webTarget)
            Mockito.`when`(webTarget.request()).thenReturn(invocationBuilder)
            Mockito.`when`(invocationBuilder.post(Mockito.any(Entity::class.java))).thenAnswer {
                postCalled = true
                Mockito.mock(Response::class.java)
            }

            return client
        }

        /**
         * Creates a new object writer.
         */
        override fun createObjectWriter(): ObjectWriter {
            return Mockito.mock(ObjectWriter::class.java)
        }
    }
}