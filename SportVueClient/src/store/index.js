import Vue from 'vue'
import Vuex from 'vuex'
import auth from '@/store/auth'
import VueCrypt from 'vue-crypt'
import user from '@/store/user'
import schedule from '@/store/schedule'
import team from '@/store/team'
import tournament from '@/store/tournament'
import round from '@/store/round'


Vue.use(Vuex)
Vue.use(VueCrypt)

export default new Vuex.Store({
	modules:{
		auth,
		user,
		schedule,
		team,
		tournament,
		round	
	},
})