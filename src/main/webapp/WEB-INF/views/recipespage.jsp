<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="_header.jsp"/>

          <div class="search">
                          <form:form modelAttribute="recipeFilterForm" action="/cookbook/recipes/filter">
                             <div class="small-3 columns">
                                <form:label path="searchText">Search</form:label>
                                <form:input type="text" path="searchText"/>
                             </div>

                             <div class="small-5 columns end">
                                 <form:checkbox path="constraints" value="name"/>name
                                 <form:checkbox path="constraints" value="category"/>category
                                 <form:checkbox path="constraints" value="ingredient"/>ingredient
                                 <form:checkbox path="constraints" value="uploader"/>uploader
                             </div>
                          </form:form>
                    </div>

          <div class="card">
            <div class="card-header">
              Recipes
            </div>
            <div class="card-body">
              <div>
                <table class="table">
                    <thead class="thead">
                        <tr>
                            <th class="col-lg-1" scope="col">Name</th>
                            <th class="col-lg-5" scope="col">Categories</th>
                            <th class="col-lg-1" scope="col">Servings</th>
                            <th class="col-lg-1" scope="col">Uploader</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${recipes}" var="recipe">
                        <tr>
                            <td><a href="/cookbook/recipe/${recipe.id}">${recipe.name}</a></td>
                            <td>${recipe.categories}</td>
                            <td>${recipe.servings}</td>
                            <td>${recipe.uploader.username}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </div>
          </div>
    </body>
</html>