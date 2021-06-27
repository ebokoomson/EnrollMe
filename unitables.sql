DROP TABLE Course CASCADE;
DROP TABLE Dean CASCADE;
DROP TABLE College CASCADE;
DROP TABLE Major CASCADE;
DROP TABLE Student CASCADE;
DROP TABLE Professor CASCADE;
DROP TABLE CourseSection CASCADE;
DROP TABLE Student_coursesection CASCADE;





CREATE TABLE Course (
    Course_ID   VARCHAR(5) PRIMARY KEY,
    Course_name VARCHAR(255)
);

CREATE TABLE Dean(
    Dean_ID  SERIAL PRIMARY KEY,
    Dean_name VARCHAR(255)
);

CREATE TABLE College(
    College_ID  SERIAL PRIMARY KEY,
    College_name VARCHAR(255),
    Dean_ID INT,
    FOREIGN KEY (Dean_ID) references Dean(Dean_ID)
);

CREATE TABLE Major(
    Major_ID  SERIAL PRIMARY KEY,
    Major_name VARCHAR(255),
    College_ID INT,
    FOREIGN KEY (College_ID) references College(College_ID)
);


CREATE TABLE Student (
    Student_ID  SERIAL PRIMARY KEY,
    Student_name VARCHAR(255),
    College_ID INT,
    Major_ID INT,
    FOREIGN KEY (College_ID) references College(College_ID),
    FOREIGN KEY (Major_ID) references Major(Major_ID)
);


CREATE TABLE Professor(
    Professor_ID  SERIAL PRIMARY KEY,
    Professor_Name VARCHAR(255),
    College_ID INT,
    FOREIGN KEY (College_ID) references College(College_ID)
);


CREATE TABLE CourseSection (
    CourseSection_ID   VARCHAR(9) PRIMARY KEY,
    Course_ID VARCHAR(255),
    Professor_ID INT,
    Meeting_Day VARCHAR(10),
    Start_time TIME,
    End_time TIME,

    FOREIGN KEY (Professor_ID) references Professor(Professor_ID),
    FOREIGN KEY (Course_ID) references Course(Course_ID)
);


CREATE TABLE Student_CourseSection(
    Student_ID INT,
    CourseSection_ID VARCHAR(255),
    PRIMARY KEY (Student_ID, CourseSection_ID),
    FOREIGN KEY (Student_ID) references Student(Student_ID),
    FOREIGN KEY (CourseSection_ID) references CourseSection(CourseSection_ID)
);

INSERT INTO Course VALUES ('CI101', 'Computing 1');
INSERT INTO Course VALUES ('CI102', 'Computing 2');

INSERT INTO Dean VALUES (DEFAULT,'Haris Elliot');
INSERT INTO Dean VALUES (DEFAULT,'Hailey Fowler');
INSERT INTO Dean VALUES (DEFAULT,'Kevin Owens');
INSERT INTO Dean VALUES (DEFAULT,'Aida Smith');
INSERT INTO Dean VALUES (DEFAULT,'Ashton Harper');
INSERT INTO Dean VALUES (DEFAULT,'Thomas Alexander');


INSERT INTO College VALUES (DEFAULT,'College of Arts and Sciences',1);
INSERT INTO College VALUES (DEFAULT,'College of Business',2);
INSERT INTO College VALUES (DEFAULT,'College of Computing and Informatics',3);
INSERT INTO College VALUES (DEFAULT,'College of Engineering',4);
INSERT INTO College VALUES (DEFAULT,'College of Education',5);
INSERT INTO College VALUES (DEFAULT,'College of Medicine',6);

INSERT INTO Major VALUES (DEFAULT,'Chemistry',1);
INSERT INTO Major VALUES (DEFAULT,'Marketing',2);
INSERT INTO Major VALUES (DEFAULT,'Computer Science',3);
INSERT INTO Major VALUES (DEFAULT,'Chemical Engineering',4);
INSERT INTO Major VALUES (DEFAULT,'Elementary Education',5);
INSERT INTO Major VALUES (DEFAULT,'Nursing',6);

INSERT INTO Student VALUES (DEFAULT,'Ashton Hawkins',1,1);
INSERT INTO Student VALUES (DEFAULT,'Amber Baker',2,2);
INSERT INTO Student VALUES (DEFAULT,'Carl Higgins',3,3);
INSERT INTO Student VALUES (DEFAULT,'Kate Thompson',4,4);
INSERT INTO Student VALUES (DEFAULT,'George Robinson',5,5);
INSERT INTO Student VALUES (DEFAULT,'Marcus Richards',6,6);

INSERT INTO Professor VALUES (DEFAULT,'Adele Russell',1);
INSERT INTO Professor VALUES (DEFAULT,'Spike Murphy',2);
INSERT INTO Professor VALUES (DEFAULT,'Edwin Stevens',3);
INSERT INTO Professor VALUES (DEFAULT,'David Hill',4);
INSERT INTO Professor VALUES (DEFAULT,'Samantha Brooks',5);
INSERT INTO Professor VALUES (DEFAULT,'Harold Foster',6);










