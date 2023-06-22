<#macro template user>
    <!doctype html>
    <html xmlns:th="http://www.thymeleaf.org" lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">

        <title class="text-light">ELECTROMARK</title>
        <link rel="stylesheet" href="/static/css/avatarmenu.css">
    </head>
    <body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid" style="height: 80px">
            <a href="/" style="text-decoration: none;display: inline-block">
                <span class="navbar-brand mb-0 h-3 " style="margin-left: 20px" >ELECTROMARK</span>
            </a>
            <div style="    display: flex;position: relative;right: 28%;">
            <a href="/about" style="text-decoration: none;color:white;display: inline">
                <span style="margin-left: 20px" >О нас</span>
            </a>
            <a href="/"  data-bs-toggle="modal" data-bs-target="#helpModal" style="text-decoration: none;color:white;display: inline-block">
                <span  style="margin-left: 20px" >Что с моим заказом?</span>
            </a>
                <div class="modal fade" id="helpModal" tabindex="-1" aria-labelledby="helpModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="helpModalLabel">Что с моим заказом?</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/help" method="post" >
                                    <div class="form-group">
                                        <label for="name">Имя</label>
                                        <input type="text" required class="form-control"
                                               id="name" name="name">
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label for="phone">Номер телефона</label>
                                        <input type="text" required class="form-control"
                                               id="phone" name="phone">
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label for="code">Номер заказа</label>
                                        <input type="text"  class="form-control"
                                               id="code" name="code">
                                    </div>
                                    <br>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" style="width: 100%" class="btn btn-dark">Узнать</button>
                                </form>
                                <p style="padding: 15px;margin: 0">Cлужба поддержки свяжется с вами в ближайшее время.</p>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <#if user.email??>

                <div class="dropdown text-end" style="display: inline">
                    <a href="/basket" style="display: inline-block">
                        <img src="/static/images/basket.png" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                    </a>
                    <ul  class="avatarmenu" style="display: inline-block;padding: 0">
                        <li>
                            <a href="/profile">
                                <img src="/static/images/avatar.png" alt="mdo"
                                     width="32" height="32" class="rounded-circle">
                            </a>
                            <ul>
                                 <#if user.isAdmin()>
                                <li>
                                    <a class="btn btn-outline-dark btn-light " href="/admin">Панель админа</a>
                                </li>
                                <li>
                                    <a class="btn btn-outline-dark btn-light " href="/my/products"> Товары</a>
                                </li>
                                     <li>
                                    <a class="btn btn-outline-dark btn-light " href="/product/property">Настройка категорий</a>
                                </li>
                                     <li>
                                         <a class="btn btn-outline-dark btn-light " href="/support">Служба подержки</a>
                                     </li>
                                 </#if>

                                <li>
                                    <form action="/logout" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button type="submit" style="text-align: center; width: 100%" class="btn btn-outline-danger btn-danger text-white mb-2">
                                            Выйти из аккаунта
                                        </button>
                                    </form>
                                </li>
                            </ul>

                        </li>
                    </ul>
                </div>
            <#else>
                <button style="margin-right: 30px" class="btn btn-light my-2 my-sm-0" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>