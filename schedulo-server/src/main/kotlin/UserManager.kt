import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.mindrot.jbcrypt.BCrypt
import java.sql.SQLException
import java.util.*

object UserManager {
    fun login(username: String, password: String): User {
        val user = Database.getUser(username)
        if (!BCrypt.checkpw(password, user.password))
            throw UnauthorizedResponse()

        return user
    }

    fun register(username: String, password: String) {
        try {
            Database.registerUser(username, password)
        } catch (ex: SQLException) {
            // todo make code more specific
            if (ex.errorCode == 19) { // unique username failed
                throw BadRequestResponse("Username must be unique")
            } else {
                throw ex
            }
        }
    }
}