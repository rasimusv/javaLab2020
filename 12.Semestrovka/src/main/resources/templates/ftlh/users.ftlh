<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="<@spring.message 'common.lang'/>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/static/css/main.css">

    <title><@spring.message 'users.title'/></title>
</head>
<body>
<div class="container-xxl">
    <div class="row">
        <div class="col-md-11">
            <table class="table table-striped table-bordered">
                <th><@spring.message 'common.username_word'/></th>
                <th><@spring.message 'common.first_name_word'/></th>
                <th><@spring.message 'common.last_name_word'/></th>
                <th><@spring.message 'common.email_word'/></th>
                <#list usersList as user>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </#list>
            </table>
        </div>
        <div class="col-md-1">
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