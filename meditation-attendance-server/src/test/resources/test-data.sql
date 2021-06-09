/****** Script for SelectTopNRows command from SSMS  ******/


Insert into [User] (id, emailAddress, firstName, lastName, [password], userName) values (101, 'pgyawali@miu.edu', 'Prabhat', 'Gyawali', '$2a$10$dkcdY0BZu.A/ldw0XPMTR.65tCxNz3iQmwGjNGBYuSagWAWNhnGAS', 'gyawaliprabhat');
Insert into [User] (id, emailAddress, firstName, lastName, [password], userName) values (102, 'henock@miu.edu', 'Henock', 'Hailu', '$2a$10$dkcdY0BZu.A/ldw0XPMTR.65tCxNz3iQmwGjNGBYuSagWAWNhnGAS', 'john');
Insert into [User] (id, emailAddress, firstName, lastName, [password], userName) values (103, 'aderson@miu.edu', 'Aderson', 'Arias', '$2a$10$dkcdY0BZu.A/ldw0XPMTR.65tCxNz3iQmwGjNGBYuSagWAWNhnGAS', 'aderson');
Insert into [User] (id, emailAddress, firstName, lastName, [password], userName) values (104, 'hoang@miu.edu', 'hoang', 'Viet', '$2a$10$dkcdY0BZu.A/ldw0XPMTR.65tCxNz3iQmwGjNGBYuSagWAWNhnGAS', 'viet');
Insert into [User] (id, emailAddress, firstName, lastName, [password], userName) values (111, 'marcelo@miu.edu', 'Marcelo', 'Sotomaior', '$2a$10$dkcdY0BZu.A/ldw0XPMTR.65tCxNz3iQmwGjNGBYuSagWAWNhnGAS', 'faculty@miu.edu');

Insert into Role values(1, 'ADMIN',102);
Insert into Role values(2, 'PERSONNEL',102);
Insert into Role values(3, 'PERSONNEL',101);
Insert into Role values(4, 'FACULTY',103);

Insert into Faculty(title, id) values ('title goes here', 111);

Insert into Student (id, entryDateTime, status, barcode,studentId) values (101, '2021-02-01','F1', 611941, '000-61-1941');
Insert into Student (id, entryDateTime, status, barcode,studentId) values (102, '2021-05-01','F1', 611942, '000-61-1942');
Insert into Student (id, entryDateTime, status, barcode,studentId) values (103, '2021-02-01','F1', 611943, '000-61-1943');
Insert into Student (id, entryDateTime, status, barcode,studentId) values(104, '2021-02-01','F1', 611944, '000-61-1944');

Insert into Course (id, courseNumber, description, name) values (11, 'CS545', 'Enterprise Architecture', 'EA');
Insert into Course (id, courseNumber, description, name) values (12, 'CS435', 'Algorithm', 'Algorithm');
Insert into Course (id, courseNumber, description, name) values (13, 'CS472', 'Web Application Programming', 'WAP');
Insert into Course (id, courseNumber, description, name) values (14, 'CS401', 'Modern Programming Practice', 'MPP');

Insert into CourseOffering(id, endDate, startDate, course_id, faculty_id) values (1,  '2021-03-01', '2021-04-01', 14, 111);--offering mpp
Insert into CourseOffering(id, endDate, startDate, course_id, faculty_id) values (2,  '2021-04-01', '2021-05-01', 12, 111); -- offering algorithm
Insert into CourseOffering(id, endDate, startDate, course_id, faculty_id) values (3,  '2021-05-10', '2021-06-10', 11, 111);--offering ea
Insert into CourseOffering(id, endDate, startDate, course_id, faculty_id) values (4,  '2021-04-01', '2021-05-01', 13, 111);--offering wap

insert into Location(locationId, buildingName, description, roomName) values( 1, 'VERILL', 'verill hall', 'V45');
insert into Location(locationId, buildingName, description, roomName) values( 2, 'VERILL', 'verill hall', 'V47');
insert into Location(locationId, buildingName, description, roomName) values( 3, 'Dalby', 'Dalby Hall Inside Argiro', 'Hall');
insert into Location(locationId, buildingName, description, roomName) values( 4, 'Derier', 'Down floor hall', '101');

insert into TimeSlot(id, code, endTime, startTime) values (1 , 'AM', '10:00', '12:00');
insert into TimeSlot(id, code, endTime, startTime) values (2 , 'PM', '2:00', '4:00');

insert into Session(sessionId, date, offering_id, location_id, timeslot_id) values ( 1, '2021-06-07', 3, 1, 1);
insert into Session(sessionId, date, offering_id, location_id, timeslot_id) values ( 2, '2021-06-07', 3, 1, 2);
insert into Session(sessionId, date, offering_id, location_id, timeslot_id) values ( 3, '2021-06-08', 3, 1, 1);
insert into Session(sessionId, date, offering_id, location_id, timeslot_id) values ( 4, '2021-06-08', 3, 1, 2);

insert into Attendance(id, barCode, session_id, student_id, timeStamp) values(1, 611941, 3, 101, '2021-06-08T10:00:00');--expected

insert into Attendance(id, barCode, session_id, student_id, timeStamp) values(3, 611942, 3, 102, '2021-06-08T10:00:00');
insert into Attendance(id, barCode, session_id, student_id, timeStamp) values(4, 611942, 4, 102, '2021-06-08T10:00:00');

Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(1, '2021-02-15', 1, 101);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(2, '2021-02-15', 1, 102);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(3, '2021-02-15', 1, 103);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(4, '2021-02-15', 1, 104);

Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(5, '2021-02-15', 2, 101);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(6, '2021-02-15', 2, 102);

Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(7, '2021-02-15', 4, 103);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(8, '2021-02-15', 4, 104);


Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(9, '2021-02-15', 3, 101);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(10, '2021-02-15', 3, 102);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(11, '2021-02-15', 3, 103);
Insert into CourseRegistration( id, registrationDateTime, offering_id, student_id)  values(12, '2021-02-15', 3, 104);

--delete from Attendance;









