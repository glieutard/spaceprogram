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
    name nvarchar(250) not null
)
go

----------------
-- Table crew --
----------------
iF OBJECT_ID('crew') is null
create table crew (
    id int identity(1, 1) primary key not null,
    name nvarchar(250) not null,
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
    name nvarchar(250) not null,
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
    name nvarchar(250) not null,
	idType int not null foreign key references spaceship_type (id) on delete cascade on update cascade
)
go

------------------
-- Table engine --
------------------
iF OBJECT_ID('engine') is null
create table engine (
    id int identity(1, 1) primary key not null,
    name nvarchar(250) not null,
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
    name nvarchar(250) not null
)
go

------------------
-- Table module --
------------------
iF OBJECT_ID('module') is null
create table module (
    id int identity(1, 1) primary key not null,
    name nvarchar(250) not null,
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
	on spaceship_modules (idSpaceship, idModule)
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
    name nvarchar(250) not null
)
go

-------------------
-- Table mission --
-------------------
iF OBJECT_ID('mission') is null
create table mission (
    id int identity(1, 1) primary key not null,
    name nvarchar(250) not null,
	description nvarchar(max) not null,
	idState int not null foreign key references mission_state (id) on delete cascade on update cascade,
	baseCoordinateX real not null,
	baseCoordinateY real not null,
	baseCoordinateZ real not null,
	targetCoordinateX real not null,
	targetCoordinateY real not null,
	targetCoordinateZ real not null,
)
go

------------------------------
-- Table mission_spaceships --
------------------------------
iF OBJECT_ID('mission_spaceships') is null
create table mission_spaceships (
    idMission int not null foreign key references mission (id) on delete cascade on update cascade,
    idSpaceship int not null primary key foreign key references spaceship (id) on delete cascade on update cascade,
	date datetime2 null
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
    name nvarchar(250) null
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
    name nvarchar(250) null,
	age int null,
	sexe nvarchar(1) null,
	idJob int null
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
    name nvarchar(250) null,
	width int null,
	[length] int null,
	height int null,
	[weight] int null,
	cargoCapacity int null
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
    name nvarchar(250) null,
	idType int null
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
    name nvarchar(250) null,
	horsePower int null,
	[weight] int null
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
    name nvarchar(250) null
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
    name nvarchar(250) not null,
	idType int null,
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
    idSpaceship int null,
    idEngine int null
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
	revtype smallint null,
)
go

---------------------------------
-- Table spaceship_modules_aud --
---------------------------------
iF OBJECT_ID('spaceship_modules_aud') is null
create table spaceship_modules_aud (
	rev int not null,
	revtype smallint not null,
    idSpaceship int null,
    idModule int null
)
go


/***********************************************************************************
						INSERTION DES DONNEES
***********************************************************************************/

-------------------------
-- Table mission_state --
-------------------------
set identity_insert mission_state on

insert mission_state (id, name) values (1, 'backlog')
insert mission_state (id, name) values (2, 'in progress')
insert mission_state (id, name) values (3, 'failed')
insert mission_state (id, name) values (4, 'accomplished')

set identity_insert mission_state off

-----------------------
-- Table module_type --
-----------------------
SET IDENTITY_INSERT [dbo].[module_type] ON 

INSERT [dbo].[module_type] ([id], [name]) VALUES (1, N'armament')
INSERT [dbo].[module_type] ([id], [name]) VALUES (2, N'power plant')
INSERT [dbo].[module_type] ([id], [name]) VALUES (3, N'shield')
INSERT [dbo].[module_type] ([id], [name]) VALUES (4, N'sensor system')
INSERT [dbo].[module_type] ([id], [name]) VALUES (5, N'navigation system')
INSERT [dbo].[module_type] ([id], [name]) VALUES (6, N'escape craft')

SET IDENTITY_INSERT [dbo].[module_type] OFF

---------------------------
-- Table module_type_aud --
---------------------------
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (1, 5, 0, N'armament')
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (2, 5, 0, N'power plant')
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (3, 5, 0, N'shield')
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (4, 5, 0, N'sensor system')
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (5, 5, 0, N'navigation system')
INSERT [dbo].[module_type_aud] ([id], [rev], [revtype], [name]) VALUES (6, 5, 0, N'escape craft')

-------------------------
-- Table spacship_type --
-------------------------
SET IDENTITY_INSERT [dbo].[spaceship_type] ON 

