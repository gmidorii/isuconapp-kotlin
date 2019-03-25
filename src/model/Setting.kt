package gmidori.com.github.model

import com.sun.media.sound.InvalidDataException

fun GetSetting(key: String) : String {
    //TODO: DBをスキャンして設定を取ってくる
    return when (key) {
        "bank_endpoint" -> "http://example.com"
        "bank_appid" -> "exapmle"
        else -> throw InvalidDataException()
    }
}
