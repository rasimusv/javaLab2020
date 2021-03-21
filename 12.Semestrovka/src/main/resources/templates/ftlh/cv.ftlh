<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring/>
<!doctype html>
<html lang="<@spring.message 'common.lang'/>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/main.css">

    <title><@spring.message 'cv.title'/></title>
</head>
<body>
<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bg-dark">
    <div class="navbar-brand navbar-text" >
        <a href="#"><@spring.message 'cv.title'/></a>
    </div>
    <img src="../images/portrait.jpg" alt="" width="75" class="round d-inline-block align-top"/>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-md-auto me-auto mb-2 mb-lg-0">
            <li class="nav-item dropdown">
                <a class="fa fa-globe nav-link dropdown-toggle mt-1" href="#" id="language" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
                    <li><a class="dropdown-item" href="?lang=en">English</a></li>
                </ul>
            </li>
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
        </ul>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <div class="offset-md-2 col-md-8">
            <div class="embed-responsive embed-responsive-a4">
                <object data="<@spring.message 'cv.pdf_file_path'/>#size=50" width="auto" height="auto" class="center-block embed-responsive-item"></object>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="offset-md-2 col-md-8">
            <div class="badge badge-download mt-5">
                <i class="fa fa-download"></i>
                <a href="<@spring.message 'cv.pdf_file_path'/>" download><@spring.message 'cv.download_main_cv'/></a>
            </div><br>
            <div class="badge badge-download mb-5">
                <i class="fa fa-download"></i>
                <a href="/static/pdf/EuropassCV.pdf" download><@spring.message 'cv.download_europass_cv'/></a>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>