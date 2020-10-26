import { searchUser } from '@/api/UserApi'
import { forgetEmail, findByEmailUser, getAll, getById, updateProfileUser } from '../api/UserApi';
import { autoLogin } from '../api/UserApi'
import { findByEmail, historyMemberMatchs, upcommingMemberMatchs } from '../api/MemberApi'
import { getPlayerId } from '../api/MemberApi'
import { createMember, members } from '@/api/MemberApi'

const state = {
    userInfo: null,
    isProfile: false,
    isAdminProfile: false
}

const mutations = {
    user_info(state, user) {
        state.userInfo = user
    },
    user_profile(state) {
        state.isProfile = !state.isProfile
    },
    admin_profile(state) {
        state.isAdminProfile = !state.isAdminProfile
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

    getAll({ }) {
        return new Promise((resolve, reject) => {
            getAll().then(res => {
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

    autoLogin({ commit },) {
        return new Promise((resolve, reject) => {
            autoLogin().then((res) => {
                commit('user_info', res.data.payload)
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    historyMemberMatchs({ }, id) {
        return new Promise((resolve, reject) => {
            historyMemberMatchs(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    upcommingMemberMatchs({ }, id) {
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

    updateProfileUser({ }, bodyFromData) {
        return new Promise((resolve, reject) => {
            updateProfileUser(bodyFromData).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    findByEmail({ }, email) {
        return new Promise((resolve, reject) => {
            findByEmail(email).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    findByEmailUser({ }, email) {
        return new Promise((resolve, reject) => {
            findByEmailUser(email).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    createMember({ }, formMember) {
        return new Promise((resolve, reject) => {
            createMember(formMember).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    members({ }) {
        return new Promise((resolve, reject) => {
            members().then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    forgetEmail({ }, email) {
        return new Promise((resolve, reject) => {
            forgetEmail(email).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}