package gmidori.com.github.model

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Setting: Table() {
    val name = binary("name", 191).primaryKey()
    val value = varchar("val", 255)
}

fun GetSetting(db: Database, key: String) : String {
    var value: String = ""
    transaction(db) {
        val row = Setting.select { Setting.name eq key.toByteArray() }.first()
        value = row[Setting.value]
    }

    return value
}

fun NewIsubank(db: Database): IsuBank {
    val ep = GetSetting(db, "bank_endpoint")
    val id = GetSetting(db, "bank_appid")

    return IsuBank(ep, id)
}