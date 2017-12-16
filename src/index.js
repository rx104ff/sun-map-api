import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './main/js/registerServiceWorker';
import Map from './main/js/Map'

// ReactDOM.render(<Application />, document.getElementById('app'));
ReactDOM.render(<Map />, document.getElementById('app'));
registerServiceWorker();
