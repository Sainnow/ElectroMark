<#import "blocks/template.ftlh" as t>
<@t.template user >
    <hr>
        <form action="/typename" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Название категрии</label>
                <input type="text" required class="form-control"
                       id="name" name="name">
            </div>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    <br>

    <form action="/classname" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Название подкатегории</label>
            <input type="text" required class="form-control"
                   id="name" name="name">
        </div>
        <br>
        <div class="form-group">
            <label for="searchCity">Категория</label>
            <select id="typename" required name="typename" class="form-control">
                <option value="">Категория не выбран</option>
                <#list typename as typename >
                    <option value="${typename.id}">${typename.name}</option>
                </#list>
            </select>
        </div>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
    </form>

    <br>

    <form action="/property" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">Названия свойства</label>
            <input type="text" required class="form-control"
                   id="title" name="title">
        </div>
        <br>
        <div class="form-group">
            <label for="searchCity">Подкатегория</label>
            <select id="classname" required name="classname" class="form-control">
                <option value="">Подкатегория не выбран</option>
                <#list classname as classname >
                    <option value="${classname.id}">${classname.name}</option>
                </#list>
            </select>
        </div>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
    </form>
</@t.template>