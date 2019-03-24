package gmidori.com.github.model

import com.sun.media.sound.InvalidDataException

fun UserSignUp(name: String, bankId: String, password: String) {
    //TODO: すでに登録しているかどうかを確認する
    val bankEndPoint = GetSetting("bank_endpoint")
    val bookAppId = GetSetting("bank_appid")
    val isuBank = IsuBank(bankEndPoint, bookAppId)

    if (!isuBank.Check(bankId)) {
        //TODO: bankId が不正のため処理を何もせずに返却
        return
    }
}

fun GetSetting(key: String) : String {
    //TODO: DBをスキャンして設定を取ってくる
    return when (key) {
        "bank_endpoint" -> "http://example.com"
        "bank_appid" -> "exapmle"
        else -> throw InvalidDataException()
    }
}