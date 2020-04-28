import com.project.app.dao.AuthenticationDAO
import com.project.app.service.AuthenticationService
import com.project.app.domain.User
import com.project.app.ExitCodes
import kotlin.test.*
import org.mockito.Mockito
import org.spekframework.spek2.Spek


object AuthenticationTest : Spek({
    // Setup AuthenticationDAO mock
    val daoMock = Mockito.mock(AuthenticationDAO::class.java)

    Mockito.`when`(daoMock.findUser("bruh")).thenReturn(User("bruh",
            "dc6a8709e9fc8de1acea34fdc98c842911686ca0c2a0b12127c512a5ed7ab382",
            "iYqHUi2<2zPhrGIL8]?p8m;bteA?ETaT"))
    Mockito.`when`(daoMock.findUser("test")).thenReturn(User("test",
            "c6d6ced902fe90f039f168837f7ce3d313df040e071281317fc6781a60cac2bc",
            "olMMIDct3GkrY:?Xp1WDJOPTw2IY0`a["))
        Mockito.`when`(daoMock.findUser("nobody")).thenReturn(null)

    // Create new Authentication object for each test
    lateinit var auth: AuthenticationService
    beforeEachTest {
        auth = AuthenticationService(daoMock)
    }

    group("Login validation") {
        group("Valid login") {

            test("Login with arbitrary size") {
                val login = "test"
                assertTrue(auth.isLoginValid(login))
            }

            test("Login with smallest valid size") {
                val login = "a"
                assertTrue(auth.isLoginValid(login))
            }

            test("Login with biggest valid size") {
                val login = "abcdefghij"
                assertTrue(auth.isLoginValid(login))
            }
        }

        group("Invalid login") {

            test("Empty login") {
                val login = ""
                assertFalse(auth.isLoginValid(login))
            }

            test("Login too long") {
                val login = "abcdefghijk"
                assertFalse(auth.isLoginValid(login))
            }

            test("Login with non-letter characters") {
                val login = "vasya!)"
                assertFalse(auth.isLoginValid(login))
            }

            test("Login with numbers") {
                val login = "vasya2005"
                assertFalse(auth.isLoginValid(login))
            }

            test("Login with uppercase letters") {
                val login = "Vasyan"
                assertFalse(auth.isLoginValid(login))
            }

            test("Login with non-latin letters") {
                val login = "вася"
                assertFalse(auth.isLoginValid(login))
            }
        }
    }


    group("Authentication") {

        test("Invalid login") {
            val login = "ыыы"
            val pass = "1234"
            assertEquals(ExitCodes.INVALID_LOGIN, auth.authentication(login, pass))
        }

        test("Right login-password combination") {
            val login = "bruh"
            val pass = "123"
            assertEquals(ExitCodes.SUCCESS, auth.authentication(login, pass))
        }

        test("Existing login, wrong password") {
            val login = "test"
            val pass = "asdf"
            assertEquals(ExitCodes.WRONG_PASSWORD, auth.authentication(login, pass))
        }

        test("Password of another user") {
            val login = "bruh"
            val pass = "admin"
            assertEquals(ExitCodes.WRONG_PASSWORD, auth.authentication(login, pass))
        } 
        test("User not found") {
            val login = "nobody"
            val pass = "0000"
           assertEquals(ExitCodes.USER_NOT_FOUND, auth.authentication(login, pass))
        }
    }
})
