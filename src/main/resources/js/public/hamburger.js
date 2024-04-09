import { Component } from './component';

export class Hamburger extends Component {

    crossIcon = document.getElementById('cross-icon');

    hamburgerIcon = document.getElementById('hamburger-icon');

    constructor() {
        super('hamburger');
    }

    toggle = () => {
        this._toggle(this.crossIcon);
        this._toggle(this.hamburgerIcon);
    }

    _toggle(element) {
        element.classList.toggle(this.getModifier('hidden'));
    }

    hide = () => {
        this.crossIcon.classList.add(this.getModifier('hidden'));
        this.hamburgerIcon.classList.remove(this.getModifier('hidden'))
    }

    addEventListener(event, handler) {
        this.context.addEventListener(event, handler);
    }
}
