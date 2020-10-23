import { createTeam, updateTeam, updateMembersInTeam, findTeamAndMembers, detail, teamTourHistory, teamWait, getById, getTeam,getAll } from "../api/TeamApi";

const state = {
    idTeam: '',

}

const mutations = {
    team_openView(state, idTeam) {
        state.idTeam = idTeam
    },

}

const actions = {

    teamTourHistory({ }, data) {
        return new Promise((resolve, reject) => {
            teamTourHistory(data).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    getAll(){
        return new Promise((resolve, reject) => {
            getAll().then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    getDetail({ }, params) {
        return new Promise((resolve, reject) => {
            detail(params).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    teamWait({ }, type) {
        return new Promise((resolve, reject) => {
            teamWait(type).then(res => {
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

    findTeamAndMembers({ }, id) {
        return new Promise((resolve, reject) => {
            findTeamAndMembers(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    updateMembersInTeam({ }, data) {
        return new Promise((resolve, reject) => {
            updateMembersInTeam(data).then((res) => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    updateTeam({ }, id, data) {
        return new Promise((resolve, reject) => {
            updateTeam(id, data).then((res) => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    getTeam({ }, id) {
        return new Promise((resolve, reject) => {
            getTeam(id).then((res) => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    createTeam({ }, teamForm) {
        return new Promise((resolve, reject) => {
            createTeam(teamForm).then((res) => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    }
}

export default {
    namespaced: true,
    actions,
    state,
    mutations
}