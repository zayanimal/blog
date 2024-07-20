export class Component {

    className = '';

    context = null;

    constructor(name) {
        this.className = name;
        this.context = document.getElementById(name);
    }

    getModifier(name) {
        return `${this.className}_${name}`;
    }
}
