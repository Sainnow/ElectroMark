<#import "blocks/template.ftlh" as t>
<@t.template user >
    <h1 style="text-align: center">Панель администратора</h1><hr>
<table class="table table-hover table-outline-dark">
    <tr>
        <th scope="col">Номер запроса в служюу поддержки</th>
        <th scope="col">Имя </th>
        <th scope="col">Телефон</th>
        <th scope="col">Номер заказа</th>
        <th scope="col">Статус заказа</th>
    </tr>
    <#list support as support>
        <tr>
            <#if support.active>
            <th>${support.id}</th>
            <th>${support.name}</th>
            <th>${support.phone}</th>
            <th>${support.code}</th>
            <th>
                <form action="/support/${support.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" class="btn btn-light" value="Закрыть запрос">
                </form>
            </th>
            </#if>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</@t.template>