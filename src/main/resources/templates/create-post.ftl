<#import 'layout.ftl' as l>

<@l.layout topics isAuth>
    <div class="create-post">
        <h1>Создать пост</h1>

        <form class="create-post__settings" id="settings">
            <label for="title"></label>
            <input class="create-post__title" id="title" type="text" name="title" placeholder="Заголовок" />

            <div class="create-post__settings-item">
                <label for="topic">Тема: </label>
                <select name="topic" form="settings" id="topic" required>
                    <#list topics as topic>
                        <option value="${topic.name}">${topic.name}</option>
                    </#list>
                </select>
            </div>

            <div id="editorjs" class="create-post__editor"></div>

            <div>
                <label for="is-public">Видно всем: </label>
                <input type="checkbox" name="is-public" id="is-public" checked />
            </div>
        </form>

        <button class="create-post__button" id="save">Сохранить</button>
    </div>
    <script src="/create-post.js"></script>
</@l.layout>