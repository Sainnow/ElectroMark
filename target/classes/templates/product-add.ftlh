<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Добавление товара</h1>
    <div class=" mb-3" id="collapseExample" style="">
        <form action="/product/add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Название товара</label>
                <input type="text" required class="form-control"
                       id="productName" name="title">
            </div>
            <br>
            <div class="form-group">
                <label for="price">Цена товара</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="price">
                    <div class="input-group-append">
                        <span class="input-group-text">BYN</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="number">Количество </label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="number" name="number">
                </div>
            </div>
            <div class="form-group">
                <label for="typename">Категория</label>
                <select id="typename"  required name="typename" class="form-control"  >
                    <option value="">Категория не выбрана</option>
                    <#list typename as typename >
                        <option value="${typename.id}">${typename.name}</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-group">
                <label for="classname">Подкатегория</label>
                <select id="classname"  required name="classname" class="form-control">
                    <option value="">Подкатегория не выьбрана</option>
                    <#list classname as classname >
                        <option id="${classname.typename.id}" value="${classname.id}">${classname.name}</option>
                    </#list>
                </select>
            </div>
            <br>
            <#list property as property>
                <div id="inputP" name="${property.classname.id}" class="inputP" style="display: none" value="${property.classname.id}">
                    <label id="${property.classname.id}"  for="${property.title}">${property.title}</label>
                    <input type="text" required class="form-control"
                           id="${property.title}" name="value">
                </div>
                <input type="text" required class="form-control"
                       id="${property.classname.id}" name="property" value="${property.id}" style="display: none">
            </#list>
            <br>
            <div  class="form-group">
                <label for="description">Описание товара</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <br>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Первая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Вторая фотография к товару</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Третья фотография к товару</label>
                </div>
            </div>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <script src="/static/jQuery/jquery-3.6.4.js"></script>
            <script src="/static/js/subcategory.js"></script>
            <script src="/static/js/property.js"></script>
            <script src="/static/js/propel.js"></script>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
</@t.template>