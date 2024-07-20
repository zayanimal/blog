import { Hamburger } from './hamburger';
import { Sidebar } from './sidebar';
import { UserMenu } from './user-menu';
import '../css/public/index.scss';

new UserMenu(new Sidebar(new Hamburger()));
