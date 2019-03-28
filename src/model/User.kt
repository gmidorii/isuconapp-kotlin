package gmidori.com.github.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class User {

}

fun UserSignUp(name: String, bankId: String, password: String) {
    //TODO: すでに登録しているかどうかを確認する
    val bankEndPoint = GetSetting("bank_endpoint")
    val bookAppId = GetSetting("bank_appid")
    val isuBank = IsuBank(bankEndPoint, bookAppId)

    if (!isuBank.Check(bankId)) {
        //TODO: bankId が不正のため処理を何もせずに返却
        return
    }

    val bcrypt = BCryptPasswordEncoder()
    val hashedPassword = bcrypt.encode(password)
    println(hashedPassword)
}
