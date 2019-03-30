package gmidori.com.github.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object User: Table(name = "user") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val bankId: Column<ByteArray> = binary("bank_id", 191).uniqueIndex()
    val userName: Column<String> = varchar("name", 128)
    val password: Column<ByteArray> = binary("password", 191)
    val createdAt: Column<DateTime> = datetime("created_at")
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

    Database.connect("jdbc:mysql://localhost:13306/isucoin?loc=Local&charset=utf8mb4",
        driver = "com.mysql.cj.jdbc.Driver", user = "root", password = "root")
    transaction {
        SchemaUtils.create(User)

        User.insertIgnore {
            it[User.bankId] = bankId.toByteArray()
            it[User.userName] = name
            it[User.password] = hashedPassword.toByteArray()
            it[User.createdAt] = DateTime.now()
        }

    }
}

