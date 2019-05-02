package com.example.a.musicplayer.songs.player.data

data class SongBean(
    var code: Int,
    var `data`: List<Data>
)

data class Data(
    var br: Int,
    var canExtend: Boolean,
    var code: Int,
    var encodeType: String,
    var expi: Int,
    var fee: Int,
    var flag: Int,
    var freeTrialInfo: Any,
    var gain: Double,
    var id: Int,
    var level: String,
    var md5: String,
    var payed: Int,
    var size: Int,
    var type: String,
    var uf: Any,
    var url: String
)