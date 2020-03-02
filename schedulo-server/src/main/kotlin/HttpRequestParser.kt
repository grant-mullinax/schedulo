import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import java.time.Instant

data class SimpleResponse(val message: String)

data class Event(val name: String, val description: String, val location: String, val start: Long, val end: Long)

object HttpRequestParser {

    fun login(ctx: Context) {
        val username = (ctx.header("username") ?: throw BadRequestResponse("Missing username"))
        val password = (ctx.header("password") ?: throw BadRequestResponse("Missing password"))

        UserManager.login(username, password)
        ctx.json(SimpleResponse("logged in"))
    }

    fun register(ctx: Context) {
        val username = (ctx.formParam("username") ?: throw BadRequestResponse("Missing username"))
        val password = (ctx.formParam("password") ?: throw BadRequestResponse("Missing password"))

        UserManager.register(username, password)
        ctx.json(SimpleResponse("user registered"))
    }

    fun createEvent(ctx: Context) {
        val event = ctx.bodyValidator<Event>()
            .check({ it.start < it.end}, "Start time must be before end time")
            // .check({ it.start.isAfter(Instant.now().minusSeconds(5)) }, "Start time must be in the future.")
            .get()

        Database.createEvent(event)
    }

    fun getEvents(ctx: Context) {
        ctx.json(Database.getEvents())
    }
}