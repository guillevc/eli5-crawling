import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faSquareRootAlt,
  faDna,
  faRobot,
  faUniversity,
  faFlask,
  faCogs,
  faAtom,
  faShapes,
  faChild,
  faInfinity
} from '@fortawesome/free-solid-svg-icons';
import Vue from 'vue';
import App from './App.vue';
import router from './router';

library.add(
  faSquareRootAlt,
  faDna,
  faRobot,
  faUniversity,
  faFlask,
  faCogs,
  faAtom,
  faShapes,
  faChild,
  faInfinity
);
Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
