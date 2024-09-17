import EditorJS from '@editorjs/editorjs';
import Header from '@editorjs/header';
import List from '@editorjs/list';
import Quote from '@editorjs/quote';
import LinkTool from '@editorjs/link';
import Delimiter from '@editorjs/delimiter';

import Image from "./image";

import '../css/public/index.scss';

const editor = new EditorJS({
    holder: 'editorjs',
    autofocus: true,
    minHeight: 200,
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
                body: JSON.stringify(Object.assign({
                    blocks: [
                        {
                            id: 'iUYhfUdf',
                            type: 'header',
                            data: {
                                text: settings.get('title')
                            }
                        },
                        ...res.blocks
                    ]
                }, {
                    topic: settings.get('topic'),
                    isPublic: settings.get('is-public') === 'on'
                }))
            })
        })
        .then(() => window.location.assign('/'))
});
