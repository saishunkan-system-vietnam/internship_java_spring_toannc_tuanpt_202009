
import { getToursByType, addTeam, getAction, addTour, deleteTeam, updateStatus, deleteTour, getAll, getById, edit, getByStatus, getByType, getRank } from "../api/TournamentApi";



const actions = {
    getAll({ }) {
        return new Promise((resolve, reject) => {
            getAll().then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    delete({ }, id) {
        return new Promise((resolve, reject) => {
            deleteTour(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    edit({ }, data) {
        return new Promise((resolve, reject) => {
            edit(data).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    getAction({ }) {
        return new Promise((resolve, reject) => {
            getAction().then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    create({ }, id) {
        return new Promise((resolve, reject) => {
            addTour(id).then(res => {
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
    deleteTeam({ }, id) {
        return new Promise((resolve, reject) => {
            deleteTeam(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    addTeam({ }, idAdd) {
        return new Promise((resolve, reject) => {
            addTeam(idAdd).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    getByStatus({ }, status) {
        return new Promise((resolve, reject) => {
            getByStatus(status).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    getByType({ }, type) {
        return new Promise((resolve, reject) => {
            getByType(type).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    getRank({ }, type) {
        return new Promise((resolve, reject) => {
            getRank(type).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    updateStatus({ }) {
        return new Promise((resolve, reject) => {
            updateStatus().then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },

    getToursByType({ }) {
        return new Promise((resolve, reject) => {
            getToursByType().then(res => {
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