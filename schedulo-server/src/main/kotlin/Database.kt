import org.mindrot.jbcrypt.BCrypt
import java.sql.*
import java.time.Instant
import java.util.*

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

    fun createEventForUser(event: Event, user: User) : IdEvent{
        val connection = DriverManager.getConnection(connectionUrl)

        val eventStatement = connection.prepareStatement(
            "INSERT INTO events (id, title, descr, loc, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)"
        )

        val eventId = UUID.randomUUID()

        eventStatement.setString(1, eventId.toString())
        eventStatement.setString(2, event.name)
        eventStatement.setString(3, event.description)
        eventStatement.setString(4, event.location)
        eventStatement.setLong(5, event.start)
        eventStatement.setLong(6, event.end)

        eventStatement.executeUpdate()

        // todo if one fails both should fail

        val userEventStatement = connection.prepareStatement(
            "INSERT INTO users_events (usr_id, event_id) VALUES (?, ?)"
        )

        userEventStatement.setString(1, user.id.toString())
        userEventStatement.setString(2, eventId.toString())

        userEventStatement.executeUpdate()

        connection.close()

        return IdEvent(eventId, event)
    }

    fun getEventsForUser(user: User): List<IdEvent> {
        return managedQuery(
            "SELECT events.*\n" +
                   "FROM users\n" +
                   "JOIN users_events ON users.id = users_events.usr_id AND users.id = '${user.id}'\n" +
                   "JOIN events ON events.id = users_events.event_id;"
        ) { rs ->
            val results = mutableListOf<IdEvent>()
            while(rs.next())
            {
                results.add(IdEvent(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("title"),
                    rs.getString("descr"),
                    rs.getString("loc"),
                    rs.getLong("start_time"),
                    rs.getLong("end_time")
                ))
            }

            return@managedQuery results.toList()
        }
    }

    fun getEvents(): List<IdEvent> {
        return managedQuery(
            "SELECT * FROM events;"
        ) { rs ->
            val results = mutableListOf<IdEvent>()
            while(rs.next())
            {
                results.add(IdEvent(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("title"),
                    rs.getString("descr"),
                    rs.getString("loc"),
                    rs.getLong("start_time"),
                    rs.getLong("end_time")
                ))
            }

            return@managedQuery results.toList()
        }
    }
}