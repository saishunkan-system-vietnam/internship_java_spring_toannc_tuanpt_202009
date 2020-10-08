import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Axios from 'axios'
import vuetify from './plugins/vuetify';

import "@babel/polyfill";
import "mutationobserver-shim";
import "./plugins/bootstrap-vue";
import VueAxios from 'vue-axios'

import 'material-design-icons-iconfont/dist/material-design-icons.css'


Vue.prototype.$http = Axios;

const token = localStorage.getItem('user-token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

Vue.config.productionTip = false
Vue.use(VueAxios)

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')