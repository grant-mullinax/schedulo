import io.javalin.http.BadRequestResponse
import io.javalin.http.Context

data class SimpleResponse(val message: String)

object HttpRequestParser {

    fun getUserFromDetails(ctx: Context) : User {
        val username = (ctx.header("username") ?: throw BadRequestResponse("Missing username"))
        val password = (ctx.header("password") ?: throw BadRequestResponse("Missing password"))

        return UserManager.login(username, password)
    }

    fun login(ctx: Context) {
        getUserFromDetails(ctx)
        ctx.json(SimpleResponse("logged in"))
    }

    fun register(ctx: Context) {
        val username = (ctx.formParam("username") ?: throw BadRequestResponse("Missing username"))
        val password = (ctx.formParam("password") ?: throw BadRequestResponse("Missing password"))

        UserManager.register(username, password)
        ctx.json(SimpleResponse("user registered"))
    }

    fun createEvent(ctx: Context) {
        val user = getUserFromDetails(ctx)

        val event = ctx.bodyValidator<Event>()
            .check({ it.start < it.end}, "Start time must be before end time")
            // .check({ it.start.isAfter(Instant.now().minusSeconds(5)) }, "Start time must be in the future.")
            .get()

        Database.createEventForUser(event, user)
    }

    fun getEvents(ctx: Context) {
        val user = getUserFromDetails(ctx)
        ctx.json(Database.getEventsForUser(user))
    }
}