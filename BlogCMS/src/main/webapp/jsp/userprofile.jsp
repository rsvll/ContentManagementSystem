<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Profile</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"> 
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/eule2.png" type="image/x-icon">
    </head>
    <body>
        <h1>User Profile</h1>
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
                |<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> 
            </p>
        </c:if>
  <!-- ONLY ADDING A TAGS FOR PURPOSE OF MAYBE LINKING TO OTHER BLOGS-->
        <h1>${user.lastName}, ${user.firstName}</h1>
        <div class="container">
            <div class="col-md-10 ">
                    <table class="table">
                        <thead>
                            <tr>
                                <th> First Name </th>
                                <th> Last Name </th>
                                <th> Username </th>
                                <th> Email </th>
                                <th> Password </th>
                                <th> Bio </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr >
                                <td>
                                    <c:out value="${user.firstName}"></c:out>
                                </td>

                                <td>
                                    <c:out value="${user.lastName}"></c:out>
                                </td>

                                <td>
                                    <c:out value="${user.username}"></c:out>
                                </td>

                                <td>
                                    <c:out value="${user.userEmail}"></c:out>
                                </td>

                                <td>
                                    <c:out value=" Password "></c:out>
                                </td>

                                <td>
                                    <c:out value="${user.userBio}"></c:out>
                                </td>
                            </tr>

                        </tbody>

                    </table>
    
                </div>
                <div class = "col-md-10">
                    <h2>Update User </h2>
                    <hr>
                    <form action="updateUser"  class="form-horizontal" role="form" method="GET" >
                        <input type="hidden" name="userId" value="${userId}" />
                        <div class="form-group">
                            <label for="add-firstname" class="control-label">First Name: </label>
                            <div class="    ">
                                <input type="text" name="firstName" placeholder="User's First Name : ${user.firstName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-lastname" class="control-label">Last Name:</label>
                            <div class="">
                                <input type="text" name="lastName" placeholder="User's Last Name : ${user.lastName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-user-username" class="control-label">Username </label>
                            <div class="    ">
                                <input type="text" name="username" placeholder="User's Username : ${user.username}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-user-useremail" class="control-label">Email </label>
                            <div class="    ">
                                <input type="text" name="email" placeholder="User's Email : ${user.userEmail}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-user-password" class="control-label">Password </label>
                            <div class="    ">
                                <input type="text" name="password" placeholder="User's Password : Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-user-bio" class="control-label">Bio:</label>
                            <div class="">
                                <textarea  class="form-control" rows="3" name="userBio" placeholder="User Biography : ${user.userBio}"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="">
                                <button type="submit" class="btn btn-success" value="${userId}">SUBMIT</button>
                            </div>
                        </div>

                    </form>
                </div>
            
        </div>
        <div class="footer">
            <button>Instagram</button>
            <button>FaceBook</button>
            <button>Twitter</button>
            <button>YouTube</button>
        </div>
        
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
