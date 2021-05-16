<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="_header.jsp"/>

          <div class="card">
            <div class="card-header">
              New recipe
            </div>
            <div class="card-body">
              <form:form modelAttribute="newRecipeForm" action="/cookbook/recipe/newRecipe">
                <div class="col-auto">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <div>
                          <form:label class="input-group-text" path="name">Name</form:label>
                      </div>
                    </div>
                    <form:input type="text" path="name" class="form-control" id="inlineFormInputGroup" placeholder="Pea Soup"/>
                  </div>
                  <form:errors cssClass="message" path="name"/>
                </div>
                <div class="col-auto">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <div>
                          <form:label class="input-group-text" path="servings">Servings</form:label>
                      </div>
                    </div>
                    <form:input type="text" path="servings" class="form-control" id="inlineFormInputGroup" placeholder="4"/>
                  </div>
                  <form:errors cssClass="message" path="servings"/>
                </div>
                <div class="col-auto">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <div class="input-group-text">
                          <form:label path="ingredients">Ingredients</form:label>
                      </div>
                    </div>
                    <form:textarea type="text" rows="3" path="ingredients" class="form-control" id="inlineFormInputGroup" placeholder="50 milliliter cream"/>
                  </div>
                  <form:errors cssClass="message" path="ingredients"/>
                </div>
                <div class="col-auto">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <div class="input-group-text">
                          <form:label path="preparation">Preparation</form:label>
                      </div>
                    </div>
                    <form:textarea type="text" rows="3" path="preparation" class="form-control" id="inlineFormInputGroup" placeholder="1. step"/>
                  </div>
                  <form:errors cssClass="message" path="name"/>
                </div>
                <div class="col-auto">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <div class="input-group-text">Categories</div>
                    </div>
                      <form:select multiple="true" path="categories">
                          <form:option value="" label="-- select categories"/>
                          <form:options items="${categories}"/>
                      </form:select>
                  </div>
                  <form:errors cssClass="message" path="categories"/>
                </div>
                <button class="btn btn-primary" type="submit">Save</button>
              </form:form>
             </div>
          </div>
    </body>
</html>