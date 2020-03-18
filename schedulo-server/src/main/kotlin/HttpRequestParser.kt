import io.javalin.http.BadRequestResponse
import io.javalin.http.Context

data class SimpleResponse(val message: String)

object HttpRequestParser {
    fun getHeader(ctx: Context, key: String) : String {
        val value = ctx.header(key)
        return value ?: throw BadRequestResponse("Missing $key")
    }

    fun getDetails(ctx: Context) : Pair<String, String> {
        val username = getHeader(ctx, "username")
        val password = getHeader(ctx, "password")
        return Pair(username, password)
    }

    fun getUserFromDetails(ctx: Context) : User {
        val (username, password) = getDetails(ctx)
        return UserManager.login(username, password)
    }

    fun login(ctx: Context) {
        getUserFromDetails(ctx)
        ctx.json(SimpleResponse("logged in"))
    }

    fun register(ctx: Context) {
        val (username, password) = getDetails(ctx)

        UserManager.register(username, password)
        ctx.json(SimpleResponse("user registered"))
    }

    fun createEvent(ctx: Context) {
        val user = getUserFromDetails(ctx)
        val event = ctx.bodyValidator<Event>()
            .check({ it.start < it.end}, "Start time must be before end time")
            // .check({ it.start.isAfter(Instant.now().minusSeconds(5)) }, "Start time must be in the future.")
            .get()

        println("here")
        Database.createEventForUser(event, user)
    }

    fun getEvents(ctx: Context) {
        val user = getUserFromDetails(ctx)
        ctx.json(Database.getEventsForUser(user))
    }
}