INSERT [dbo].[spaceship_type] ([id], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (1, N'X-Wing', 1140, 1250, 230, 10, 0)
INSERT [dbo].[spaceship_type] ([id], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (2, N'Y-Wing', 800, 1600, 230, 10, 0)
INSERT [dbo].[spaceship_type] ([id], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (3, N'Light Cargo', 2560, 3475, 827, 250, 100)
INSERT [dbo].[spaceship_type] ([id], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (4, N'A-Wing', 462, 768, 202, 6, 0)

SET IDENTITY_INSERT [dbo].[spaceship_type] OFF

-----------------------------
-- Table spacship_type_aud --
-----------------------------
INSERT [dbo].[spaceship_type_aud] ([id], [rev], [revtype], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (1, 1, 0, N'X-Wing', 1140, 1250, 230, 10, 0)
INSERT [dbo].[spaceship_type_aud] ([id], [rev], [revtype], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (2, 1, 0, N'Y-Wing', 800, 1600, 230, 10, 0)
INSERT [dbo].[spaceship_type_aud] ([id], [rev], [revtype], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (3, 1, 0, N'Light Cargo', 2560, 3475, 827, 250, 100)
INSERT [dbo].[spaceship_type_aud] ([id], [rev], [revtype], [name], [width], [length], [height], [weight], [cargoCapacity]) VALUES (4, 1, 0, N'A-Wing', 462, 768, 202, 6, 0)

---------------
-- Table job --
---------------
SET IDENTITY_INSERT [dbo].[job] ON 

INSERT [dbo].[job] ([id], [name]) VALUES (1, N'pilote')
INSERT [dbo].[job] ([id], [name]) VALUES (2, N'copilote')
INSERT [dbo].[job] ([id], [name]) VALUES (3, N'droid')
INSERT [dbo].[job] ([id], [name]) VALUES (4, N'mecanician')
INSERT [dbo].[job] ([id], [name]) VALUES (5, N'general')
INSERT [dbo].[job] ([id], [name]) VALUES (6, N'captain')
INSERT [dbo].[job] ([id], [name]) VALUES (7, N'emperor')
INSERT [dbo].[job] ([id], [name]) VALUES (8, N'soldier')

SET IDENTITY_INSERT [dbo].[job] OFF

-------------------
-- Table job_aud --
-------------------
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (1, 2, 0, N'pilote')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (2, 2, 0, N'copilote')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (3, 2, 0, N'droid')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (4, 2, 0, N'mecanician')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (5, 2, 0, N'general')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (6, 2, 0, N'captain')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (7, 2, 0, N'emperor')
INSERT [dbo].[job_aud] ([id], [rev], [revtype], [name]) VALUES (8, 2, 0, N'soldier')

----------------
-- Table crew --
----------------
SET IDENTITY_INSERT [dbo].[crew] ON 

INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (1, N'Luke Skywalker', 19, N'M', 1)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (2, N'Leia Organa', 19, N'F', 5)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (3, N'Han Solo', 32, N'M', 1)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (4, N'Chewbacca', 200, N'M', 2)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (5, N'R2D2', 32, N'M', 3)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (6, N'C3PO', 32, N'M', 3)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (7, N'Palpatine', 82, N'M', 7)
INSERT [dbo].[crew] ([id], [name], [age], [sexe], [idJob]) VALUES (8, N'Wedge Antilles', 21, N'M', 1)

SET IDENTITY_INSERT [dbo].[crew] OFF

--------------------
-- Table crew_aud --
--------------------
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (1, 3, 0, N'Luke Skywalker', 19, N'M', 1)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (2, 3, 0, N'Leia Organa', 19, N'F', 5)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (3, 3, 0, N'Han Solo', 32, N'M', 1)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (4, 3, 0, N'Chewbacca', 200, N'M', 2)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (5, 3, 0, N'R2D2', 32, N'M', 3)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (6, 3, 0, N'C3PO', 32, N'M', 3)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (7, 3, 0, N'Palpatine', 82, N'M', 7)
INSERT [dbo].[crew_aud] ([id], [rev], [revtype], [name], [age], [sexe], [idJob]) VALUES (8, 3, 0, N'Wedge Antilles', 21, N'M', 1)

------------------
-- Table engine --
------------------
SET IDENTITY_INSERT [dbo].[engine] ON 

INSERT [dbo].[engine] ([id], [name], [horsePower], [weight]) VALUES (1, N'Hyperdrive', 0, 2)
INSERT [dbo].[engine] ([id], [name], [horsePower], [weight]) VALUES (2, N'Incom 4L4 fusial thrust engines', 750, 4)
INSERT [dbo].[engine] ([id], [name], [horsePower], [weight]) VALUES (3, N'Novaldex K-88 Event Horizon sublight engines', 350, 1)
INSERT [dbo].[engine] ([id], [name], [horsePower], [weight]) VALUES (4, N'Girodyne SRB42 sublight engines', 2500, 10)

SET IDENTITY_INSERT [dbo].[engine] OFF

----------------------
-- Table engine_aud --
----------------------
INSERT [dbo].[engine_aud] ([id], [rev], [revtype], [name], [horsePower], [weight]) VALUES (1, 4, 0, N'Hyperdrive', 0, 2)
INSERT [dbo].[engine_aud] ([id], [rev], [revtype], [name], [horsePower], [weight]) VALUES (2, 4, 0, N'Incom 4L4 fusial thrust engines', 750, 4)
INSERT [dbo].[engine_aud] ([id], [rev], [revtype], [name], [horsePower], [weight]) VALUES (3, 4, 0, N'Novaldex K-88 Event Horizon sublight engines', 350, 1)
INSERT [dbo].[engine_aud] ([id], [rev], [revtype], [name], [horsePower], [weight]) VALUES (4, 4, 0, N'Girodyne SRB42 sublight engines', 2500, 10)

------------------
-- Table module --
------------------
SET IDENTITY_INSERT [dbo].[module] ON 

INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (1, N'Laser canons', 1, 15, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (2, N'Deflector shields', 3, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (3, N'Quadrex power core', 2, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (4, N'Incom N2I-4 power converter', 2, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (5, N'Konesayr TLB power converter', 2, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (6, N'Torplex fore deflector shield generator', 3, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (7, N'Siep-Irol passive sensor antenna', 4, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (8, N'Rubicon navigation computer', 5, 0, 0)
INSERT [dbo].[module] ([id], [name], [idType], [firePower], [cargoCapacity]) VALUES (9, N'Corellian Engineering Corporation AG-2G quad laser cannons', 1, 30, 0)

SET IDENTITY_INSERT [dbo].[module] OFF

----------------------
-- Table module_aud --
----------------------
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (1, 6, 0, N'Laser canons', 1, 15, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (2, 6, 0, N'Deflector shields', 3, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (3, 6, 0, N'Quadrex power core', 2, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (4, 6, 0, N'Incom N2I-4 power converter', 2, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (5, 6, 0, N'Konesayr TLB power converter', 2, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (6, 6, 0, N'Torplex fore deflector shield generator', 3, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (7, 6, 0, N'Siep-Irol passive sensor antenna', 4, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (8, 6, 0, N'Rubicon navigation computer', 5, 0, 0)
INSERT [dbo].[module_aud] ([id], [rev], [revtype], [name], [idType], [firePower], [cargoCapacity]) VALUES (9, 6, 0, N'Corellian Engineering Corporation AG-2G quad laser cannons', 1, 30, 0)

---------------------
-- Table spaceship --
---------------------
SET IDENTITY_INSERT [dbo].[spaceship] ON 

INSERT [dbo].[spaceship] ([id], [name], [idType]) VALUES (1, N'X-Wing Red Five', 1)
INSERT [dbo].[spaceship] ([id], [name], [idType]) VALUES (2, N'X-Wing Red Two', 1)
INSERT [dbo].[spaceship] ([id], [name], [idType]) VALUES (3, N'Millennium Falcon', 3)

SET IDENTITY_INSERT [dbo].[spaceship] OFF

-------------------------
-- Table spaceship_aud --
-------------------------
INSERT [dbo].[spaceship_aud] ([id], [rev], [revtype], [name], [idType]) VALUES (1, 7, 0, N'X-Wing Red Five', 1)
INSERT [dbo].[spaceship_aud] ([id], [rev], [revtype], [name], [idType]) VALUES (2, 7, 0, N'X-Wing Red Two', 1)
INSERT [dbo].[spaceship_aud] ([id], [rev], [revtype], [name], [idType]) VALUES (3, 7, 0, N'Millennium Falcon', 3)

-------------------
-- Table mission --
-------------------
SET IDENTITY_INSERT [dbo].[mission] ON 

INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (1, N'Save Princess Leila Organa', N'Infiltrate the Death Star and save Princess Leila Organa', 2, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)
INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (2, N'Destroy the Death Star', N'Use our new weapon to destroy the Death Star.', 1, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)
INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (3, N'Save Princess Leila Organa', N'Infiltrate the Death Star and save Princess Leila Organa', 2, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)
INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (4, N'Destroy the Death Star', N'Use our new weapon to destroy the Death Star.', 1, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)
INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (5, N'Save Princess Leila Organa', N'Infiltrate the Death Star and save Princess Leila Organa', 2, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)
INSERT [dbo].[mission] ([id], [name], [description], [idState], [baseCoordinateX], [baseCoordinateY], [baseCoordinateZ], [targetCoordinateX], [targetCoordinateY], [targetCoordinateZ]) VALUES (6, N'Destroy the Death Star', N'Use our new weapon to destroy the Death Star.', 1, 1.156, 2.785, 3.365, 11.103, 12.185, 13.654)

SET IDENTITY_INSERT [dbo].[mission] OFF

------------------------------
-- Table mission_spaceships --
------------------------------
INSERT [dbo].[mission_spaceships] ([idMission], [idSpaceship], [date]) VALUES (6, 1, NULL)
INSERT [dbo].[mission_spaceships] ([idMission], [idSpaceship], [date]) VALUES (1, 2, CAST(N'2018-10-14T19:06:58.2790000' AS DateTime2))

---------------------------
-- Table spaceship_crews --
---------------------------
INSERT [dbo].[spaceship_crews] ([idSpaceship], [idCrew]) VALUES (1, 1)
INSERT [dbo].[spaceship_crews] ([idSpaceship], [idCrew]) VALUES (3, 3)
INSERT [dbo].[spaceship_crews] ([idSpaceship], [idCrew]) VALUES (3, 4)
INSERT [dbo].[spaceship_crews] ([idSpaceship], [idCrew]) VALUES (1, 5)
INSERT [dbo].[spaceship_crews] ([idSpaceship], [idCrew]) VALUES (2, 8)

-------------------------------
-- Table spaceship_crews_aud --
-------------------------------
INSERT [dbo].[spaceship_crews_aud] ([idSpaceship], [idCrew], [rev], [revtype]) VALUES (1, 1, 7, 0)
INSERT [dbo].[spaceship_crews_aud] ([idSpaceship], [idCrew], [rev], [revtype]) VALUES (1, 5, 7, 0)
INSERT [dbo].[spaceship_crews_aud] ([idSpaceship], [idCrew], [rev], [revtype]) VALUES (2, 8, 7, 0)
INSERT [dbo].[spaceship_crews_aud] ([idSpaceship], [idCrew], [rev], [revtype]) VALUES (3, 3, 7, 0)
INSERT [dbo].[spaceship_crews_aud] ([idSpaceship], [idCrew], [rev], [revtype]) VALUES (3, 4, 7, 0)

-----------------------------
-- Table spaceship_engines --
-----------------------------
SET IDENTITY_INSERT [dbo].[spaceship_engines] ON 

INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (1, 1, 1)
INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (2, 1, 2)
INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (3, 2, 1)
INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (4, 2, 2)
INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (5, 3, 1)
INSERT [dbo].[spaceship_engines] ([id], [idSpaceship], [idEngine]) VALUES (6, 3, 4)

SET IDENTITY_INSERT [dbo].[spaceship_engines] OFF

---------------------------------
-- Table spaceship_engines_aud --
---------------------------------
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 1, 1)
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 1, 2)
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 2, 2)
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 2, 1)
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 3, 4)
INSERT [dbo].[spaceship_engines_aud] ([rev], [revtype], [idSpaceship], [idEngine]) VALUES (7, 0, 3, 1)

-----------------------------
-- Table spaceship_modules --
-----------------------------
SET IDENTITY_INSERT [dbo].[spaceship_modules] ON 

INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (1, 1, 1)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (2, 1, 2)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (3, 2, 1)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (4, 2, 2)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (5, 3, 3)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (6, 3, 4)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (7, 3, 5)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (8, 3, 6)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (9, 3, 7)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (10, 3, 8)
INSERT [dbo].[spaceship_modules] ([id], [idSpaceship], [idModule]) VALUES (11, 3, 9)

SET IDENTITY_INSERT [dbo].[spaceship_modules] OFF

---------------------------------
-- Table spaceship_modules_aud --
---------------------------------
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 1, 2)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 1, 1)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 2, 2)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 2, 1)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 3)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 8)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 5)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 6)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 9)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 4)
INSERT [dbo].[spaceship_modules_aud] ([rev], [revtype], [idSpaceship], [idModule]) VALUES (7, 0, 3, 7)

-------------------
-- Table revinfo --
-------------------
SET IDENTITY_INSERT [dbo].[revinfo] ON 

INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (1, 1539536039911)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (2, 1539536064010)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (3, 1539536079433)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (4, 1539536095634)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (5, 1539536142964)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (6, 1539536171467)
INSERT [dbo].[revinfo] ([rev], [revtstmp]) VALUES (7, 1539536244305)

SET IDENTITY_INSERT [dbo].[revinfo] OFF
