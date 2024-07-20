import { Component } from './component';

export class Sidebar extends Component {

    hamburger = null;

    background = document.getElementById('sidebar-background');

    constructor(hamburger) {
        super('sidebar');
        this.hamburger = hamburger;
        this.hamburger.addEventListener('click', this.show);
        this.background.addEventListener('click', this.show);
    }

    show = () => {
        this.hamburger.toggle();
        this.context.classList.toggle(this.getModifier('hidden'));
        this.background.classList.toggle(`${this.className}__background_hidden`);
    }

    hide = () => {
        this.hamburger.hide();
        this.context.classList.add(this.getModifier('hidden'));
        this.background.classList.add(`${this.className}__background_hidden`);
    }
}
