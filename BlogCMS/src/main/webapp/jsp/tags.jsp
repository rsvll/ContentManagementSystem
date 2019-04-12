<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tags</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"> 
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/eule2.png" type="image/x-icon">
    </head>
    <body>
        <h1>Tags</h1>
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
        
            <div class="container">
                <div class="row">
                     <div class="col-md-8 ">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th> Tag Name </th>
                                    <th> Description </th>
                                    <th> EDIT</th>
                                    <th> DELETE</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="i" begin="0" end="${allTags.size() - 1}">
                                    <tr >
                                        <td>
                                            <c:out value="${allTags[i].tagName}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${allTags[i].description}"></c:out>
                                        </td>

                                        <td>
                                            <a href="${pageContext.request.contextPath}/chooseTag?viewType=edit&tagId=${allTags[i].tagId}" class = "btn btn-warning">EDIT</a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/deleteTag?tagId=${allTags[i].tagId}" class = "btn btn-danger">DELETE</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>
                    <div class="col-md-4 ">
                        
                        <c:choose>
                            <c:when test="${viewType=='edit'}">
                                <h2>Update Tag </h2>
                                <hr>
                                <form action="updateTag" class="form-horizontal" role="form" method="GET"> 
                                    <input type="hidden" name="tagId" value="${tagID}" />

                                    <div class="form-group">
                                        <label for="tagName" class=" control-label">Tag Name: </label>
                                        <div class="">
                                            <input type="text" name="tagName" placeholder="Tag Name : ${tag.tagName}" >
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="tagDescription" class=" control-label">Description:</label>
                                        <div class="">
                                            <textarea class="form-control" rows="3" name="tagDescription" placeholder="Description : ${tag.description}"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="">
                                            <input type="submit" class="btn btn-success" value="Submit">
                                        </div>
                                    </div>

                                </form> 
                            </c:when>
                            <c:otherwise>
                                <h2>Add New Tag </h2>
                                <hr>
                                <form action="createTag" class="form-horizontal" role="form" method="POST"> 

                                    <div class="form-group">
                                        <label for="tagName" class=" control-label">Tag Name: </label>
                                        <div class="">
                                            <input type="text" name="tagName" placeholder="Name">
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="tagDescription" class=" control-label">Description:</label>
                                        <div class="">
                                            <textarea class="form-control" rows="3" name="tagDescription" placeholder="Description of tag"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="">
                                            <input type="submit" class="btn btn-success" value="Submit">
                                        </div>
                                    </div>

                                </form>

                            </c:otherwise>    
                        </c:choose>
                    </div> <!-- End of column -->
                </div> <!-- End of row --> 
            <div class="footer">
                <button>Instagram</button>
                <button>FaceBook</button>
                <button>Twitter</button>
                <button>YouTube</button>
            </div>
        </div>
                
                
    </body>
    
    
</html>
