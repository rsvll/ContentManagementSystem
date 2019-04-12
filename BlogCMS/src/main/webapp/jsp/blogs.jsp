<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blogs</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">     
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/eule2.png" type="image/x-icon">
    </head>
    <body>
        <h1>All Blogs</h1>
        <sec:authorize access="isAnonymous()">
            <a href="${pageContext.request.contextPath}/login" class="">Login</a>
        </sec:authorize>
        
        <div class="nav">
          <ul>
            <li><a href="${pageContext.request.contextPath}/index">Home </a></li>
            <li><a href="${pageContext.request.contextPath}/blogs"> Blogs </a></li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="${pageContext.request.contextPath}/unapprovedBlogs"> Need Approval </a></li>
                <li><a href="${pageContext.request.contextPath}/categories"> Categories </a></li>
                <li><a href="${pageContext.request.contextPath}/users"> Users </a></li>
                <li><a href="${pageContext.request.contextPath}/tags"> Tags</a></li>
            </sec:authorize>
            <li><a href="${pageContext.request.contextPath}/viewAllStaticPages"> Other Pages</a></li>
            <!-- <li><a>Static Pages</a></li> STATIC PAGES UP FOR DISCUSSION-->
          </ul>
        </div>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
                |<a href="${pageContext.request.contextPath}/displayUserProfile?viewType=edit&username=${pageContext.request.userPrincipal.name}" /> Edit</a> |<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> 
            </p>
        </c:if>
  <!-- ONLY ADDING A TAGS FOR PURPOSE OF MAYBE LINKING TO OTHER BLOGS-->
        <div class="container">
            <div class="row">
                <div class="col-md-12 ">
                    <table class="table">
                        <thead>
                            <tr>
                                <th> Title </th>
                                <th> Description </th>
                                <th> Author </th>
                                <th> Publish Date </th>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <th> EDIT</th>
                                    <th> DELETE </th>
                                </sec:authorize>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" begin="0" end="${allBlogs.size()-1}">
                                <tr >
                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayBlog/${allBlogs[i].id}">${allBlogs[i].title}</a>
                                    </td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayBlog/${allBlogs[i].id}">${allBlogs[i].description}</a>
                                    </td>
                                    
                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayBlog/${allBlogs[i].id}">${allBlogs[i].author}</a>
                                    </td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/displayBlog/${allBlogs[i].id}">${allBlogs[i].publishDate}</a>
                                    </td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                
                                            <td>
                                                <a href="${pageContext.request.contextPath}/chooseBlogPostToUpdate?blogId=${allBlogs[i].id}" class = "btn btn-warning">EDIT</a>
                                            </td>

                                            <td>
                                                <a href="${pageContext.request.contextPath}/deleteBlogPost?blogId=${allBlogs[i].id}" class = "btn btn-danger">DELETE</a>
                                            </td>
                                    </sec:authorize>
                                </tr>

                            </c:forEach>
                            <sec:authorize access= "isAuthenticated()">
                                <td>
<!--                                    //<button class = "btn-success">CREATE POST</button>-->
                                        <a href="${pageContext.request.contextPath}/displayCreateBlogPostPage?username=${pageContext.request.userPrincipal.name}" class = "btn btn-success">CREATE</a>
                                    
                                </td>
                            </sec:authorize>
                        </tbody>
                    </table>
    
                </div>
                
                
                

            </div>
            
            <div class="footer">
                <button>Instagram</button>
                <button>FaceBook</button>
                <button>Twitter</button>
                <button>YouTube</button>
            </div>
            
        </div>
        
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>

    </body>
</html>
