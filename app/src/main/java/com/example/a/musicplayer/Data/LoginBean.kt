package com.example.a.musicplayer.Data

data class LoginBean(
    var msg : String,
    var account: Account,
    var bindings: List<Binding>,
    var code: Int,
    var loginType: Int,
    var profile: Profile
)

data class Profile(
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
    var birthday: Int,
    var city: Int,
    var defaultAvatar: Boolean,
    var description: String,
    var detailDescription: String,
    var djStatus: Int,
    var eventCount: Int,
    var expertTags: Any,
    var experts: Experts,
    var followed: Boolean,
    var followeds: Int,
    var follows: Int,
    var gender: Int,
    var mutual: Boolean,
    var nickname: String,
    var playlistBeSubscribedCount: Int,
    var playlistCount: Int,
    var province: Int,
    var remarkName: Any,
    var signature: String,
    var userId: Int,
    var userType: Int,
    var vipType: Int
)

class Experts(
)

data class Binding(
    var expired: Boolean,
    var expiresIn: Int,
    var id: Long,
    var refreshTime: Int,
    var tokenJsonStr: String,
    var type: Int,
    var url: String,
    var userId: Int
)

data class Account(
    var anonimousUser: Boolean,
    var ban: Int,
    var baoyueVersion: Int,
    var createTime: Long,
    var donateVersion: Int,
    var id: Int,
    var salt: String,
    var status: Int,
    var tokenVersion: Int,
    var type: Int,
    var userName: String,
    var vipType: Int,
    var viptypeVersion: Int,
    var whitelistAuthority: Int
)