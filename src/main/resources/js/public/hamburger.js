import { Component } from './component';

export class Hamburger extends Component {

    crossIcon = document.getElementById('cross-icon');

    hamburgerIcon = document.getElementById('hamburger-icon');

    constructor() {
        super('hamburger');
        this.addEventListener('click', () => {
            this.toggle(this.crossIcon);
            this.toggle(this.hamburgerIcon);
        });
    }

    toggle(element) {
        element.classList.toggle(this.getModifier('hidden'));
    }

    addEventListener(event, handler) {
        this.context.addEventListener(event, handler);
    }
}
