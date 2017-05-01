use master
create database HotelManagement
go
use HotelManagement
go


create table RoomType
(
	RoomTypeID int identity(1,1) primary key,
	RoomTypeName varchar(50) not null unique,
	RoomRate money,
	Note varchar(250),
	NumOfChild int,
	NumOfAdult int
)
go

insert into roomtype values ('Vip', 1100000, 'The Hotel Hesperia is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. ', 2,2)
insert into roomtype values ('Luxury',1000000, 'The hotel is arranged on three floors, without a lift. On the ground floor, apart from the reception, there is a comfortable lounge where you can sit and drink tea, or just read. ', 2,3)
insert into roomtype values ('Normal', 500000, 'The Hotel Hesperia is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. It is a small,', 1,2)
insert into roomtype values ('Standard', 2000000, 'On the ground floor, apart from the reception, there is a comfortable lounge where you can sit and drink tea, or just read. There is also a splendid terrace, ', 2,2)

go


create table Rooms
(
	RoomID int identity(1,1) primary key,
	RoomName varchar(50) not null unique,
	RoomTypeID int constraint FK_RoomType foreign key references RoomType(RoomTypeID),
	RoomStatus varchar(50),
	Image1 varchar(200),
	Image2 varchar(200),
	Image3 varchar(200)
)

go
insert into Rooms values ('Room 1', 1, 'Available', 'Room 1_roomdon1.jpg', 'Room 1_roomdon2.jpg', 'Room 1_roomdon3.jpg')
insert into Rooms values ('Room 2', 2, 'Available', 'Room 2_roomdon4.jpg', 'Room 2_roomdon5.jpg', 'Room 2_roomdon6.jpg')
insert into Rooms values ('Room 3', 3, 'Available',  'Room 3_roomdon7.jpg', 'Room 3_roomdon8.jpg', 'Room 3_roomdon9.jpg')
insert into Rooms values ('Room 4', 4, 'Available', 'Room 4_roomdon10.jpg', 'Room 4_roomdon11.jpg', 'Room 4_roomdon12.jpg')
insert into Rooms values ('Room 5', 1, 'Available', 'Room 5_roomdon13.jpg', 'Room 5 _roomdon14.jpg', 'Room 5_roomdon15.jpg')
insert into Rooms values ('Room 6', 2, 'Available', 'Room 6_roomdon16.jpg', 'Room 6_roomdon17.jpg', 'Room 6_roomdon18.jpg')
insert into Rooms values ('Room 7', 3, 'Available', 'Room 7_roomdon19.jpg', 'Room 7_roomdon20.jpg', 'Room 7_roomdon1.jpg')
insert into Rooms values ('Room 8', 4, 'Available', 'Room 8_roomdon1.jpg', 'Room 8_roomdon2.jpg', 'Room 8_roomdon3.jpg')
insert into Rooms values ('Room 9', 1, 'Available', 'Room 9_roomdon4.jpg', 'Room 9_roomdon5.jpg', 'Room 9_roomdon6.jpg')
insert into Rooms values ('Room 10', 2, 'Available', 'Room 10_roomdon7.jpg', 'Room 10_roomdon8.jpg', 'Room 10_roomdon9.jpg')
insert into Rooms values ('Room 11', 3, 'Available', 'Room 11_roomdon10.jpg', 'Room 11_roomdon11.jpg', 'Room 10_roomdon12.jpg')
insert into Rooms values ('Room 12', 4, 'Available', 'Room 12_roomdon13.jpg', 'Room 12_roomdon13.jpg', 'Room 12_roomdon14.jpg')
insert into Rooms values ('Room 13', 1, 'Available', 'Room 13_roomdon15.jpg', 'Room 13_roomdon16.jpg', 'Room 13_roomdon17.jpg')
insert into Rooms values ('Room 14', 2, 'Available', 'Room 14_roomdon18.jpg', 'Room 14_roomdon18.jpg', 'Room 14_roomdon19.jpg')
insert into Rooms values ('Room 15', 3, 'Available', 'Room 15_roomdon1.jpg', 'Room 15_roomdon2.jpg', 'Room 15_roomdon3.jpg')
insert into Rooms values ('Room 16', 4, 'Available', 'Room 16_roomdon4.jpg', 'Room 16_roomdon5.jpg', 'Room 16_roomdon6.jpg')
insert into Rooms values ('Room 17', 1, 'Available', 'Room 17_roomdon7.jpg', 'Room 17_roomdon8.jpg', 'Room 17_roomdon9.jpg')
insert into Rooms values ('Room 18', 2, 'Available', 'Room 18_roomdon10.jpg', 'Room 18_roomdon11.jpg', 'Room 18_roomdon12.jpg')
insert into Rooms values ('Room 19', 3, 'Available', 'Room 19_roomdon13.jpg', 'Room 19_roomdon14.jpg', 'Room 19_roomdon15.jpg')
insert into Rooms values ('Room 20', 4, 'Available', 'Room 20_roomdon1.jpg', 'Room 20_roomdon2.jpg', 'Room 20_roomdon3.jpg')

go

create table Employee
(
	EmployeeID int identity(1,1) primary key,
	UserName  varchar(50) not null,
	[PassWord] varchar(50) not null,
	DOB datetime not null,
	Gender varchar(10) not null,
	Email varchar(50),
	[Role] varchar(10) not null
)

go

