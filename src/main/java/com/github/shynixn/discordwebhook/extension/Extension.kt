package com.github.shynixn.discordwebhook.extension

import java.awt.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

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

private val dateFormatIso8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

/**
 * Converts the awt color to a decimal.
 */
val Color.decimal: Int
    get() {
        val hex = String.format("%02x%02x%02x", red, green, blue)
        return hex.toInt(16)
    }

/**
 * Converts the date to the given timestamp.
 */
val Date.timestampIso8601: String
    get() {
        return dateFormatIso8601.format(this)
    }

/**
 * Converts the date to the given timestamp.
 */
val LocalDateTime.timestampIso8601: String
    get() {
        return java.sql.Timestamp.valueOf(this).timestampIso8601
    }
