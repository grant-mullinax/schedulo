import junit.framework.TestCase.assertTrue
import org.junit.BeforeClass
import java.lang.Exception
import java.sql.DriverManager
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

            statement.executeUpdate("drop table if exists user")
            statement.executeUpdate("create table user (id text, Username text, Password text)")

            Database.connectionUrl = testDbUrl

            UserManager.register("tester", "dog")
        }
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
}