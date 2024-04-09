import { Component } from './component';

export class UserMenu extends Component {

    sidebar = null;

    userIcon = document.getElementById('user-icon');

    background = document.getElementById('user-menu-background');

    constructor(sidebar) {
        super('user-menu');
        this.sidebar = sidebar;
        this.userIcon.addEventListener('click', this.show);
        this.background.addEventListener('click', this.show);
    }

    show = () => {
        this.sidebar.hide();
        this.context.classList.toggle(this.getModifier('hidden'));
        this.background.classList.toggle(`${this.className}__background_hidden`);
    }
}
