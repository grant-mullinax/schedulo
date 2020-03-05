import java.util.*

data class IdEvent(val id: UUID,
                   val name: String,
                   val description: String,
                   val location: String,
                   val start: Long,
                   val end: Long) {
    constructor(id: UUID, event: Event) : this(id, event.name, event.description, event.location, event.start, event.end)
}

data class Event(val name: String,
                 val description: String,
                 val location: String,
                 val start: Long,
                 val end: Long) {
    constructor(event: IdEvent) : this(event.name, event.description, event.location, event.start, event.end)
}


data class User(val id: UUID, val username: String, val password: String)