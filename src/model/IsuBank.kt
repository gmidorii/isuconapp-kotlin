package gmidori.com.github.model

class IsuBank(val endPoint: String, val appId: String) {
    fun Check(bankId: String, price: Int = 0) : Boolean {
        //TODO: url requestしてcheckする
        return true
    }
}