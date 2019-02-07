package com.github.shynixn.discordwebhook.impl

import com.github.shynixn.discordwebhook.contract.ConnectionService
import com.github.shynixn.discordwebhook.contract.DiscordWebhookService
import com.github.shynixn.discordwebhook.entity.DiscordPayload
import javax.ws.rs.client.Entity

/**
 * Created by Shynixn 2019.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2019 by Shynixn
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
class DiscordWebhookServiceImpl(private val connectionService: ConnectionService = ConnectionServiceImpl()) : DiscordWebhookService {
    /**
     * Sends the given [payload] to the given [url].
     * The [url] is the full webHook url which can be directly copied from discord.
     * The [payload] can be any custom payload with atleast 1 embedded message.
     */
    override fun sendDiscordPayload(url: String, payload: DiscordPayload) {
        if (payload.embeds.isEmpty()) {
            throw IllegalArgumentException("DiscordPayload requires atleast 1 DiscordEmbed to be displayed on Discord!")
        }

        val client = connectionService.createClient()
        val mappedObject = connectionService.createObjectWriter()
        val json = mappedObject.writeValueAsString(payload)
        val target = client.target(url).request().post(Entity.json(json))

        target.close()
        client.close()
    }
}