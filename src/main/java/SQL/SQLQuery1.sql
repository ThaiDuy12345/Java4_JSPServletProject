create database Assignment_PS16703;
go
use Assignment_PS16703;
go
create table Users(
ID int Identity(1,1) primary key,
Password nvarchar(100),
Email nvarchar(100) unique,
FullName nvarchar(100),
Admin bit
)
create table Videos(
ID int Identity(1,1) primary key,
Title nvarchar(100),
Poster nvarchar(100),
Views int,
Description nvarchar(100),
Link nvarchar(100),
)
create table Shares(
ID int Identity(1,1) primary key,
UserID int foreign key references Users(ID),
VideoID int foreign key references Videos(ID),
Email nvarchar(100),
ShareDate nvarchar(100)
)
create table Favorites(
ID int Identity(1,1) primary key,
UserID int foreign key references Users(ID),
VideoID int foreign key references Videos(ID),
LikeDate nvarchar(100)
)
go
create proc sp_GetVideoLike(@ID int)
as
begin
	Select COUNT(*) as 'Like' from Favorites
	where VideoID = @ID;
end
go
create proc sp_GetVideoShare(@ID int)
as
begin
	Select COUNT(*) as 'Share' from Shares
	where VideoID = @ID;
end
go
create proc sp_IncreaseViews(@ID int)
as
begin
	Declare @View int = (Select Views from Videos where ID = @ID);
	Update Videos
	set Views = @View + 1
	where ID = @ID;
end
go
create proc sp_SearchAllWithOrder
as
begin
	Select * from Videos
	Order by views DESC;
end
go
create proc sp_SearchByTitle(@KeyWord nvarchar(100))
as
begin
	Select * from Videos Select * from Users
	where Title like '%'+@KeyWord+'%'; 
end
go
create proc sp_SearchAllWithFavorite(@ID int)
as
begin                           
	Select v.ID, v.Title, v.Poster, v.Views, v.Description, v.Link from Videos v, Users u, Favorites f
	where @ID = u.ID and @ID = f.UserID and v.ID = f.VideoID and u.ID = f.UserID;
end
go
create proc sp_SearchFavoriteByUserAndVideo(@UserID int, @VideoID int)
as
begin
	Select * from Favorites
	where UserID = @UserID and VideoID = @VideoID;
end
go
create proc sp_SearchUserWithEmail(@Email nvarchar(100))
as
begin
	Select * from Users
	where Email like @Email;
end

select * from Videos
select * from Users
select * from Favorites
delete from Users where id = 5;