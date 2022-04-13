package com.fangzsx.retrofit_room.model.youtube

data class Item(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)