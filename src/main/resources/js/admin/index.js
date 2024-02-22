import EditorJS from '@editorjs/editorjs';
import Header from '@editorjs/header';
import List from '@editorjs/list';
import SimpleImage from '@editorjs/image';
import Quote from '@editorjs/quote';
import LinkTool from '@editorjs/link'
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
            class: SimpleImage,
            inlineToolbar: ['link']
        },
        quote: {
            class: Quote,
            inlineToolbar: ['link']
        },
        linkTool: {
            class: LinkTool,
            inlineToolbar: ['link']
        }
    }
});
