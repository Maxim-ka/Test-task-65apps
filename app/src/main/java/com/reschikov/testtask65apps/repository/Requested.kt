package com.reschikov.testtask65apps.repository

import com.reschikov.testtask65apps.net.model.Reply

interface Requested {

    suspend fun receive() : Reply
}