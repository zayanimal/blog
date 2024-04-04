<#import 'layout.ftl' as l>

<@l.layout>
    <div id="editorjs"></div>

    <form class="main__settings" id="settings">
        <div class="main__settings-item">
            <label for="is-public">Публичный пост: </label>
            <input type="checkbox" name="is-public" id="is-public" checked />
        </div>

        <div class="main__settings-item">
            <label for="topic">Тема поста: </label>
            <select name="topic" form="settings" id="topic" required>
                <#list topics as topic>
                    <option value="${topic}">${topic}</option>
                </#list>
            </select>
        </div>
    </form>

    <button id="save">Сохранить</button>
    <button id="render">Отрисовать</button>
</@l.layout>
