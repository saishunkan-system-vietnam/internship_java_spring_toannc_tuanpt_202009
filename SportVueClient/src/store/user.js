import { searchUser } from '@/api/UserApi'
import { getById, updateProfileUser } from '../api/UserApi';
import { autoLogin } from '../api/UserApi'
import {findByEmail, historyMemberMatchs,upcommingMemberMatchs} from '../api/MemberApi'
import {getPlayerId} from '../api/MemberApi'
 
const state = {
    userInfo: null,
    isProfile: false,
}

const mutations = {
    user_info(state, user) {
        state.userInfo = user
        console.log("Run First")
        console.log(state.userInfo )
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

    historyMemberMatchs({ },id) {
        return new Promise((resolve, reject) => {
            historyMemberMatchs(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    upcommingMemberMatchs({ },id) {
        return new Promise((resolve, reject) => {
            upcommingMemberMatchs(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    getPlayerId({ }, id) {
        return new Promise((resolve, reject) => {
            getPlayerId(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    updateProfileUser({},bodyFromData){
        return new Promise((resolve, reject) => {
            updateProfileUser(bodyFromData).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    findByEmail({},email){
        return new Promise((resolve, reject) => {
            findByEmail(email).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}