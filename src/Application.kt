package gmidori.com.github

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Routing) {
        routing{
            get("/") {
                call.respondText("Hello World!!", ContentType.Text.Html)
            }
            get("/signup") {
                val postParameters = call.receiveParameters()
                val name = postParameters.get("name")
                val bankId = postParameters.get("bank_id")
                val password = postParameters.get("password")

                if (name == "" || bankId == "" || password == "") {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
            }
        }
    }
}

