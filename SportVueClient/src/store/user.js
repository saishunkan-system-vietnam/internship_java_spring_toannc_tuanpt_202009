import { searchUser } from '@/api/UserApi'
import { getById } from '../api/UserApi';
import { getByUsername } from '../api/UserApi'

const state = {
    userInfo: null,
    isProfile: false,
}

const mutations = {
    user_info(state, user) {
        state.userInfo = user
    },
    user_profile(state){
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
    getByUsername({ commit }, username) {
        
        commit('user_info', {
            "id": 113,
            "email": "Create1@gmail.com",
            "password": "$2a$12$9kaVBBWMtQyy/0sPH9UBuOAlwxuvGwnSgREf6i33.zV160k89s1.a",
            "role": "ROLE_USER",
            "username": "Create1",
            "profile": {
                "id": null,
                "name": null,
                "email": "Create1@gmail.com",
                "phone": null,
                "age": null,
                "gender": null,
                "address": null,
                "type": null,
                "avatar": "http://localhost:8090/images/defaultuser.png",
                "idTeam": 0,
                "team": null
            }
        })
        // return new Promise((resolve, reject) => {
        //     getByUsername(username).then(res => {
        //         resolve(res);
        //     }).catch((err) => {
        //         reject(err);
        //     })
        // })
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}