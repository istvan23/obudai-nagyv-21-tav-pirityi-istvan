<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="_header.jsp"/>

          <div class="card">
            <div class="card-header">
              ${recipe.name}
            </div>
            <div class="card-body">
              <div>
                <table class="table">
                        <tr>
                          <th class="col-lg-1">Preparation</th>
                          <td>${recipe.preparation}</td>
                        </tr>
                        <tr>
                          <th>Ingredients</th>
                          <td>
                            <c:forEach items="${recipe.ingredients}" var="ingredient">
                               <p>${ingredient.amount} ${ingredient.unit} ${ingredient.name}</p>
                            </c:forEach>
                          </td>
                        </tr>
                        <tr>
                          <th>Categories</th>
                          <td>${recipe.categories}</td>
                        </tr>
                        <tr>
                          <th>Servings</th>
                          <td>${recipe.servings}</td>
                        </tr>
                        <tr>
                          <th>Uploader</th>
                          <td>${recipe.uploader.username}</td>
                        </tr>
                </table>
            </div>

            <div class="card comments">
              <div class="card-header">
                Comments
              </div>
              <div class="card-body">
                <div>
                    <table class="table">
                        <thead class="thead">
                            <tr>
                                <th class="col-lg-5" scope="col">Comment</th>
                                <th class="col-lg-1" scope="col">Time</th>
                            </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${recipe.comments}" var="comment">
                            <tr>
                                <td>${comment.description}</td>
                                <td>${comment.timestamp}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
              </div>
            </div>

            <div class="commentform">
                <form:form modelAttribute="commentObject" action="/cookbook/recipe/newComment">
                            <form:hidden path="recipeId" value="${recipe.id}"/>

                            <div class="input-group-text">
                                <form:label path="description">Comment:</form:label>
                            </div>
                            <div>
                                <form:input path="description"/>
                                <input type="submit" value="Send comment"/>
                            </div>

                </form:form>
            </div>

          </div>
    </body>
</html>