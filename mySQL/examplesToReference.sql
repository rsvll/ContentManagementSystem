Select * from User usr
	
    Left Join  BlogPost bp on usr.idUser = bp.idUser where
    
    idBlogPost = 1;
    
    Use `CMS`;



select * from Categories;

UPDATE `CMS`.`StaticPage`
Set
`isActive`= 0 
WHERE 
idStaticPage = 7;

Select * from StaticPage;

use cms;

select * from BlogpostTag where idBlogPost = 16;

Use cms;

Select tg.`idTag`, tg.`tagName`, tg.`tagDescription` from Tag tg
	
    Join  BlogpostTag bpt on tg.idTag = bpt.idTag where
    
    idBlogPost = 1;
