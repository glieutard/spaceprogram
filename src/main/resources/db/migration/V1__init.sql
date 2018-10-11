/***********************************************************************************
				CREATION DES TABLES
***********************************************************************************/

/*****************************************
				VAISEAUX
*****************************************/

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
    name nvarchar(50) not null,
	width int not null,
	length int not null,
	height int not null,
	weight int not null,
	cargoCapacity int not null
)
go

----------------------
-- Table spaceship --
----------------------
iF OBJECT_ID('spaceship') is null
create table spaceship (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	idType int not null foreign key references spaceship_type (id) on delete cascade on update cascade
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
	id int identity(1, 1) primary key not null,
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
    idCrew int not null primary key foreign key references crew (id) on delete cascade on update cascade
)
go

-----------------------------
-- Table spaceship_modules --
-----------------------------
iF OBJECT_ID('spaceship_modules') is null
create table spaceship_modules (
	id int identity(1, 1) primary key not null,
    idSpaceship int not null foreign key references spaceship (id) on delete cascade on update cascade,
    idModule int not null foreign key references module (id) on delete cascade on update cascade
)
go

-- Index(es)
if not exists(SELECT * FROM sys.indexes WHERE name='idx_spaceship_modules_pk' AND object_id = OBJECT_ID('spaceship_modules'))
create unique index idx_spaceship_modules_pk
	on spaceship_modules (idSpaceship, idCrew)
go

/*****************************************
				MISSIONS
*****************************************/

-------------------------
-- Table mission_state --
-------------------------
iF OBJECT_ID('mission_state') is null
create table mission_state (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null
)
go

-------------------
-- Table mission --
-------------------
iF OBJECT_ID('mission') is null
create table mission (
    id int identity(1, 1) primary key not null,
    name nvarchar(50) not null,
	description nvarchar(max) not null,
	idState int not null foreign key references mission_state (id) on delete cascade on update cascade,
	baseCoordinateX int not null,
	baseCoordinateY int not null,
	baseCoordinateZ int not null,
	targetCoordinateX int not null,
	targetCoordinateY int not null,
	targetCoordinateZ int not null,
)
go

------------------------------
-- Table mission_spaceships --
------------------------------
iF OBJECT_ID('mission_spaceships') is null
create table mission_spaceships (
    idMission int not null foreign key references mission (id) on delete cascade on update cascade,
    idSpaceship int not null primary key foreign key references spaceship (id) on delete cascade on update cascade,
	date datetime2 default getDate() not null
)
go


/***********************************************************************************
						CREATION TABLES HISTORIQUE
***********************************************************************************/

-------------------
-- Table revingo --
-------------------
iF OBJECT_ID('revinfo') is null
create table revinfo (
    rev int identity(1, 1) primary key not null,
    revtstmp bigint not null
)
go

----------------
-- Table job_aud --
----------------
iF OBJECT_ID('job_aud') is null
create table job_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null
)
go

--------------------
-- Table crew_aud --
--------------------
iF OBJECT_ID('crew_aud') is null
create table crew_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null,
	age int not null,
	sexe nvarchar(1) not null,
	idJob int not null
)
go

------------------------------
-- Table spaceship_type_aud --
------------------------------
iF OBJECT_ID('spaceship_type_aud') is null
create table spaceship_type_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null,
	width int not null,
	[length] int not null,
	height int not null,
	[weight] int not null,
	cargoCapacity int not null
)
go

-------------------------
-- Table spaceship_aud --
-------------------------
iF OBJECT_ID('spaceship_aud') is null
create table spaceship_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null,
	idType int not null
)
go

----------------------
-- Table engine_aud --
----------------------
iF OBJECT_ID('engine_aud') is null
create table engine_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null,
	horsePower int not null,
	[weight] int not null
)
go

---------------------------
-- Table module_type_aud --
---------------------------
iF OBJECT_ID('module_type_aud') is null
create table module_type_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null
)
go

----------------------
-- Table module_aud --
----------------------
iF OBJECT_ID('module_aud') is null
create table module_aud (
    id int not null,
	rev int not null,
	revtype smallint not null,
    name nvarchar(50) not null,
	idType int not null,
	firePower int null,
	cargoCapacity int null
)
go

---------------------------------
-- Table spaceship_engines_aud --
---------------------------------
iF OBJECT_ID('spaceship_engines_aud') is null
create table spaceship_engines_aud (
	rev int not null,
	revtype smallint not null,
    idSpaceship int not null,
    idEngine int not null
)
go

------------------------------
-- Table spaceship_crew_aud --
------------------------------
iF OBJECT_ID('spaceship_crews_aud') is null
create table spaceship_crews_aud (
    idSpaceship int not null,
    idCrew int not null,
	rev int not null,
	revtype smallint not null,
)
go

---------------------------------
-- Table spaceship_modules_aud --
---------------------------------
iF OBJECT_ID('spaceship_modules_aud') is null
create table spaceship_modules_aud (
	rev int not null,
	revtype smallint not null,
    idSpaceship int not null,
    idModule int not null
)
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
