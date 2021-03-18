<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>
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

    <title><@spring.message 'cv.title'/></title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-xxl">
        <div>
            <img src="/static/images/portrait.jpg" alt="" width="60" class="navbar-left round">
            <a class="navbar-brand navbar-left" href="#"><@spring.message 'cv.title'/></a>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#"><@spring.message 'cv.homepage_name'/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="links" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <@spring.message 'cv.links_name'/></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="https://t.me/rasimusv"><@spring.message 'cv.telegram_name'/></a></li>
                        <li><a class="dropdown-item" href="https://vk.com/rasimusv"><@spring.message 'cv.vk_name'/></a></li>
                        <li><a class="dropdown-item" href="https://twitter.com/TatarskiyDrug"><@spring.message 'cv.twitter_name'/></a></li>
                        <li><a class="dropdown-item" href="https://www.instagram.com/rasimus_v/"><@spring.message 'cv.instagram_name'/></a></li>
                        <li><a class="dropdown-item" href="https://www.facebook.com/rasimusv"><@spring.message 'cv.facebook_name'/></a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="repositories" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <@spring.message 'cv.repositories_name'/></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="https://github.com/rasimusv">GitHub</a></li>
                        <li><a class="dropdown-item" href="https://gitlab.com/rasimusv">GitLab</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="fa fa-globe nav-link dropdown-toggle" href="#" id="language" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
                        <li><a class="dropdown-item" href="?lang=en">English</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>