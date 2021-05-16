<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="_header.jsp"/>

          <div class="card">
            <div class="card-header">
              My recipes
            </div>
            <div class="card-body">
                <table class="table">
                    <thead class="thead">
                        <tr>
                            <th class="col-lg-1" scope="col">Name</th>
                            <th class="col-lg-1" scope="col">Servings</th>
                            <th class="col-lg-1" scope="col">Uploader</th>
                            <th class="col-lg-1" scope="col"></th>
                            
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${recipes}" var="recipe">
                        <tr>
                            <td><a href="/cookbook/recipe/${recipe.id}">${recipe.name}</a></td>
                            <td>${recipe.servings}</td>
                            <td>${recipe.uploader.username}</td>
                            <td><a href="/cookbook/recipe/delete/${recipe.id}"><button class="btn btn-primary" type="submit">Delete</button></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

          </div>
    </body>
</html>