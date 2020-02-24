import org.mindrot.jbcrypt.BCrypt
import java.sql.*
import java.time.Instant
import java.util.*
import kotlin.random.Random

object Database {
    // TODO SWITCH TO PREPARED STATEMENTS TO PREVENT SQL INJECTION
    var connectionUrl = "jdbc:sqlite:schedulo.db"

    private fun <T> managedStatement(
        statementCall: (Statement) -> T
    ): T {
        val connection = DriverManager.getConnection(connectionUrl)

        try {
            val sqlStatement = connection.createStatement()
            sqlStatement.queryTimeout = 30
            return statementCall(sqlStatement)
        } catch (e: SQLException) {
            System.err.println("DATABASE ERROR:")
            System.err.println(e.message)
            throw e
        } finally {
            try {
                connection.close()
            } catch (e: SQLException) {
                System.err.println("FAILED TO CLOSE CONNECTION:")
                System.err.println(e.message)
                throw e
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
            "SELECT * FROM users WHERE username = '$username';"
        ) { rs -> User(
            UUID.fromString(rs.getString("id")),
            rs.getString("username"),
            rs.getString("pwd_hash")
        )}
    }

    fun registerUser(username: String, password: String) {
        val hashed = BCrypt.hashpw(password, BCrypt.gensalt())
        managedUpdate("INSERT INTO users VALUES('${UUID.randomUUID()}', '$username', '$hashed')")
    }

    fun createEvent(event: Event) {
        val connection = DriverManager.getConnection(connectionUrl)

        val sqlStatement = connection.prepareStatement(
            "INSERT INTO events (id, title, descr, loc, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)"
        )

        sqlStatement.setString(1, UUID.randomUUID().toString())
        sqlStatement.setString(2, event.name)
        sqlStatement.setString(3, event.description)
        sqlStatement.setString(4, event.location)
        // todo does sqllite care about longs vs strings?
        sqlStatement.setLong(5, event.start.toEpochMilli())
        sqlStatement.setLong(6, event.end.toEpochMilli())

        sqlStatement.executeUpdate()

        connection.close()
    }

    fun getEvents(): List<Event> {
        return managedQuery(
            "SELECT * FROM events;"
        ) { rs ->
            val results = mutableListOf<Event>()
            while(rs.next())
            {
                results.add(Event(
                    rs.getString("title"),
                    rs.getString("descr"),
                    rs.getString("loc"),
                    Instant.ofEpochMilli(rs.getLong("start_time")),
                    Instant.ofEpochMilli(rs.getLong("end_time"))
                ))
            }

            return@managedQuery results.toList()
        }
    }
}