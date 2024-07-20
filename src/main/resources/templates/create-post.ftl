<#import 'layout.ftl' as l>

<@l.layout topics isAuth>
    <div class="create-post">
        <h1>Создать пост</h1>

        <div id="editorjs" class="create-post__editor"></div>

        <form class="create-post__settings" id="settings">
            <div class="create-post__settings-item">
                <label for="is-public">Видно всем: </label>
                <input type="checkbox" name="is-public" id="is-public" checked />
            </div>

            <div class="create-post__settings-item">
                <label for="topic">Тема: </label>
                <select name="topic" form="settings" id="topic" required>
                    <#list topics as topic>
                        <option value="${topic.name}">${topic.name}</option>
                    </#list>
                </select>
            </div>
        </form>

        <button id="save">Сохранить</button>
    </div>
    <script src="/create-post.js"></script>
</@l.layout>