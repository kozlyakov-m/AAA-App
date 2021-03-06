import com.project.app.dao.AuthorizationDAO
import com.project.app.service.AuthorizationService
import com.project.app.domain.Permission
import com.project.app.ExitCodes
import kotlin.test.*
import org.mockito.Mockito
import org.spekframework.spek2.Spek


object AuthorizationTest : Spek({
    // Setup AuthorizationDAO mock
    val daoMock = Mockito.mock(AuthorizationDAO::class.java)

    Mockito.`when`(daoMock.getResource("A.B",  "READ", "vasya")).thenReturn(Permission(1, "A.B", "READ", "vasya"))
    Mockito.`when`(daoMock.getResource("A", "WRITE", "admin")).thenReturn(Permission(2, "A", "WRITE", "admin"))
    Mockito.`when`(daoMock.getResource("A.B", "EXECUTE", "vasya")).thenReturn(null)

    // Create new Authentication object for each test
    lateinit var auth: AuthorizationService
    beforeEachTest {
        auth = AuthorizationService(daoMock)
    }


    group("Authorization") {
        group("Success authorization") {
            test("vasya READ A.B") {
                val user = "vasya"
                val role = "READ"
                val res = "A.B"
                assertEquals(ExitCodes.SUCCESS, auth.authorization(res, role, user))
            }
            test("admin WRITE A") {
                val user = "admin"
                val role = "WRITE"
                val res = "A"
                assertEquals(ExitCodes.SUCCESS, auth.authorization(res, role, user))
            }
        }

        group("Invalid role") {

            test("user DEFAULT A") {
                val user = "user"
                val role = "DEFAULT"
                val res = "A"
                assertEquals(ExitCodes.INVALID_ROLE, auth.authorization(res, role, user))
            }
        }

        group("No access") {

            test("vasya EXECUTE A.B") {
                val user = "vasya"
                val role = "EXECUTE"
                val res = "A.B"
                assertEquals(ExitCodes.ACCESS_DENIED, auth.authorization(res, role, user))
            }
        }

    }
})
