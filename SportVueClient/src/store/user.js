import { searchUser } from '@/api/UserApi'
import { getById } from '../api/UserApi';
import { autoLogin } from '../api/UserApi'

const state = {
    userInfo: null,
    isProfile: false,
}

const mutations = {
    user_info(state, user) {
        state.userInfo = user
        // console.log(state.userInfo)
    },
    user_profile(state) {
        state.isProfile = !state.isProfile
    }
}

const actions = {
    searchUser({ }, params) {
        return new Promise((resolve, reject) => {
            searchUser(params).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    getById({ }, id) {
        return new Promise((resolve, reject) => {
            getById(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    async autoLogin({ commit },) {
        try {
            await autoLogin().then((res) => {
                commit('user_info', res.data.payload)
                return res
            })
        } catch (Ex) {
            reject(err);
        }
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}