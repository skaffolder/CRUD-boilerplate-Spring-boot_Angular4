--
-- Database: `Manage_Film_Example_db`
--
CREATE TABLE users (    
    "id" NUMBER(38,0),
    "username" VARCHAR2(30 BYTE), 
    "password" VARCHAR2(32 BYTE), 
    "mail" VARCHAR2(32 BYTE), 
    "name" VARCHAR2(32 BYTE), 
    "surname" VARCHAR2(32 BYTE)
);

CREATE UNIQUE INDEX "USERS_PK" ON users ("id") ;
ALTER TABLE users MODIFY ("id" NOT NULL ENABLE);
ALTER TABLE users ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("id") ENABLE;

INSERT INTO users ("id", "username", "password", "mail", "name", "surname") VALUES (1, 'admin', '1a1dc91c907325c69271ddf0c944bc72', '', '', '');

CREATE TABLE roles (	
    "role" VARCHAR2(30 BYTE), 
    "_user" NUMBER(38, 0), 
    "id" NUMBER(38,0)
);

CREATE UNIQUE INDEX "ROLES_PK" ON roles ("id");
ALTER TABLE roles ADD CONSTRAINT "ROLES_PK" PRIMARY KEY ("id");
ALTER TABLE roles MODIFY ("id" NOT NULL ENABLE);
ALTER TABLE roles ADD CONSTRAINT "ROLES_FK1" FOREIGN KEY ("_user")
	  REFERENCES users ("id") ENABLE;

INSERT INTO roles ("role", "_user", "id") VALUES ('ADMIN', '1', 1);


-- ENTITIES

--
-- Struttura della tabella `Actor`
--

    CREATE TABLE Actor (	
        "birthDate" date,
        "name" varchar(30),
        "surname" varchar(30),
        
        -- RELAZIONI
        
        "id" NUMBER(38,0)
    );
    
    
    CREATE UNIQUE INDEX "Actor_PK" ON Actor ("id");
    ALTER TABLE Actor ADD CONSTRAINT "Actor_PK" PRIMARY KEY ("id");
    ALTER TABLE Actor MODIFY ("id" NOT NULL ENABLE);

--
-- Struttura della tabella `Film`
--

    CREATE TABLE Film (	
        "genre" varchar(30),
        "title" varchar(30),
        "year" numeric,
        
        -- RELAZIONI
        "filmMaker" NUMBER(38, 0), 
        
        "id" NUMBER(38,0)
    );
    
    
    CREATE UNIQUE INDEX "Film_PK" ON Film ("id");
    ALTER TABLE Film ADD CONSTRAINT "Film_PK" PRIMARY KEY ("id");
    ALTER TABLE Film MODIFY ("id" NOT NULL ENABLE);

--
-- Struttura della tabella `FilmMaker`
--

    CREATE TABLE FilmMaker (	
        "name" varchar(30),
        "surname" varchar(30),
        
        -- RELAZIONI
        
        "id" NUMBER(38,0)
    );
    
    
    CREATE UNIQUE INDEX "FilmMaker_PK" ON FilmMaker ("id");
    ALTER TABLE FilmMaker ADD CONSTRAINT "FilmMaker_PK" PRIMARY KEY ("id");
    ALTER TABLE FilmMaker MODIFY ("id" NOT NULL ENABLE);


-- RELATIONS

    -- RELATIONS TABLE Actor
    
        
        
        
        



    -- RELATIONS TABLE Film
    
        
        -- foreign key filmMaker
        ALTER TABLE Film ADD CONSTRAINT "Film_filmMaker" FOREIGN KEY ("filmMaker")
        	  REFERENCES FilmMaker ("id") ON DELETE SET NULL ENABLE;
        
        
        
        
        -- relation m:m cast Film - Actor
        CREATE TABLE Film_cast (
            "id" NUMBER(38,0),
            "id_Film" NUMBER(38, 0),	
            "id_Actor" NUMBER(38, 0)
        );
        
        
        ALTER TABLE Film_cast ADD CONSTRAINT "reference_Film_cast" FOREIGN KEY ("id_Film")
        	  REFERENCES Film ("id") ON DELETE CASCADE ENABLE;
        	  
        	  
        ALTER TABLE Film_cast ADD CONSTRAINT "reference_Actor_cast" FOREIGN KEY ("id_Actor")
        	  REFERENCES Actor ("id") ON DELETE CASCADE ENABLE;
        



    -- RELATIONS TABLE FilmMaker
    
        
        
        
        



