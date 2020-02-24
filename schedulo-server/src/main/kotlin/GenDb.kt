
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
        statement.executeUpdate(
            "DROP TABLE users;\n" +
                    " \n" +
                    "CREATE TABLE users (\n" +
                    "    id          TEXT PRIMARY KEY,\n" +
                    "    username    TEXT NOT NULL,\n" +
                    "    pwd_hash    TEXT NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "-- #############################################\n" +
                    " \n" +
                    "CREATE TABLE events (\n" +
                    "    id          TEXT    PRIMARY KEY,\n" +
                    "    title       TEXT    NOT NULL,\n" +
                    "    descr       TEXT    NOT NULL,\n" +
                    "    loc         TEXT    NOT NULL,\n" +
                    "    start_time  INTEGER NOT NULL,\n" +
                    "    end_time    INTEGER NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE users_events (\n" +
                    "    usr_id   TEXT NOT NULL REFERENCES users (id),\n" +
                    "    event_id TEXT NOT NULL REFERENCES events (id)\n" +
                    ");"
        )
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