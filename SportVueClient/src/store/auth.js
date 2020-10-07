import { login, userRegister } from '@/api/UserApi';
import { getListMember } from '@/api/MemberApi';

const state = {
    token: localStorage.getItem('token') || '',
    secure: localStorage.getItem('secure') || '',
    status: '',
    checkAccount: false,
    avatar: '',
}

const mutations = {
    auth_image(state, img) {
        state.avatar = img
    },
    auth_request(state) {
        state.status = 'loading'
    },
    auth_success(state, user) {
        state.status = 'success'
        state.token = user.token
    },
    auth_error(state) {
        state.checkAccount = !state.checkAccount
    },
    logout(state) {
        state.status = ''
        state.token = ''
    }
}

const actions = {

    login({ commit }, user) {
        return new Promise((resolve, reject) => {
            commit('auth_request')
            login(user)
                .then(resp => {
                    if (resp.data.code === 9999) {
                        commit('auth_error')
                        setTimeout(function () {
                            commit('auth_error')
                        }, 3000);
                    } else {
                        const user = resp.data.payload
                        // console.log(user)
                        var encrypted = CryptoJS.AES.encrypt(user.account.username, "secure");
                        // console.log("encrypted :" + encrypted)
                        // var decrypted = CryptoJS.AES.decrypt(encrypted,"secure");
                        // console.log(decrypted.toString(CryptoJS.enc.Utf8))
                        localStorage.setItem('token', user.token);
                        localStorage.setItem('secure', encrypted);
                        commit('auth_success', user);
                        commit('auth_image', user.account.profile.avatar)
                        resolve(resp)
                    }
                })
                .catch(err => {
                    commit('auth_error')
                    reject(err)
                })
        })
    },

    register({ commit }, user) {
        return new Promise((resolve, reject) => {
            commit('auth_request')
            userRegister(user)
                .then(resp => {
                    resolve(resp)
                })
                .catch(err => {
                    commit('auth_error', err)
                    localStorage.removeItem('token')
                    reject(err)
                })


        })
    },

    logout({ commit }) {
        return new Promise((resolve, reject) => {
            commit('logout')
            localStorage.removeItem('token')
            localStorage.removeItem('secure')
            resolve()
        })
    },

    getMembers({ commit }) {
        // console.log("Run this")
        return new Promise((resolve, reject) => {
            getListMember().then(res => {
                // console.log("Run ")
                // console.log(res.data);
                resolve(res);
            })
                .catch((err) => {
                    reject(err);
                })
        })
    }
}

const getters = {
    isLoggedIn: state => !!state.token, // change when have token or not
    authStatus: state => state.status,

}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
