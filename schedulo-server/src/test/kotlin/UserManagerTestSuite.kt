import junit.framework.TestCase.assertTrue
import org.junit.BeforeClass
import java.sql.DriverManager
import kotlin.random.Random
import org.junit.Test as test

class UserManagerTestSuite {
    companion object {
        @BeforeClass
        @JvmStatic fun setup() {
            val connection = DriverManager.getConnection("jdbc:sqlite:schedulo.db")
            val statement = connection!!.createStatement()
            statement.queryTimeout = 30  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists user")
            statement.executeUpdate("create table user (id integer, Username text, Password text)")

            UserManager.register("tester", "dog")
        }
    }

    @test fun loginSuccessTest() {
        try {
            assertTrue(UserManager.login("tester", "dog"))
        } catch (e: HttpErrorResponseException) {
            assertTrue(false)
        }
    }

    @test fun loginFailureTest() {
        try {
            UserManager.login("tester", "dogger")
            assertTrue(false)
        } catch (e: HttpErrorResponseException) {
            assertTrue(e.code == 403)
        }
    }

    @test fun registerTest() {
        val username = Random.nextInt()
        val pass = Random.nextInt()
        try {
            UserManager.register(username.toString(), pass.toString())

            assertTrue(UserManager.login(username.toString(), pass.toString()))
        } catch (e: HttpErrorResponseException) {
            assertTrue(false)
        }
    }
}