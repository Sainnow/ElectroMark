<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизация</h1>
    <hr>
    <form action="/login " method="post">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Адрес электронной почты</label>
            <input type="email" class="form-control  <#if RequestParameters.error??>is-invalid</#if>"   id="email" name="username" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">Мы никогда никому не передадим вашу электронную почту.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h2>Нет аккаунта ELECTROMARK? <a href="/registration"> Зарегистрируйся!</a></h2>
</@t.template>