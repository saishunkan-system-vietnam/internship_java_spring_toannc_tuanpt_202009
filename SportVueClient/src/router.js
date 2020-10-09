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
import BaskestBall from '@/views/web/contents/BaskestBall'
import TableTennis from '@/views/web/contents/TableTennis'
import DetailSoccer from './views/web/contents/Soccer/DetailSoccer'
import LayoutSoccer from './views/web/contents/Soccer/LayoutSoccer'
import DetailTournametSoccer from './views/web/contents/Soccer/DetailTournametSoccer'
import LayoutTableTennis from './views/web/contents/TableTennis/LayoutTableTennis'
import DetailTournametTableTennis from './views/web/contents/TableTennis/DetailTournametTableTennis'
import LayoutAllSports from './views/web/contents/AllSports/LayoutAllSports'
import DetailTournametSports from './views/web/contents/AllSports/DetailTournametSports'

Vue.use(Router)
const metaConfig = {
  requiredAuth: true,
  adminAuth: true,
  userAuth: false
}
let routes = [
  {
    path: '/',
    name: 'web',
    component: Web,
    redirect: '/sports',
    children: [
      {
        path: '/sports',
        component: AllSports,
        children:[{
          path:'/',
          name:'LayoutAllSports',
          component:LayoutAllSports
        },
        {
          path: '/DetailTournametSports/:id',
          name: 'DetailTournametSports',
          component: DetailTournametSports
        }
        
      ]
      },
      {
        path: '/soccer',
        component: Soccer,
        children: [
          {
            path: '/',
            component: LayoutSoccer
          },
          {
            path: '/DetailTournametSoccer/:id',
            name: 'DetailTournametSoccer',
            component: DetailTournametSoccer
          }
        ]
      },

      {
        path: '/baskestBall',
        name: 'baskestBall',
        component: BaskestBall,
      },
      {
        path: '/tableTennis',
        component: TableTennis,
        children: [
          {
            path: '/',
            component: LayoutTableTennis
          },
          {
            path: '/DetailTournametTableTennis/:id',
            name: 'DetailTournametTableTennis',
            component: DetailTournametTableTennis
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
            meta: metaConfig
          },
          {
            path: "/Detail/:id",
            component: Detail,
            meta:
              metaConfig
          }
        ],
        meta: metaConfig
      },
      {
        path: "/LayoutSchedule",
        component: LayoutSchedule,
        children: [
          {
            path: "/",
            component: Schedule,
            name: 'Schedule',
            meta: metaConfig
          },
          {
            path: "/DetailSchedule/:id",
            component: DetailSchedule,
            meta: metaConfig
          }

        ],
        meta: metaConfig
      },
      {
        path: "/LayoutTeam",
        component: LayoutTeam,
        children: [
          {
            path: "/",
            component: Teams,
            name: "Teams",
            meta: metaConfig
          },
          {
            path: "/create",
            component: CreateTeam,
            name: "CreateTeam",
            meta: metaConfig
          },
          {
            path: "/edit/:id",
            component: TeamDetail,
            name: "TeamDetail",
            meta: metaConfig
          }
        ],
        meta: metaConfig
      },
      {
        path: "/LayoutTournament",
        component: LayoutTournament,
        children: [
          {
            path: "/",
            component: Tournament,
            name: "Tournament",
            meta: metaConfig
          },
          {
            path: "/DetailTournament/:id",
            component: DetailTournament,
            name: "DetailTournament",
            meta: metaConfig
          }
        ],
        meta: metaConfig
      }
    ],
    meta: metaConfig
  }
]
const router = new Router({
  routes,
  mode: 'history'
})

router.beforeEach(async (to, from, next) => {
  if (store.state.auth.token != '' && store.state.user.userInfo == null) {
    store.commit('user/user_profile')
    await store.dispatch("user/autoLogin")
  } else {
    next()
  }

  if (to.meta.requiredAuth) {
    const authUser = store.state.auth
    if (!authUser || !authUser.token) {
      next()
    }

    if (to.meta.adminAuth) {
      let role = store.state.user.userInfo.role;
      if (role === 'ROLE_ADMIN') {
        next()
      } else {
        next('/admin/login')
      }
    }
  }
  else {
    next()
  }
})

export default router