import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import Web from '@/views/web'
import AdminLogin from './views/admin/AdminLogin.vue'
import AdminHome from './views/admin/Home.vue'
import LayoutUser from '@/views/admin/user/LayoutUser'
import User from '@/views/admin/user/User'
import Detail from '@/views/admin/user/Detail'

import LayoutSchedule from '@/views/admin/schedule/LayoutSchedule'
import Schedule from '@/views/admin/schedule/Schedule'
import DetailSchedule from '@/views/admin/schedule/DetailSchedule'


import LayoutTournament from '@/views/admin/tournament/LayoutTournament'
import Tournament from '@/views/admin/tournament/Tournament'
import DetailTournament from '@/views/admin/tournament/DetailTournament'

import LayoutTeam from '@/views/admin/team/LayoutTeam'
import Teams from '@/views/admin/team/Teams'
import CreateTeam from '@/views/admin/team/TeamCreate'
import TeamDetail from '@/views/admin/team/TeamDetail'

import AllSports from '@/views/web/contents/AllSports'
import Soccer from '@/views/web/contents/Soccer'
import DetailSoccer from './views/web/contents/Soccer/DetailSoccer'
import LayoutSoccer from './views/web/contents/Soccer/LayoutSoccer'
import DetailTournametSoccer from './views/web/contents/Soccer/DetailTournametSoccer'
Vue.use(Router)

let routes = [
  {
    path: '/',
    name: 'web',
    component: Web,

    redirect: '/sports',
    children: [
      {
        path: '/sports',
        name: 'allsports',
        component: AllSports,
      },
      {
        path: '/soccer',
        name: 'soccer',
        component: Soccer,
        children: [
          {
            path: '/',
            name: 'LayoutSoccer',
            component: LayoutSoccer
          },
          {
            path:'/DetailTournametSoccer/:id',
            name:'DetailTournametSoccer',
            component:DetailTournametSoccer
          }
        ]
      },
      

    ]
  },
  {
    path: '/soccer/detail/:id',
    name: 'detailSoccer',
    component: DetailSoccer
  },
  {
    path: '/admin/login',
    name: 'login',
    component: AdminLogin
  },


  {
    path: '/admin/home',
    name: 'Home',
    component: AdminHome,
    redirect: '/LayoutUser',
    children: [
      {
        path: "/LayoutUser",
        component: LayoutUser,
        children: [
          {
            path: "/",
            component: User,
            name: "User",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          },
          {
            path: "/Detail/:id",
            component: Detail,
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          }
        ],
        meta: {
          requiredAuth: true,
          adminAuth: true,
          userAuth: false
        }
      },

      {
        path: "/LayoutSchedule",
        component: LayoutSchedule,
        children: [
          {
            path: "/",
            component: Schedule,
            name: 'Schedule',
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          },
          {
            path: "/DetailSchedule/:id",
            component: DetailSchedule,
            meta: { requiresAuth: true },
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          }

        ],
        meta: {
          requiredAuth: true,
          adminAuth: true,
          userAuth: false
        }

      },
      {
        path: "/LayoutTeam",
        component: LayoutTeam,
        children: [
          {
            path: "/",
            component: Teams,
            name: "Teams",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          },
          {
            path: "/create",
            component: CreateTeam,
            name: "CreateTeam",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          },
          {
            path: "/edit/:id",
            component: TeamDetail,
            name: "TeamDetail",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          }
        ],
        meta: {
          requiredAuth: true,
          adminAuth: true,
          userAuth: false
        }
      },
      {
        path: "/LayoutTournament",
        component: LayoutTournament,
        name: "LayoutTournament",
        children: [
          {
            path: "/",
            component: Tournament,
            name: "Tournament",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          },
          {
            path: "/DetailTournament/:id",
            component: DetailTournament,
            name: "DetailTournament",
            meta: {
              requiredAuth: true,
              adminAuth: true,
              userAuth: false
            }
          }
        ],
        meta: {
          requiredAuth: true,
          adminAuth: true,
          userAuth: false
        }
      }
    ],
    meta: {
      requiredAuth: true,
      adminAuth: true,
      userAuth: false
    }
  }
]
const router = new Router({
  routes,
  mode: 'history'
})


router.beforeEach(async (to, from, next) => {
  // console.log(store)
  // console.log(store.state.user.userInfo)
  if (store.state.auth.token != '' && store.state.user.userInfo == null) {
    store.commit('user/user_profile')
    store.dispatch("user/getByUsername", null)
    console.log(store.state.user.userInfo)
  }
  else {
    store.state.user.isProfile = false
  }
  next()

  if (to.meta.requiredAuth) {
    //Check token
    const authUser = store.state.auth
    const status = localStorage.getItem('secure');
    var decrypted = CryptoJS.AES.decrypt(status, "secure");
    var info = decrypted.toString(CryptoJS.enc.Utf8)
    store.dispatch()
    if (!authUser || !authUser.token) {
      next({ name: '/admin/login' })
    }
    if (to.meta.adminAuth) {
      if (role === 'ROLE_ADMIN') {
        next()
      } else {
        next('/admin/login')
      }
    }
    // else if (to.meta.userAuth) {
    //   if (role === 'ROLE_USER' || role === 'MEMBER' || role === "ADMIN") {
    //     next()
    //   } else {
    //     next()
    //   }
    // }
  }
  else {
    next()
  }
})

export default router