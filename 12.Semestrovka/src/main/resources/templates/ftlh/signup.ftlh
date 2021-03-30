<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>

<#assign username_word><@spring.message 'common.username_word'/></#assign>
<#assign first_name_word><@spring.message 'common.first_name_word'/></#assign>
<#assign last_name_word><@spring.message 'common.last_name_word'/></#assign>
<#assign email_word><@spring.message 'common.email_word'/></#assign>
<#assign password_word><@spring.message 'common.password_word'/></#assign>
<#assign repeat_password_word><@spring.message 'common.repeat_password_word'/></#assign>

<!doctype html>
<html lang="<@spring.message 'common.lang'/> ">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/static/css/main.css">

    <title><@spring.message 'signup.title'/></title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <@spring.bind "signUpForm"/>
            <form method="post" action="/signup" class="form-horizontal">
                <span class="heading"><@spring.message 'signup.upper_case_title'/></span>
                <div class="form-group">
                    <@spring.formInput path="signUpForm.username" attributes='class="form-control" placeholder="${username_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <i class="fa fa-at"></i>
                </div>
                <div class="form-group">
                    <@spring.formInput path="signUpForm.firstName" attributes='class="form-control" placeholder="${first_name_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <@spring.formInput path="signUpForm.lastName" attributes='class="form-control" placeholder="${last_name_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <#if namesErrorMessage??><span class="invalid-feedback error-text">${namesErrorMessage}</span></#if>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <@spring.formInput path="signUpForm.email" attributes='class="form-control" placeholder="${email_word}"' fieldType="email"/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <i class="fa fa-envelope"></i>
                </div>
                <div class="form-group">
                    <@spring.formPasswordInput path="signUpForm.password" attributes='class="form-control" placeholder="${password_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <@spring.formPasswordInput path="signUpForm.repeatPassword" attributes='class="form-control" placeholder="${repeat_password_word}"'/>
                    <@spring.showErrors "" "invalid-feedback error-text"/>
                    <#if passwordMismatchErrorMessage??><span class="invalid-feedback error-text">${passwordMismatchErrorMessage}</span></#if>
                    <i class="fa fa-lock"></i>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <#if isSignedUp??>
                    <@spring.message 'signup.user_already_signed_up'/>
                </#if>
                <div class="form-group">
                    <div class="col-lg-1">
                        <a class="text" href="/login"><@spring.message 'login.title'/></a>
                    </div>
                    <button type="submit" class="btn btn-default"><@spring.message 'signup.upper_case_confirm_sign_up_word'/></button>
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