USE `CMS`;

INSERT INTO `User`(firstName, lastName, username, userEmail, `password`,bio, isAdmin,enabled)
Values
('empty','empty','admin','admin@mail.com','$2a$10$Qew/yJcsBYadbBSlXMeMw.dHzfe6RpTLqGRi.d2kCWviu6Jc4wjx6','the admin',1,1),
('empty','empty','user','user@mail.com','$2a$10$Qew/yJcsBYadbBSlXMeMw.dHzfe6RpTLqGRi.d2kCWviu6Jc4wjx6','the user',0,1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');

INSERT INTO `Categories` (categoryName, categoryDescription)
Values
('Frisbee', 'Ultimate Frisbee Life'),
('Cats', 'I <3 Cats'),
('Exercise', 'Hottest Workouts'),
('Dogs', 'I <3 Dogs'),
('TV', 'My fave shows'),
('Movies', 'My fave movies'),
('Golf', 'Best Courses'),
('Fishing', 'Best Bait'),
('Tennis', 'Best Courts'),
('Baseball', 'Fave Players'),
('Ice Cream', 'Fave Flavors'),
('Choclate', 'Best Brands'),
('Video Games', 'Favorite Games'),
('Java', 'Spring MVC');

INSERT INTO `Tag` (tagName, tagDescription)
Values
('#Frisbee', 'Ultimate Frisbee Life'),
('#Cats', 'I <3 Cats'),
('#Exercise', 'Hottest Workouts'),
('#Dogs', 'I <3 Dogs'),
('#TV', 'My fave shows'),
('#Movies', 'My fave movies'),
('#Golf', 'Best Courses'),
('#Fishing', 'Best Bait'),
('#Tennis', 'Best Courts'),
('#Baseball', 'Fave Players'),
('#Ice Cream', 'Fave Flavors'),
('#Choclate', 'Best Brands'),
('#Video Games', 'Favorite Games'),
('#Java', 'Spring MVC');

INSERT INTO `BlogPost` (title, description, content, author, createdDate,publishDate, `expirationDate`, approved, idUser,idCategories)
VALUES 
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',True,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',FALSE,'1', '1'),
('Day at a Cafe', 'Coding with Rich', 'blah blah blah', 'Jose and Rich', '2018-04-20 12:30:30', '2018-04-20 12:30:30', '2018-04-20 12:30:30',FALSE,'1', '1');



INSERT INTO `StaticPage`( title, description, content, author, dateCreated, publishedDate, expirationDate, isActive, idUser)
VALUES
('I Love Cats', 'express my love of cats', 'I love cats alot', 'CatLady0320', '2018-04-20 12:30:30', '2018-03-20 12:30:30', '2018-03-10 12:30:30', TRUE, '1'),
('How to Code HTML', 'beg html', '<h1></h1> eample of tage', 'Coder123', '2018-03-20 12:30:30', '2018-04-20 12:30:30', '2018-04-10 12:30:30', TRUE, '1'),
('How to Code Java', 'beg java', 'what is a method?', 'Coder567', '2018-03-10 11:30:30', '2018-04-11 12:30:30', '2018-04-10 12:30:30', TRUE, '1'),
('How to Code C++', 'beg c++', 'cout<<"Hello World;', 'Coder567', '2018-03-10 11:30:30', '2018-04-11 12:30:30', '2018-04-10 12:30:30', TRUE, '1'),
('How to Code JavaScript', 'beg javaScript', 'console.log();', 'Coder567', '2018-03-10 11:30:30', '2018-04-11 12:30:30', '2018-04-10 12:30:30', FALSE, '1'),
('How to Code CSS', 'beg css', 'h1 { color: red }', 'Coder567', '2018-03-10 11:30:30', '2018-04-11 12:30:30', '2018-04-10 12:30:30', FALSE, '1'),
('How to Make a Cake', 'chocloate cake', 'recipe', 'Baker123', '2018-01-10 11:30:30', '2018-01-01 12:30:30', '2018-04-01 12:30:30', FALSE, '1');

INSERT INTO `BlogpostTag` (idBlogPost, idTag)
VALUES
('1','1'),
('2', '1'),
('3','2'),
('4','3'),
('5','3'),
('6','5'),
('7','4'),
('8','6'),
('9','7'),
('10','8'),
('1','9'),
('2','10'),
('1','11'),
('2','13');