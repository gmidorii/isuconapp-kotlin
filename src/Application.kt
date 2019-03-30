package gmidori.com.github

import gmidori.com.github.model.UserSignUp
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
import io.ktor.routing.post
import io.ktor.routing.routing


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    install(Routing) {
        routing{
            get("/") {
                call.respondText("Hello World!!", ContentType.Text.Html)
            }
            get("/hoge") {
                call.respondText("Hoge!!!")
            }
            post("/signup") {
                val postParameters = call.receiveParameters()
                val name = postParameters.get("name")
                val bankId = postParameters.get("bank_id")
                val password = postParameters.get("password")

                if (name.isNullOrBlank() || bankId.isNullOrBlank() || password.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

                UserSignUp(name, bankId, password)

                call.respondText("OK", status = HttpStatusCode.OK)
            }

        }
    }
}
