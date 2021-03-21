<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>

<#assign username_word><@spring.message 'common.username_word'/></#assign>
<#assign password_word><@spring.message 'common.password_word'/></#assign>

<!doctype html>
<html lang="<@spring.message 'common.lang'/>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/static/css/main.css">

    <title><@spring.message 'login.title'/></title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <@spring.bind "logInForm"/>
            <form method="post" action="/login" class="form-horizontal">
                <span class="heading"><@spring.message 'login.upper_case_title'/></span>
                <div class="form-group">
                    <@spring.formInput path="logInForm.username" attributes='class="form-control" placeholder="${username_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <#if credentialsErrorMessage??><span class="invalid-feedback error-text">${credentialsErrorMessage}</span></#if>
                    <i class="fa fa-at"></i>
                </div>
                <div class="form-group">
                    <@spring.formPasswordInput path="logInForm.password" attributes='class="form-control" placeholder="${password_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <#if credentialsErrorMessage??><span class="invalid-feedback error-text">${credentialsErrorMessage}</span></#if>
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <div class="col-lg-1">
                        <a class="text" href="/signup"><@spring.message 'signup.title'/></a>
                    </div>
                    <button type="submit" class="btn btn-default"><@spring.message 'login.upper_case_confirm_sign_in_word'/></button>
                </div>
            </form>
        </div>
        <div class="col-md-offset-2 col-md-1">
            <a class="dropdown">
                <a class="fa fa-globe loc-button dropdown-toggle btn btn-default" href="#" id="language" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
                    <li><a class="dropdown-item" href="?lang=en">English</a></li>
                </ul>
            </a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>