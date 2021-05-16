<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cookbook Application</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="/css/cookbook_style.css">
        <meta charset="UTF-8">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark">
            <div class="container-fluid">
              <a class="text-white" href="#">CookBook</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link text-white" href="/cookbook/recipes">Recipes</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-white" href="/cookbook/myRecipes">My recipes</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-white" href="/cookbook/newRecipe">New recipe</a>
                  </li>
                </ul>
                <form:form class="d-flex" action="/logout" method="POST">
                  <button class="btn btn-outline-success" type="submit">Logout</button>
                </form:form>
              </div>
            </div>
          </nav>