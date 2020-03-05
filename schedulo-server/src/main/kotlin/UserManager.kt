import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.mindrot.jbcrypt.BCrypt
import java.util.*

object UserManager {
    fun login(username: String, password: String): User {
        val user = Database.getUser(username)

        if (!BCrypt.checkpw(password, user.password))
            throw UnauthorizedResponse()

        return user
    }

    fun register(username: String, password: String) {
        Database.registerUser(username, password)
    }
}