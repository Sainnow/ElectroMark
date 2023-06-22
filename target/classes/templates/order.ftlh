<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-4 g-4">
        <#list order as order>
            <div class="col" style="height: 400px">
                <a href="/product/${product.id}" class="product-link">
                    <div class="card " style="width: 19rem">
                        <div class="card-body ">
                            <img class="card-img-bottom" src=
                            "/images/${product.previewImageId}"
                                 alt="Card image" style="width: 100%;">
                            <h4 class="card-title">${product.title}</h4>
                            <br>
                            <p class="card-text">${product.price} BYN</p>
                            <a href="/product/${product.id}" class="btn btn-success btn-lg">Подробнее</a>
                        </div>
                    </div>
                </a>
            </div>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
    <br>

    <br>

</@t.template>