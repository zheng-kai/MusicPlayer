package com.example.a.musicplayer.songs.player.data

data class LyricBean(
    var code: Int,
    var klyric: Klyric,
    var lrc: Lrc,
    var lyricUser: LyricUser,
    var qfy: Boolean,
    var sfy: Boolean,
    var sgc: Boolean,
    var tlyric: Tlyric
)

data class LyricUser(
    var demand: Int,
    var id: Int,
    var nickname: String,
    var status: Int,
    var uptime: Long,
    var userid: Int
)

data class Tlyric(
    var lyric: Any,
    var version: Int
)

data class Klyric(
    var lyric: Any,
    var version: Int
)

data class Lrc(
    var lyric: String,
    var version: Int
)