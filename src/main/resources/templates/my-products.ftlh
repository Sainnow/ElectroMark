<#import "blocks/template.ftlh" as t>
<@t.template user>
    <link rel="stylesheet" href="/static/css/menu.css">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div>
                <ul id="firsul" class="menu">
                    <li>
                        <button class="btn btn-outline-dark btn-light btn-lg" type="submit" href="#">Категории</button>
                        <ul >
                            <#list typename as typename >
                                <li>
                                    <form class="d-flex " method="get" action="/my/products">
                                        <button class="btn btn-outline-dark btn-light btn-lg"  value="${typename.id}" name="searchTypenameId" type="submit">${typename.name}</button>
                                    </form>
                                    <ul>
                                        <#list classname as classname >
                                        <#if typename.id==classname.typename.id>
                                        <li>
                                            <form class="d-flex " method="get" action="/my/products">
                                                <button class="btn btn-outline-dark btn-light btn-lg"  value="${classname.id}" name="searchClassId" type="submit">${classname.name}</button>
                                            </form>
                                            </#if>

                                            </#list>
                                        </li>
                                    </ul>
                                </li>
                            </#list>
                        </ul>
                    </li>
                </ul>
            </div>
            <div>
                <form action="/product/add" method="get" class="col-sm form-inline" >
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-outline-dark btn-light btn-lg">
                        Добавить товар
                    </button>
                </form>
            </div>
            <div>
                <form class="d-flex " method="get" action="/my/products">
                    <input <#if searchWord??>value="${searchWord}"</#if> name="searchWord" class="form-control me-2" type="search" placeholder="Поиск" aria-label="Поиск">
                    <button class="btn btn-outline-dark btn-lg" type="submit">Поиск</button>
                </form>
            </div>
        </div>
    </nav>
    <br>
    <h1 style="text-align: center">Мои товары</h1>
    <hr>
    <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-4 g-4">
        <#list products as product>
            <div class="col"  >
                <a href="/product/${product.id}" class="product-link">
                    <div class="card " style="width: 19rem">
                        <div class="card-body " >
                            <img class="card-img-bottom" src=
                            "/images/${product.previewImageId}"
                                 alt="Card image" style="width: 100%;">
                            <h4 class="card-title">${product.title}</h4>
                            <br>
                            <form action="/product/edit/${product.id}" method="get" class="col-sm form-inline" >
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-warning">
                                    Редактировать
                                </button>
                            </form>
                            <form action="/product/delete/${product.id}" method="post" class="col-sm form-inline">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger ">
                                    Удалить
                                </button>
                            </form>
                        </div>
                    </div>
                </a>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>