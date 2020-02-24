DROP TABLE users;
 
CREATE TABLE users (
    id          TEXT PRIMARY KEY,
    username    TEXT NOT NULL,
    pwd_hash    TEXT NOT NULL,
);

-- #############################################
 
CREATE TABLE events (
    id          TEXT    PRIMARY KEY,
    title       TEXT    NOT NULL,
    descr       TEXT    NOT NULL,
    loc         TEXT    NOT NULL,
    start_time  INTEGER NOT NULL,
    end_time    INTEGER NOT NULL,
);

CREATE TABLE users_events (
    usr_id   TEXT NOT NULL REFERENCES users (id),
    event_id TEXT NOT NULL REFERENCES events (id)
);