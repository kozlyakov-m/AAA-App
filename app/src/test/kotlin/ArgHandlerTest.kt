import com.project.app.ArgHandler
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.spekframework.spek2.Spek

object ArgHandlerTest : Spek({

    lateinit var args: Array<String>
    lateinit var argHandler: ArgHandler

    group("Authentication arguments") {
        test("No login") {
            args = arrayOf("-pass", "123")
            argHandler = ArgHandler(args)

            assertFalse(argHandler.isAuthenticationRequired())
        }

        test("No pass") {
            args = arrayOf("-login", "user")
            argHandler = ArgHandler(args)

            assertFalse(argHandler.isAuthenticationRequired())
        }

        test("Login and pass received") {
            args = arrayOf("-login", "user", "-pass", "123")
            argHandler = ArgHandler(args)

            assertTrue(argHandler.isAuthenticationRequired())
        }
    }

    group("Authorization arguments") {
        group("Positive test cases") {
            test("Login, pass, res, role received in random order") {
                args = arrayOf("-login", "user", "-res", "A.B", "-pass", "123", "-role", "WRITE")
                argHandler = ArgHandler(args)

                assertTrue(argHandler.isAuthorizationRequired())
            }

            test("Only res and role received") {
                args = arrayOf("-res", "A.B", "-role", "ADMIN")
                argHandler = ArgHandler(args)

                assertTrue(argHandler.isAuthorizationRequired())
            }
        }

        group("Negative test cases") {
            test("Only login and pass") {
                args = arrayOf("-login", "user", "-pass", "123")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAuthorizationRequired())
            }

            test("Login, pass and res (no role)") {
                args = arrayOf("-login", "user", "-pass", "123", "-res", "A.B.C")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAuthorizationRequired())
            }

            test("Login, pass and role (no res)") {
                args = arrayOf("-login", "user", "-pass", "123", "-role", "READ")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAuthorizationRequired())
            }
        }
    }

    group("Accounting arguments") {
        group("Positive test cases") {
            test("All 7 args received") {
                args = arrayOf("-login", "user", "-pass", "123", "-res", "A.B.C", "-role", "READ", "-ds", "2020-12-30", "-de", "2020-12-31", "-vol", "100")
                argHandler = ArgHandler(args)

                assertTrue(argHandler.isAccountingRequired())
            }
        }

        group("Negative test cases") {
            test("No vol") {
                args = arrayOf("-login", "user", "-pass", "123", "-res", "A.B.C", "-role", "READ", "-ds", "2020-12-30", "-de", "2020-12-31")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAccountingRequired())
            }

            test("No ds") {
                args = arrayOf("-login", "user", "-pass", "123", "-res", "A.B.C", "-role", "READ", "-de", "2020-12-31", "-vol", "100")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAccountingRequired())
            }

            test("No de") {
                args = arrayOf("-login", "user", "-pass", "123", "-res", "A.B.C", "-role", "READ", "de")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAccountingRequired())
            }

            test("Only login and pass") {
                args = arrayOf("-login", "user", "-pass", "123")
                argHandler = ArgHandler(args)

                assertFalse(argHandler.isAccountingRequired())
            }
        }
    }
})
