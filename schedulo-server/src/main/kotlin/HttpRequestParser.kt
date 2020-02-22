import io.javalin.http.Context

class HttpErrorResponseException(val code: Int, message: String) : Throwable()

object HttpRequestParser {

    fun login(ctx: Context) {
        try {
            val a = ctx.formParam("username")
            val b = ctx.formParam("password")
            val username = (a ?: throw HttpErrorResponseException(400, "Missing username"))
            val password = (b ?: throw HttpErrorResponseException(400, "Missing password"))

            UserManager.login(username, password)

            ctx.result("logged in")
        } catch (e: HttpErrorResponseException) {
            ctx.status(e.code)
            ctx.result(e.message?:"")
        }
    }

    fun register(ctx: Context) {
        try {
            val a = ctx.formParam("username")
            val b = ctx.formParam("password")
            val username = (a ?: throw HttpErrorResponseException(400, "Missing username"))
            val password = (b ?: throw HttpErrorResponseException(400, "Missing password"))

            UserManager.register(username, password)

            ctx.result("registered")
        } catch (e: HttpErrorResponseException) {
            ctx.status(e.code)
            ctx.result(e.message?:"")
        }
    }
}