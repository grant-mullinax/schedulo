import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/login", UserManager::login)
    app.post("/register", UserManager::register)
}