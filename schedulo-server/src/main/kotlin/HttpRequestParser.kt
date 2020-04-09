import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import java.util.*

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

    private fun getUserFromDetails(ctx: Context) : User {
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

        ctx.json(Database.createEventForUser(event, user))
    }

    fun deleteEvent(ctx: Context) {
        val user = getUserFromDetails(ctx)
        val id = ctx.pathParam("id")

        Database.deleteEvent(UUID.fromString(id))
    }

    fun editEvent(ctx: Context) {
        val user = getUserFromDetails(ctx)
        val id = UUID.fromString(ctx.pathParam("id"))

        val event = ctx.bodyValidator<Event>()
            .check({ it.start < it.end}, "Start time must be before end time")
            // .check({ it.start.isAfter(Instant.now().minusSeconds(5)) }, "Start time must be in the future.")
            .get()


        Database.updateEvent(IdEvent(id, event))
        ctx.json(Database.getEvent(id))
    }

    fun shareEvent(ctx: Context) {
        val user = getUserFromDetails(ctx)
        val eventId = UUID.fromString(ctx.pathParam("eventid"))
        val toUser = Database.getUser(ctx.pathParam("username")) ?: throw BadRequestResponse("User does not exist")

        Database.shareEvent(eventId, toUser)
        ctx.json(SimpleResponse("event shared"))
    }

    fun getEvents(ctx: Context) {
        val user = getUserFromDetails(ctx)
        ctx.json(Database.getEventsForUser(user))
    }
}