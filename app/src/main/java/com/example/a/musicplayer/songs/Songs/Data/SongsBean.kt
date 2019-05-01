package com.example.a.musicplayer.songs.Songs.Data

data class SongsBean(
    var code: Int,
    var playlist: Playlist,
    var privileges: List<Privilege>
)

data class Privilege(
    var cp: Int,
    var cs: Boolean,
    var dl: Int,
    var fee: Int,
    var fl: Int,
    var flag: Int,
    var id: Int,
    var maxbr: Int,
    var payed: Int,
    var pl: Int,
    var preSell: Boolean,
    var sp: Int,
    var st: Int,
    var subp: Int,
    var toast: Boolean
)

data class Playlist(
    var adType: Int,
    var cloudTrackCount: Int,
    var commentCount: Int,
    var commentThreadId: String,
    var coverImgId: Long,
    var coverImgUrl: String,
    var createTime: Long,
    var creator: Creator,
    var description: Any,
    var highQuality: Boolean,
    var id: Long,
    var name: String,
    var newImported: Boolean,
    var ordered: Boolean,
    var playCount: Int,
    var privacy: Int,
    var shareCount: Int,
    var specialType: Int,
    var status: Int,
    var subscribed: Boolean,
    var subscribedCount: Int,
    var subscribers: List<Any>,
    var tags: List<Any>,
    var trackCount: Int,
    var trackIds: List<TrackId>,
    var trackNumberUpdateTime: Long,
    var trackUpdateTime: Long,
    var tracks: List<Track>,
    var updateTime: Long,
    var userId: Int
)

data class Creator(
    var accountStatus: Int,
    var authStatus: Int,
    var authority: Int,
    var avatarImgId: Long,
    var avatarImgIdStr: String,
    var avatarImgId_str: String,
    var avatarUrl: String,
    var backgroundImgId: Long,
    var backgroundImgIdStr: String,
    var backgroundUrl: String,
    var birthday: String,
    var city: Int,
    var defaultAvatar: Boolean,
    var description: String,
    var detailDescription: String,
    var djStatus: Int,
    var expertTags: Any,
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

data class Track(
    var a: Any,
    var al: Al,
    var alia: List<String>,
    var ar: List<Ar>,
    var cd: String,
    var cf: String,
    var copyright: Int,
    var cp: Int,
    var crbt: Any,
    var djId: Int,
    var dt: Int,
    var fee: Int,
    var ftype: Int,
    var h: H,
    var id: Int,
    var l: L,
    var m: M,
    var mst: Int,
    var mv: Int,
    var name: String,
    var no: Int,
    var pop: Int,
    var pst: Int,
    var publishTime: Long,
    var rt: String,
    var rtUrl: Any,
    var rtUrls: List<Any>,
    var rtype: Int,
    var rurl: Any,
    var s_id: Int,
    var st: Int,
    var t: Int,
    var v: Int
)

data class Al(
    var id: Int,
    var name: String,
    var pic: Long,
    var picUrl: String,
    var pic_str: String,
    var tns: List<Any>
)

data class M(
    var br: Int,
    var fid: Int,
    var size: Int,
    var vd: Double
)

data class Ar(
    var alias: List<Any>,
    var id: Int,
    var name: String,
    var tns: List<Any>
)

data class H(
    var br: Int,
    var fid: Int,
    var size: Int,
    var vd: Double
)

data class L(
    var br: Int,
    var fid: Int,
    var size: Int,
    var vd: Double
)

data class TrackId(
    var id: Int,
    var v: Int
)