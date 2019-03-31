package gmidori.com.github.model

interface IsuBankResponse {
    fun setStatus(code: Int)
}

class IsubankBasicResponse(var code: Int): IsuBankResponse {
    override fun setStatus(code: Int) {
        this.code = code
    }

    fun success(): Boolean {
       return code == 200
    }
}

class IsubankReserveResponse(val basic: IsubankBasicResponse, var reserveId: Int) {
}

class IsuBank(val endPoint: String, val appId: String) {
    fun Check(bankId: String, price: Int = 0) : Boolean {
        //TODO: url requestしてcheckする
        return true
    }

    fun request(path: String, body: String, r: IsuBankResponse) {
    }
}

