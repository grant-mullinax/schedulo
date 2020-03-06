import io.javalin.Javalin
import io.javalin.core.validation.JavalinValidation
import io.javalin.http.InternalServerErrorResponse
import org.mindrot.jbcrypt.BCrypt
import java.sql.SQLException
import java.time.Instant

fun main(args: Array<String>) {
    JavalinValidation.register(Instant::class.java) {
        Instant.ofEpochMilli(it.toLong())
    }

    val app = Javalin.create().start(7000)
    app.post("/login", HttpRequestParser::login)
    app.post("/register", HttpRequestParser::register)
    app.get("/events", HttpRequestParser::getEvents)
    app.post("/events", HttpRequestParser::createEvent)

    app.exception(SQLException::class.java) { _, ctx ->
        ctx.status(500)
        ctx.html("Database error")
    }
}

