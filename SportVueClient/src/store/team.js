import {updateTeam, updateMembersInTeam, findTeamAndMembers, detail, teamTourHistory, teamWait, getById } from "../api/TeamApi";

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
    }
}

export default {
    namespaced: true,
    actions,
}