use yingjianren;

drop table if exists user;

create table user{
    u_id int(10) PRIMARY KEY AUTO_INCREMENT,
    u_name nvarchar(20) not null UNIQUE,
    u_psd nvarchar(20) not null,
    u_email nvarchar(20) not null,
    u_is_login boolean DEFAULT 0,
    u_is_v boolean DEFAULT 0
};