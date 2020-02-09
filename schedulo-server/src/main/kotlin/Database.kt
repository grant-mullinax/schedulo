import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.random.Random

object Database {
    private var connection: Connection? = null

    fun getUser(username: String): User? {
        connection = DriverManager.getConnection("jdbc:sqlite:schedulo.db")

        try {
            val sqlStatement = connection!!.createStatement()
            sqlStatement.queryTimeout = 30  // set timeout to 30 sec.
            val rs = sqlStatement.executeQuery("select * FROM person WHERE Username = '$username';")

            return User(rs.getString("Username"), rs.getString("Password"))
        } catch (e: SQLException) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.message)
        } finally {
            try {
                connection?.close()
            } catch (e: SQLException) {
                // connection close failed.
                System.err.println(e.message)
            }

        }
        return null
    }

    fun executeUpdate(statement: String) {
        connection = DriverManager.getConnection("jdbc:sqlite:schedulo.db")

        try {
            val sqlStatement = connection!!.createStatement()
            sqlStatement.queryTimeout = 30  // set timeout to 30 sec.
            sqlStatement.executeUpdate(statement)

        } catch (e: SQLException) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.message)
        } finally {
            try {
                connection?.close()
            } catch (e: SQLException) {
                // connection close failed.
                System.err.println(e.message)
            }

        }
    }
}