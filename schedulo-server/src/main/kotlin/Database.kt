import org.mindrot.jbcrypt.BCrypt
import java.sql.*
import kotlin.random.Random

object Database {
    // TODO SWITCH TO PREPARED STATEMENTS TO PREVENT SQL INJECTION

    private fun <T> managedStatement(
        statementCall: (Statement) -> T
    ): T {
        val connection = DriverManager.getConnection("jdbc:sqlite:schedulo.db")

        try {
            val sqlStatement = connection.createStatement()
            sqlStatement.queryTimeout = 30
            return statementCall(sqlStatement)
        } catch (e: SQLException) {
            System.err.println("DATABASE ERROR:")
            System.err.println(e.message)
            throw HttpErrorResponseException(404, "Database Error")
        } finally {
            try {
                connection.close()
            } catch (e: SQLException) {
                System.err.println("FAILED TO CLOSE CONNECTION:")
                System.err.println(e.message)
                throw HttpErrorResponseException(404, "Database Error")
            }
        }
    }

    private fun <T> managedQuery(query: String, transformation: (ResultSet) -> T): T {
        return managedStatement { s -> transformation(s.executeQuery(query)) }
    }

    private fun managedUpdate(update: String): Int {
        return managedStatement { s -> s.executeUpdate(update)}
    }

    fun getUser(username: String): User {
        return managedQuery(
            "select * FROM person WHERE Username = '$username';"
        ) { rs -> User(rs.getString("Username"), rs.getString("Password"))}
    }

    fun registerUser(username: String, password: String) {
        val hashed = BCrypt.hashpw(password, BCrypt.gensalt())
        managedUpdate("insert into person values(${Random.nextInt()}, '$username', '$hashed')")
    }
}