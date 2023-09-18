import { createElement } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.js';
import 'bootstrap/dist/css/bootstrap.min.css';

createRoot(document.getElementById('outlet')!).render(createElement(App));
