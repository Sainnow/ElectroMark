<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование товара</h1>
<div class="mb-3" id="collapseExample" >
    <form  method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Название товара</label>
                <input type="text"  class="form-control"
                       id="productName" name="title" value="${product.title}">
            </div>
            <br>
            <div class="form-group">
                <label for="price">Цена товара</label>
                <div class="input-group mb-3">
                    <input type="number"  class="form-control" id="price" name="price" value="${product.price}">
                    <div class="input-group-append">
                        <span class="input-group-text">BYN</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="number">Количество </label>
                <div class="input-group mb-3">
                    <input type="number"  class="form-control" id="number" name="number" value="${product.number}">
                </div>
            </div>
            <br>
            <div  class="form-group">
                <label for="description">Описание товара</label>
                <textarea  class="form-control" id="description" name="description" >${product.description}</textarea>
            </div>
            <br>
        <div class="form-group">
            <div class="custom-file">
                <input  type="file" name="file1" id="file1">
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
                <input type="file" name="file3" id="file3" value="">
                <label class="custom-file-label" for="file3">Третья фотография к товару</label>
            </div>
        </div>
        <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <script src="/static/jQuery/jquery-3.6.4.js"></script>
            <script src="/static/js/subcategory.js"></script>
            <script src="/static/js/property.js"></script>
            <script src="/static/js/propel.js"></script>

            <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
        </form>
</div>
</@t.template>