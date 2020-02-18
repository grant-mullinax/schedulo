import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.post("/login", HttpRequestParser::login)
    app.post("/register", HttpRequestParser::register)
}

