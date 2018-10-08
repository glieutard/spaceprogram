/***********************************************************************************
				CREATION DES TABLES
***********************************************************************************/

----------------
-- Table job --
----------------
iF OBJECT_ID('job') is null
create table job (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null
)
go

----------------
-- Table crew --
----------------
iF OBJECT_ID('crew') is null
create table crew (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	age int not null,
	sexe nvarchar(1) not null,
	idJob int not null foreign key references job (id) on delete cascade on update cascade
)
go

--------------------------
-- Table spaceship_type --
--------------------------
iF OBJECT_ID('spaceship_type') is null
create table spaceship_type (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null
)
go

----------------------
-- Table spaceship --
----------------------
iF OBJECT_ID('spaceship') is null
create table spaceship (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	idType int not null foreign key references spaceship_type (id) on delete cascade on update cascade,
	width int not null,
	[length] int not null,
	height int not null,
	[weight] int not null,
	cargoCapacity int not null
)
go

----------------------
-- Table engine --
----------------------
iF OBJECT_ID('engine') is null
create table engine (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	horsePower int not null,
	[weight] int not null
)
go

----------------------------
-- Table spaceship_engine --
----------------------------
iF OBJECT_ID('spaceship_engine') is null
create table spaceship_engine (
    idSpaceship int not null foreign key references spaceship (id) on delete cascade on update cascade,
    idEngine int not null foreign key references engine (id) on delete cascade on update cascade
)
go

-- Index(es)
if not exists(SELECT * FROM sys.indexes WHERE name='idx_spaceship_engine_pk' AND object_id = OBJECT_ID('spaceship_engine'))
create unique index idx_spaceship_engine_pk
	on spaceship_engine (idSpaceship, idEngine)
go