import { Component } from './component';

export class Sidebar extends Component {

    constructor(hamburger) {
        super('sidebar');
        hamburger.addEventListener('click', () => this.show());
    }

    show() {
        this.context.classList.toggle(this.getModifier('hidden'));
    }
}
