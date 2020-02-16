
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main(args: Array<String>) {
    var connection: Connection? = null
    try {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:schedulo.db")
        val statement = connection!!.createStatement()
        statement.queryTimeout = 30  // set timeout to 30 sec.

        statement.executeUpdate("drop table if exists user")
        statement.executeUpdate("create table user (id integer, Username text, Password text)")
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