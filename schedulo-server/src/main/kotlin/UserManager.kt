import io.javalin.http.Context
import org.mindrot.jbcrypt.BCrypt

data class User(val username: String, val password: String)

object UserManager {
    fun login(username: String, password: String): Boolean {
        val user = Database.getUser(username)

        if (BCrypt.checkpw(password, user.password))
            return true
        else
            throw HttpErrorResponseException(403, "Wrong password")
    }

    fun register(username: String, password: String): Boolean {
        Database.registerUser(username, password)
        return true
    }
}