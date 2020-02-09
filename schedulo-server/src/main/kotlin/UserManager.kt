import io.javalin.http.Context
import io.javalin.http.Handler
import org.mindrot.jbcrypt.BCrypt
import kotlin.random.Random

data class User(val username: String, val password: String)

object UserManager {

    fun login(ctx: Context) {
        print("logging in")
        // SQL INJECTION !!! THIS IS TESTING CODE

        val username = ctx.formParam("username")
        val password = ctx.formParam("password")

        val user = Database.getUser(username!!)

        if (BCrypt.checkpw(password, user!!.password))
            ctx.status(200)
        else
            ctx.status(403)
    }

    fun register(ctx: Context) {
        print("registering")
        // SQL INJECTION !!! THIS IS TESTING CODE

        val username = ctx.formParam("username")
        val password = ctx.formParam("password")

        val hashed = BCrypt.hashpw(password, BCrypt.gensalt())

        Database.executeUpdate("insert into person values(${Random.nextInt()}, '$username', '$hashed')")

        ctx.status(200)
    }
}