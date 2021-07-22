create schema gamedata;
create table gamedata.kalahgame (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    pits INT,
    stones INT,
    stonesinhouse7 INT,
    stonesinhouse14 INT
);
create table gamedata.kalahstatus (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    gameId INT,
    current_pit INT,
    stones_in_pit INT
);