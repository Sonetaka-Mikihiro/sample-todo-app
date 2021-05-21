/* このファイルに記述されているSQLは起動時に実行されます */

CREATE TABLE IF NOT EXISTS TODO_APP (
    TODO_ID int PRIMARY KEY,
    CATEGORY varchar(30) NOT NULL,
    TITLE varchar(30) NOT NULL,
    DETAIL varchar(100) NOT NULL,
    DEADLINE date,
    DELETED_FLAG bit
);

CREATE TABLE IF NOT EXISTS SUBTASK (
    TODO_ID int,
    SUBTASK_ID int,
    SUBTITLE varchar(30) NOT NULL
);