DROP TABLE MoneyEarned;
DROP TABLE ZombiesKilled;
DROP TABLE HighScore;
DROP TABLE User;

CREATE TABLE User(
    UserID      INTEGER PRIMARY KEY AUTOINCREMENT,
    UserName    TEXT
);

CREATE TABLE HighScore(
    UserID       INTEGER,
    HighScore    INTEGER,
    PRIMARY KEY(UserID), 
    FOREIGN KEY(UserID) REFERENCES User(UserID)
    ON DELETE CASCADE
);

CREATE TABLE ZombiesKilled(
    UserID          INTEGER,
    ZombiesKilled   INTEGER,
    PRIMARY KEY(UserID),
    FOREIGN KEY(UserID) REFERENCES User(UserID)
    ON DELETE CASCADE
);

CREATE TABLE MoneyEarned(
    UserID        INTEGER,
    MoneyEarned   INTEGER,
    PRIMARY KEY(UserID),
    FOREIGN KEY(UserID) REFERENCES User(UserID) 
    ON DELETE CASCADE
);

