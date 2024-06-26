import EditorJS from '@editorjs/editorjs';
import Header from '@editorjs/header';
import List from '@editorjs/list';
import Quote from '@editorjs/quote';
import LinkTool from '@editorjs/link';
import Delimiter from '@editorjs/delimiter';

import Image from "./image";

import '../../css/admin/index.scss';

const editor = new EditorJS({
    holder: 'editorjs',
    tools: {
        header: {
            class: Header,
            inlineToolbar: ['link']
        },
        list: {
            class: List,
            inlineToolbar: ['link']
        },
        image: {
            class: Image,
            inlineToolbar: ['link'],
            config: {
                endpoints: {
                    byFile: '/upload'
                }
            }
        },
        quote: {
            class: Quote,
            inlineToolbar: ['link']
        },
        linkTool: {
            class: LinkTool,
            inlineToolbar: ['link']
        },
        delimiter: Delimiter
    }
});

const btn = document.getElementById("save");

btn.addEventListener('click', () => {
    const settings = new FormData(document.getElementById("settings"));
    editor.save()
        .then((res) => {
            return fetch('/post/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Object.assign(res, {
                    topic: settings.get('topic'),
                    isPublic: settings.get('is-public') === 'on'
                }))
            })
        })
});

const renderButton = document.getElementById("render");

renderButton.addEventListener('click', () => {
    editor.render({
        "time": 1709465247023,
        "blocks": [
            {
                "id": "8ZhBrrp_8c",
                "type": "image",
                "data": {
                    "file": {
                        "url": "https://downloader.disk.yandex.ru/preview/bf848b05b75c4257da0f4a1fdcd09df3c1b00790aa18a6c76c04dcb475d67501/inf/KbglbWOTKizt0qQxTb7oKiCHSUYWO4EFpjbxQuqcp2BxZUHMBraHP5JxmcgBQK_nYd6M9LhTuoKlH55MKHzVfQ%3D%3D?uid=305198944&filename=82121f43036140e3a4ac2f90a2ab4031.jpeg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=305198944&tknv=v2&size=XL&crop=0"
                    },
                    "caption": "",
                    "withBorder": false,
                    "stretched": false,
                    "withBackground": false
                }
            }
        ],
        "version": "2.29.0"
    }).then(() => {

    })
});
