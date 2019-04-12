<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Categories</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">     
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/eule2.png" type="image/x-icon">
    </head>
    <body>
        <h1>Categories</h1>
        
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
                <div class="col-md-8 ">
                    <table class="table">
                        <thead>
                            <tr>
                                <th> Category Name </th>
                                <th> Description </th>
                                <th> EDIT</th>
                                <th> DELETE</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" begin="0" end="${allCategories.size() - 1}">
                                <tr >
                                    <td>
                                        <c:out value="${allCategories[i].catName}"></c:out>
                                    </td>

                                    <td>
                                        <c:out value="${allCategories[i].description}"></c:out>
                                    </td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/chooseCategoryToUpdate?viewType=edit&categoryId=${allCategories[i].catId}" class = "btn btn-warning">EDIT</a>
                                    </td>

                                    <td>
                                        <a href="${pageContext.request.contextPath}/deleteCategory?categoryId=${allCategories[i].catId}" class = "btn btn-danger">DELETE</a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                        
                </div>
                <div class="col-md-4 body">
                    <c:choose>
                                <c:when test="${viewType == 'edit'}">
                                    <h2>Update Category </h2>
                                    <hr>
                                    <form action="updateCategory"  class="form-horizontal" role="form" method="GET" >
                                        <input type="hidden" name="categoryId" value="${catID}" />
                                        <div class="form-group">
                                            <label for="add-category-name" class=" control-label">Category </label>
                                            <div class="    ">
                                                <input type="text" name="categoryName" placeholder="Category Name : ${cat.catName}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="add-category-description" class=" control-label">Description:</label>
                                            <div class="">
                                                <textarea  class="form-control" rows="3" name="categoryDescription" placeholder="Category Description : ${cat.description}"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-10">
                                                <button type="submit" class="btn btn-success" value="${catID}">Submit</button>
                                            </div>
                                        </div>
                                    </form>
                                    
                                </c:when>
                                <c:otherwise>
                                    <h2>Add New Category </h2>
                                    <hr>
                                    <form action="createCategory" class="form-horizontal" role="form" method="POST" >
                                        <div class="form-group">
                                            <label for="add-category-name" class=" control-label">Category: </label>
                                            <div class="">
                                                <input type="text" name="categoryName" placeholder="Category Name : ">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="add-category-description" class=" control-label">Description:</label>
                                            <div class="">
                                                    <textarea  class="form-control" rows="3" name="categoryDescription" placeholder="Category Description : "></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-10">
                                                <button type="submit" class="btn btn-success" value="${catID}">Submit</button>
                                            </div>
                                        </div>
                                    </form>

                                </c:otherwise>
                    </c:choose>
                        
                </div> <!-- End of column -->
                
                
                

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

    </body>
</html>
