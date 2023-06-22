<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <#list images as image >
            <div class="carousel-item <#if image.previewImage>active</#if>" style="height: 600px">
                <div class="container">
                    <div class="carousel-caption" style="height: 500px">
                        <a href="/images/${image.id}">
                            <img class="product" style="height: 400px" src="/images/${image.id}">
                        </a>
                    </div>
                </div>
            </div>
            </#list>
        </div>
        <button class="carousel-control-prev " type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon btn-dark" style="border-radius: 20%" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next " type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon btn-dark " style="border-radius: 20%" aria-hidden="true" ></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <hr>

    <div>
    <#if user.email??>
    <form action="/product/basket/${product.id}" method="post" class="col-sm form-inline" style="float:right;margin-top: 10px">
        <p style="font-size: 1.2em;margin-left:5px;margin-bottom: 5px;font-weight: bolder">   ${product.price} BYN</p>
        <#if product.number == 0>
            <p style="font-size: 1.2em;margin-left:5px;margin-bottom: 5px;font-weight: bolder">Нет в наличии  </p>
        <#else>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit"  class="btn btn-outline-success btn-dark btn-lg">
                Добавить в корзину
            </button>
            <p style="font-size: 1.2em;margin-left:5px;margin-bottom: 5px;font-weight: bolder">В наличии  </p>
        </#if>
    </form>
    <#else>
    </#if>
    </div>
    <p><h1 style="text-align: center;">${product.title}
    <br>

</h1>
    <br>
    <h1 style="text-align: Left;">Характеристики товара:
        <br>
    </h1>
    <p style="font-size: 1.2em;margin-left:10px;margin-bottom: 5px;font-weight: bolder">  Категория товара: ${product.typename.name}</p>
    <p style="font-size: 1.2em;margin-left:10px;margin-bottom: 5px;font-weight: bolder"> Подкатегория товара: ${product.classname.name}</p>
    <#list property as property>
        <p style="text-align: left;display: inline-block;font-size: 1.2em;margin-left:10px;margin-bottom: 5px;font-weight: bolder" >${property.title} :  <#list value as value>
                    <#if property.id == value.property.id><p style="text-align: right;display: inline-block;font-size: 1.2em;margin-left:10px;margin-bottom: 5px;font-weight: bolder">${value.value}</p></#if>
                </#list>
        <br>
    </#list>
    <p style="font-size: 1.2em;margin-left:10px;margin-bottom: 5px;font-weight: bolder;width: 300px" >${product.description}</p>
    <p style="text-align: center">Номер телефона : <b>${authorProduct.phoneNumber}</b></p>
    <hr>
    <p style="text-align: center">
        <a href="/user/${authorProduct.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorProduct.name}
        </a>
    </p>

</@t.template>