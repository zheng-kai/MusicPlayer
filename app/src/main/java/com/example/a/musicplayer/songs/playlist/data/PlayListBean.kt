package com.example.a.musicplayer.songs.playlist.data

data class PlayListBean(
    var code: Int,
    var more: Boolean,
    var playlist: List<Playlist>
)

data class Playlist(
    var adType: Int,
    var anonimous: Boolean,
    var artists: Any,
    var cloudTrackCount: Int,
    var commentThreadId: String,
    var coverImgId: Long,
    var coverImgId_str: String,
    var coverImgUrl: String,
    var createTime: Long,
    var creator: Creator,
    var description: String,
    var highQuality: Boolean,
    var id: String,
    var name: String,
    var newImported: Boolean,
    var ordered: Boolean,
    var playCount: Int,
    var privacy: Int,
    var specialType: Int,
    var status: Int,
    var subscribed: Boolean,
    var subscribedCount: Int,
    var subscribers: List<Any>,
    var tags: List<String>,
    var totalDuration: Int,
    var trackCount: Int,
    var trackNumberUpdateTime: Long,
    var trackUpdateTime: Long,
    var tracks: Any,
    var updateTime: Long,
    var userId: Int
)

data class Creator(
    var accountStatus: Int,
    var authStatus: Int,
    var authority: Int,
    var avatarImgId: Long,
    var avatarImgIdStr: String,
    var avatarUrl: String,
    var backgroundImgId: Long,
    var backgroundImgIdStr: String,
    var backgroundUrl: String,
    var birthday: Long,
    var city: Int,
    var defaultAvatar: Boolean,
    var description: String,
    var detailDescription: String,
    var djStatus: Int,
    var expertTags: List<String>,
    var experts: Any,
    var followed: Boolean,
    var gender: Int,
    var mutual: Boolean,
    var nickname: String,
    var province: Int,
    var remarkName: Any,
    var signature: String,
    var userId: Int,
    var userType: Int,
    var vipType: Int
)