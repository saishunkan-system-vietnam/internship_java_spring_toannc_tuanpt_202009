import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'
import Web from '@/views/web'

import AdminLogin from './views/admin/AdminLogin.vue'
import AdminHome from './views/admin/Home.vue'
import HomeAdmin from './views/admin/HomeAdmin.vue'


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
import DetailScheduleView from './views/web/contents/DetailSchedule'
import Football from '@/views/web/contents/Football'
import LayoutFootball from './views/web/contents/Football/LayoutFootball'
import DetailTournamentFootball from './views/web/contents/Football/DetailTournamentFootball'
import DetailTeamFootball from './views/web/contents/Football/DetailTeamFootball'
import RankTournament from './views/web/contents/Football/RankTournament'
import TableTennis from '@/views/web/contents/TableTennis'
import LayoutTableTennis from './views/web/contents/TableTennis/LayoutTableTennis'
import DetailTournamentTableTennis from './views/web/contents/TableTennis/DetailTournamentTableTennis'
import DetailTeamTableTennis from './views/web/contents/TableTennis/DetailTeamTableTennis'
import RankTournamentTableTennis from './views/web/contents/TableTennis/RankTournament'
import BasketBall from '@/views/web/contents/BasketBall'
import LayoutBasketBall from './views/web/contents/BasketBall/LayoutBasketBall'
import DetailTournamentBasketBall from './views/web/contents/BasketBall/DetailTournamentBasketBall'
import DetailTeamBasketBall from './views/web/contents/BasketBall/DetailTeamBasketBall'
import RankTournamentBasketBall from './views/web/contents/BasketBall/RankTournament'
import LayoutAllSports from './views/web/contents/AllSports/LayoutAllSports'
import PlayerProfile from './views/web/profile/PlayerProfile'

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
        children: [{
          path: '/',
          name: 'LayoutAllSports',
          component: LayoutAllSports
        },
        ]
      },
      {
        path: '/PlayerProfile/:id',
        component: PlayerProfile,
      },

      {
        path: '/Football',
        component: Football,
        children: [
          {
            path: '/',
            component: LayoutFootball
          },
          {
            path: '/DetailTournamentFootball/:id',
            name: 'DetailTournamentFootball',
            component: DetailTournamentFootball
          },
          {
            path: '/DetailTeamFootball/:id',
            name: 'DetailTeamFootball',
            component: DetailTeamFootball
          },
          {
            path: '/RankTournament/Football',
            component: RankTournament
          }
        ]
      },

      {
        path: '/BasketBall',
        component: BasketBall,
        children: [
          {
            path: '/',
            component: LayoutBasketBall
          },
          {
            path: '/DetailTournamentBasketBall/:id',
            name: 'DetailTournamentBasketBall',
            component: DetailTournamentBasketBall
          },
          {
            path: '/DetailTeamBasketBall/:id',
            name: 'DetailTeamBasketBall',
            component: DetailTeamBasketBall
          },
          {
            path: '/RankTournament/BasketBall',
            component: RankTournamentBasketBall
          }

        ]
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
            path: '/DetailTournamentTableTennis/:id',
            name: 'DetailTournamentTableTennis',
            component: DetailTournamentTableTennis
          },
          {
            path: '/DetailTeamTableTennis/:id',
            name: 'DetailTeamTableTennis',
            component: DetailTeamTableTennis
          },
          {
            path: '/RankTournament/TableTennis',
            name: 'RankTournament',
            component: RankTournamentTableTennis
          }
        ]
      },

    ]
  },
  {
    path: '/detail/:id',
    name: 'detailScheduleView',
    component: DetailScheduleView
  },
  {
    path: '/admin/login',
    name: 'login',
    component: AdminLogin,
    meta: { requiredAuth: false }
  },

  {
    path: '/admin/home',
    name: 'Home',
    component: AdminHome,
    redirect: '/admin/home',
    children: [
      {
        path: '/',
        component: HomeAdmin,
        name: 'HomeAdmin'
      },
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
    await store.dispatch("user/autoLogin").then((res) => {
      // console.log(res)
      if (store.state.user.userInfo.role === 'ROLE_ADMIN') {
        store.commit('user/admin_profile')
      }
      else {
        store.commit('user/user_profile')
      }
    })
  } else {
    next()
  }
  if (to.meta.requiredAuth) {
    const authUser = store.state.auth
    if (!authUser || !authUser.token) {
      next()
    }
    else if (to.meta.adminAuth) {
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