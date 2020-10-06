import { login, userRegister } from '@/api/UserApi';
import { getListMember } from '@/api/MemberApi';

// const state = {
//     token: getToken(),
//     name: '',
//     avatar: '',
//     introduction: '',
//     roles: []
// }

const state = {
    token: localStorage.getItem('token') || '',
    name: '',
    avatar: '',
    secure: localStorage.getItem('secure') || '',
    status: '',
    dialog: false,
}

const mutations = {
    auth_request(state) {
        state.status = 'loading'
    },
    auth_success(state, user) {
        state.status = 'success'
        // state.secure = encrypted
        state.token = user.token
    },
    auth_error(state) {
        state.status = 'error'
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
                    const user = resp.data
                    // console.log(user.account.role)

                    var encrypted = CryptoJS.AES.encrypt(user.account.role,"secure");
                    // console.log("encrypted :" + encrypted)

                    // var decrypted = CryptoJS.AES.decrypt(encrypted,"secure");
                    // console.log(decrypted.toString(CryptoJS.enc.Utf8))

                    localStorage.setItem('token', user.token);
                    localStorage.setItem('secure', encrypted);
                    commit('auth_success', user);
                    resolve(resp)
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
  