insert into Employee values('laivantrach','admin',12-02-1991,'Male','trach@gmail.com', 'A')
insert into Employee values('nguyenvanhung','admin',14-06-1992,'Male','hung@gmail.com', 'E')
insert into Employee values('nguyenngocnghia','admin',16-06-1985,'Male','nghia@gmail.com', 'E')
insert into Employee values('lephamanhvu','admin',12-05-1997,'Male','vu@gmail.com', 'E')


go

create table Customer
(
	CustomerID int identity(1,1) primary key,
	[Person Identifier] varchar(50) not null,
	DOB datetime not null,
	FullName nvarchar(50),
	Gender varchar(10) not null,
	Company varchar(50),
	[Address] varchar(100),
	[Status] varchar(10) not null,
	Phone varchar(20) not null,
	Email varchar(50) not null,
	[PassWord] varchar(50) not null,
)
go
  insert into Customer 
values
('250796555',17-11-1990,'Lai Van Trach', 'M','Neilsen','Ha huy giap -Q12-HCM','Y','01283477050','trach@mail.com','123456'),
('250796576',17-11-1990,'Nguyen Van Hung', 'M','Neilsen','Ha huy giap -Q12-HCM','Y','01283477055','hung@mail.com','123456'),
('250796554',17-11-1990,'Nguyen Ngoc Nghia', 'M','Neilsen','Ha huy giap -Q12-HCM','Y','01283477053','nghia@mail.com','123456'),
('250796569',17-11-1990,'Le Pham Anh Vu', 'M','Neilsen','Ha huy giap -Q12-HCM','Y','01283477052','vu@mail.com','123456')

go

create  table Reservation
(
	ReservationID int identity(1,1) primary key,
	CustomerID int not null constraint FK_Reserva foreign key 
												references Customer(CustomerID),
	StartDate datetime not null,
	EndDate datetime not null,
	NumberOfPeople int not null,
	Paid money,
	[Status] varchar(10),
	RoomID int not null constraint FK_Reser_Cus foreign key 
												references Rooms(RoomID)
)
go

insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(2,'2017-03-03 00:00:00.000','2017-04-01 00:00:00.000',1,100,'Check In',1)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(1,'2017-03-23 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',2)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(3,'2017-02-13 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',3)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(4,'2017-02-10 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',4)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(4,'2017-01-10 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',5)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(1,'2017-01-22 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',6)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(2,'2017-01-22 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',7)
insert into Reservation(CustomerID,StartDate,EndDate,NumberOfPeople,Paid,Status,RoomID) 
values(3,'2017-01-22 00:00:00.000','2017-04-01 00:00:00.000',1,0,'Check In',8)


go

create table ReservationDetail
(
	RoomID int not null constraint FK_Room foreign key 
												references Rooms(RoomID),
	ReservationID int not null constraint FK_Res_Room foreign key 
												references Reservation(ReservationID),
	[Date] datetime,
	Rate money,
	primary key (RoomID, ReservationId)
)
go
insert into ReservationDetail values(1, 1, '2017-03-03 00:00:00.000', 1000)
insert into ReservationDetail values(2, 2, '2017-03-03 00:00:00.000', 4000)
insert into ReservationDetail values(3, 3, '2017-02-13 00:00:00.000', 1000)
insert into ReservationDetail values(4, 4, '2017-02-13 00:00:00.000', 4000)
insert into ReservationDetail values(5, 3, '2017-01-10 00:00:00.000', 1000)
insert into ReservationDetail values(6, 6, '2017-01-10 00:00:00.000', 4000)
insert into ReservationDetail values(7, 7, '2016-04-03 00:00:00.000', 1000)
insert into ReservationDetail values(8, 8, '2017-01-10 00:00:00.000', 4000)

go
create  table [Services]
(
	ServiceID int identity(1,1) primary key,
	ServiceName varchar(50) not null unique,
	Price money
)
go
insert into Services values('Water', 8000)
insert into Services values('Massage', 15000)
insert into Services values('Iron', 5000)
insert into Services values('Washing', 10000)

go


create table ServiceDetail
(
	ServiceDetailID int identity(1,1) primary key,
	ReservationID int not null constraint FK_Res_service foreign key 
												references Reservation(ReservationID),
	ServiceName varchar(50) not null constraint FK_Ser foreign key 
												references [Services](ServiceName),
	Price money,
	
	[DateOfUse] datetime,
)
go
insert into ServiceDetail values(6,'Water', 8000, '2016-04-03 00:00:00.000')
insert into ServiceDetail values(7,'Massage', 15000, '2016-04-03 00:00:00.000')
insert into ServiceDetail values(3,'Iron', 5000, '2016-04-03 00:00:00.000')
insert into ServiceDetail values(4,'Washing', 10000, '2016-04-03 00:00:00.000')
go
create  table Bill 
(
	BillID int identity(1,1) primary key,
	ReservationID int not null constraint FK_Res_bill foreign key 
												references Reservation(ReservationID),
	PaymentDate datetime,
	Rent money,
	[Service] money,
	Amount money,
	Fullname varchar(50)

)
go

go
create table Feedback
(
	FeedbackID int identity(1,1) primary key,
	CustomerID int constraint FK_CusFeed foreign key 
												references Customer(CustomerID),
	EmployeeID int constraint FK_EmpFeed foreign key 
												references Employee(EmployeeID),
	DateReceive datetime,
	DateFeedback datetime,
	Question varchar(50),
	Feedback varchar(50)	
)

create table News
(
	NewsID int identity(1,1) primary key,
	NewsTitle varchar(50),
	ImageUri varchar(50),
	DatePost datetime,
	EmployeeID int not null constraint FK_EmpNews foreign key 
												references Employee(EmployeeID),
)


