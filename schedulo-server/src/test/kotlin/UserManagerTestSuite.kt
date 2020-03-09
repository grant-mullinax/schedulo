import junit.framework.TestCase.assertTrue
import org.junit.BeforeClass
import org.mindrot.jbcrypt.BCrypt
import java.lang.Exception
import java.sql.DriverManager
import java.time.Instant
import kotlin.random.Random
import org.junit.Test as test

class UserManagerTestSuite {
    companion object {
        @BeforeClass
        @JvmStatic fun setup() {
            val testDbUrl = "jdbc:sqlite:scheduloTest.db"
            val connection = DriverManager.getConnection(testDbUrl)
            val statement = connection!!.createStatement()
            statement.queryTimeout = 30  // set timeout to 30 sec.

            statement.executeUpdate(
                    "DROP TABLE IF EXISTS users;" +
                        "DROP TABLE IF EXISTS events;" +
                        "DROP TABLE IF EXISTS users_events;")
            statement.executeUpdate(
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
                        ");")

            Database.connectionUrl = testDbUrl

            UserManager.register("tester", "dog")
        }
    }

    @test fun databaseHashTest() {
        val user = Database.getUser("tester")
        assertTrue(BCrypt.checkpw("dog", user.password))
    }

    @test fun loginSuccessTest() {
        try {
            UserManager.login("tester", "dog")
            assertTrue(true)
        } catch (e: Exception) {
            assertTrue(false)
        }
    }

    @test fun loginFailureTest() {
        try {
            UserManager.login("tester", "dogger")
            assertTrue(false)
        } catch (e: Exception) {
            assertTrue(true)
        }
    }

    @test fun registerTest() {
        val username = Random.nextInt()
        val pass = Random.nextInt()
        try {
            UserManager.register(username.toString(), pass.toString())

            UserManager.login(username.toString(), pass.toString())
            assertTrue(true)
        } catch (e: Exception) {
            assertTrue(false)
        }
    }

    @test fun createEventTest() {
        try {
            val user = UserManager.login("tester", "dog")
            val now = Instant.now().toEpochMilli()
            Database.createEventForUser(Event("name", "desc", "loc", now, now + 100), user)
            assertTrue(true)
        } catch (e: Exception) {
            assertTrue(false)
        }
    }

    @test fun getEventTest() {
        try {
            val user = UserManager.login("tester", "dog")
            val now = Instant.now().toEpochMilli()
            Database.createEventForUser(Event("create test", "desc", "loc", now, now + 100), user)
            assertTrue(Database.getEventsForUser(user).filter { it.name == "create test" }.size == 1)
        } catch (e: Exception) {
            assertTrue(false)
        }
    }
}