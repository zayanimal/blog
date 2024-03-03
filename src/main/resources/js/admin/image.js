import SimpleImage from '@editorjs/image';

export default class Image extends SimpleImage {

    static get sanitize() {
        return {
            a: true,
            href: true,
            url: true
        }
    }
}
