import io.javalin.http.Context

class HttpErrorResponseException(val code: Int, message: String) : Throwable()

object HttpRequestParser {

    fun login(ctx: Context) {
        try {
            val username = ctx.formParam("username") ?: throw HttpErrorResponseException(400, "Missing username")
            val password = ctx.formParam("password") ?: throw HttpErrorResponseException(400, "Missing password")

            UserManager.login(username, password)
        } catch (e: HttpErrorResponseException) {
            ctx.status(e.code)
        }
    }

    fun register(ctx: Context) {
        try {
            val username = ctx.formParam("username") ?: throw HttpErrorResponseException(400, "Missing username")
            val password = ctx.formParam("password") ?: throw HttpErrorResponseException(400, "Missing password")

            UserManager.register(username, password)
        } catch (e: HttpErrorResponseException) {
            ctx.status(e.code)
        }
    }
}