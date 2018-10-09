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

------------------
-- Table engine --
------------------
iF OBJECT_ID('engine') is null
create table engine (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	horsePower int not null,
	[weight] int not null
)
go

-----------------------
-- Table module_type --
-----------------------
iF OBJECT_ID('module_type') is null
create table module_type (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null
)
go

------------------
-- Table module --
------------------
iF OBJECT_ID('module') is null
create table module (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	idType int not null foreign key references module_type(id) on delete cascade on update cascade,
	firePower int null,
	cargoCapacity int null
)
go


-----------------------------
-- Table spaceship_engines --
-----------------------------
iF OBJECT_ID('spaceship_engines') is null
create table spaceship_engines (
    idSpaceship int not null foreign key references spaceship (id) on delete cascade on update cascade,
    idEngine int not null foreign key references engine (id) on delete cascade on update cascade
)
go

-- Index(es)
if not exists(SELECT * FROM sys.indexes WHERE name='idx_spaceship_engines_pk' AND object_id = OBJECT_ID('spaceship_engines'))
create unique index idx_spaceship_engines_pk
	on spaceship_engines (idSpaceship, idEngine)
go

--------------------------
-- Table spaceship_crew --
--------------------------
iF OBJECT_ID('spaceship_crews') is null
create table spaceship_crews (
    idSpaceship int not null foreign key references spaceship (id) on delete cascade on update cascade,
    idCrew int not null foreign key references crew (id) on delete cascade on update cascade
)
go

-- Index(es)
if not exists(SELECT * FROM sys.indexes WHERE name='idx_spaceship_crews_pk' AND object_id = OBJECT_ID('spaceship_crews'))
create unique index idx_spaceship_crews_pk
	on spaceship_crews (idSpaceship, idCrew)
go

-----------------------------
-- Table spaceship_modules --
-----------------------------
iF OBJECT_ID('spaceship_modules') is null
create table spaceship_modules (
    idSpaceship int not null foreign key references spaceship (id) on delete cascade on update cascade,
    idModule int not null foreign key references module (id) on delete cascade on update cascade
)
go

-- Index(es)
if not exists(SELECT * FROM sys.indexes WHERE name='idx_spaceship_modules_pk' AND object_id = OBJECT_ID('spaceship_modules'))
create unique index idx_spaceship_modules_pk
	on spaceship_modules (idSpaceship, idModule)
go


/***********************************************************************************
						INSERTION DES DONNEES
***********************************************************************************/

---------------
-- Table Job --
---------------
set identity_insert Job on

if ((select count(*) from Job where id = 1) = 0)
	insert Job (id, name) values (1, 'pilote')
if ((select count(*) from Job where id = 2) = 0)
	insert Job (id, name) values (2, 'copilote')
if ((select count(*) from Job where id = 3) = 0)
	insert Job (id, name) values (3, 'droid')
if ((select count(*) from Job where id = 4) = 0)
	insert Job (id, name) values (4, 'mecanician')

set identity_insert Job off
