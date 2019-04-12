<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/eule2.png" type="image/x-icon">
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=8wq1p7ezynjhqroyyo88vmd460hkdl5ihfl6rpr9z1dvmmil"></script>
        
        
        <script type="text/javascript">
            tinymce.init({
              selector: '#tinymce',
              theme: 'modern',
              plugins: [
                'advlist autolink code link image lists charmap print preview hr anchor pagebreak spellchecker',
                'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
                'save table contextmenu directionality emoticons template paste textcolor'
              ],
              toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons | code'
            });
        </script>
        <script type="text/javascript">
            tinymce.init({
              selector: '#tinymceTitle',
              inline: false
            });
        </script>
        
        
        
    </head>
    <body>
        <h1>Edit Page</h1>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="login-btn"><a href="${pageContext.request.contextPath}/login">Login</a></div>
            </c:if>
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
            
          </ul>
        </div>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name} 
                |<a href="${pageContext.request.contextPath}/displayUserProfile?viewType=edit&username=${pageContext.request.userPrincipal.name}" /> Edit</a> |<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> 
            </p>
        </c:if>
  <!-- ONLY ADDING A TAGS FOR PURPOSE OF MAYBE LINKING TO OTHER BLOGS-->
        
        <div class = "container">

            <div class = "row">
                <div class=" col-md-12">
                    <c:choose>
                        
                        <c:when test="${bp.title != null}">
                            <form action="updateBlogPost" class="form-horizontal" role="form" method="GET">
                                <input type="text" name="username" value="${pageContext.request.userPrincipal.name}" hidden>
                                <input type="text" name="bpId" value="${bp.id}" hidden>
                                <div class = "form-group">
                                    <label for="add-Title" class=" control-label">Title:</label> 
                                    <br>
                                    <div class="" style="margin-top:10px">
                                        <textarea id="tinymceTitle" name="title" >
                                            ${bp.title}
                                        </textarea>
                                    </div>
                                </div>
                                <div class = "form-group">
                                    <label for="add-Description" class=" control-label">Description: </label> 
                                    <textarea  name="description" >
                                        ${bp.description}
                                    </textarea>

                                </div>
                                
                                <div class = "form-group">
                                    <label for="add-Content" class=" control-label">Content:</label>
                                    <br>
                                    <div class="" style="margin-top:10px">
                                        <textarea id="tinymce" name="content" >
                                            ${bp.content}
                                        </textarea>
                                    </div>
                                </div>
                                <div class = " form-group row">
                                    <div class="col-md-3 dropdown">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> CATEGORIES </button>
                                        <div class="dropdown-menu radio" id="catRest">

                                        </div>
                                    </div>
                                    <div class="col-md-3 col-md-offset-6 dropdown" style="margin-bottom: 10px">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> TAGS </button>
                                        <div class="dropdown-menu" id="tagRest">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <div class="col-md-4 col-md-offset-4">
                                        <input type="submit" class="btn btn-success" value="Submit">
                                    </div>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="updateStaticPage" class="form-horizontal" role="form" method="GET">
                                <input type="text" name="username" value="${pageContext.request.userPrincipal.name}" hidden>
                                <input type="text" name="spId" value="${sp.id}" hidden>
                                <div class = "form-group">
                                    <label for="add-Title" class=" control-label">Title:</label> 
                                    <br>
                                    <div class="" style="margin-top:10px">
                                        <textarea id="tinymceTitle" name="title" >
                                            ${sp.title}
                                        </textarea>
                                    </div>
                                </div>
                                <div class = "form-group">
                                    <label for="add-Description" class=" control-label">Description: </label> 
                                    <textarea  name="description" >
                                        ${sp.description}
                                    </textarea>

                                </div>
                                <div class = "form-group">
                                    <label for="is-Active" class=" control-label">Leave Blank To Deactivate Page: </label> 
                                        <div>
                                                <input class ="" type="checkbox" name="isActive" value="yes"/> : Active?
                                        </div>
                                </div>
                                <div class = "form-group">
                                    <label for="add-Content" class=" control-label">Content:</label>
                                    <br>
                                    <div class="" style="margin-top:10px">
                                        <textarea id="tinymce" name="content" >
                                            ${sp.content}
                                        </textarea>
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
                </div>
                
            </div>
        </div>
                    
        <div class="footer">
            <button>Instagram</button>
            <button>FaceBook</button>
            <button>Twitter</button>
            <button>YouTube</button>
        </div>
            
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>

    
    </body>
</html>