# DiscordWebhook-Ktl  [![Build Status](https://maven-badges.herokuapp.com/maven-central/com.github.shynixn/discordwebhook-ktl/badge.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/com.github.shynixn/discordwebhook-ktl) [![GitHub license](http://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://raw.githubusercontent.com/DiscordWebhook-Ktl/master/LICENSE)


| branch        | status        | coverage | version | download |
| ------------- | ------------- | -------- | --------| ---------| 
| master        | [![Build Status](https://img.shields.io/travis/Shynixn/DiscordWebhook-Ktl/master.svg?style=flat-square)](https://travis-ci.org/Shynixn/DiscordWebhook-Ktl) | [![Coverage](https://img.shields.io/codecov/c/github/shynixn/discordwebhook-ktl/master.svg?style=flat-square)](https://codecov.io/gh/Shynixn/DiscordWebhook-Ktl/branch/master)|![GitHub license](https://img.shields.io/nexus/r/https/oss.sonatype.org/com.github.shynixn/discordwebhook-ktl.svg?style=flat-square)  |[Download latest release](https://github.com/Shynixn/DiscordWebhook-Ktl/releases)|
## Description

DiscordWebhook-Ktl is a wrapper around the discord webhook bot REST api.

## Features

* A robustful library for REST client calls to discord webhook bots.

## How to use it

* Include it from the central maven repository.

* Use a Java sample:
```java
String webHookUrl = "yourDiscordWebHookUrlWhichYouCanDirectlyCopyPasteIntoThis";

DiscordWebhookService service = new DiscordWebhookServiceImpl();

DiscordPayload payload = new DiscordPayload();
DiscordEmbed embeddedMessage = new DiscordEmbed("Hello World.", ExtensionKt.getDecimal(Color.RED)
        ,new DiscordAuthor("Shynixn", null, null), "This should represent a simple sample."
        ,ExtensionKt.getTimestampIso8601(new Date()), new ArrayList<>());
payload.getEmbeds().add(embeddedMessage);

service.sendDiscordPayload(webHookUrl, payload);
```

* Use a Kotlin sample:
```kotlin
val webHookUrl = "yourDiscordWebHookUrlWhichYouCanDirectlyCopyPasteIntoThis";

val service = DiscordWebhookServiceImpl()

val payload = DiscordPayload()
val embeddedMessage = DiscordEmbed(
    "Hello World.",
    Color.RED.decimal,
    DiscordAuthor("Shynixn"),
    "This should represent a simple sample.",
    Date().timestampIso8601
)
payload.embeds.add(embeddedMessage)

service.sendDiscordPayload(webHookUrl, payload)
```

## Licence

Copyright 2019 Shynixn

The source code is licensed under the MIT license.
