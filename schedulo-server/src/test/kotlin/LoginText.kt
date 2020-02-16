import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlin.random.Random
import org.junit.Test as test

class UserManagerTestSuite {
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