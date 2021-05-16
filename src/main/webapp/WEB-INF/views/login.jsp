<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cookbook Application</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/cookbook_style.css">
    </head>
    <body>
        <div class="jumbotron">
                <h1 class="display-4">Welcome to Cookbook Application</h1>
            </div>

            <div class="container">

                <div class="card login">
                    <div class="card-header">
                            Login
                    </div>
                        <div class="card-body">
                            <form:form action="login" method="POST">
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label text-md-right"> User Name: </label>
                                        <div class="col-sm-6"><input type="text" class="form-control" name="username"/></div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label text-md-right"> Password: </label>
                                        <div class="col-sm-6"><input type="password" class="form-control" name="password"/></div>
                                    </div>
                                    <div class="offset-sm-1 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Sign in</button>
                                      </div>
                            </form:form>
                        </div>
                </div>
            </div>
    </body>
</html>