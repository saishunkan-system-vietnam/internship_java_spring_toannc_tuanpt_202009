import { getById } from '@/api/ScheduleApi';
import { create, deleteSchedule, edit, updateShedule, getByIdTour, statusNow, getAll, upcomingMatch } from '../api/ScheduleApi';

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
    getByIdTour({ }, id) {
        return new Promise((resolve, reject) => {
            getByIdTour(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    createSchedule({ }, schedule) {
        return new Promise((resolve, reject) => {
            create(schedule).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    deleteSchedule({ }, id) {
        return new Promise((resolve, reject) => {
            deleteSchedule(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    updateSchedule({ }, bodyFormData) {
        return new Promise((resolve, reject) => {
            updateShedule(bodyFormData).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    }
    ,
    getById({ }, id) {
        return new Promise((resolve, reject) => {
            getById(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    editSchedule({ }, schedule) {
        console.log(schedule)
        return new Promise((resolve, reject) => {
            edit(schedule).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    statusNow({ }) {
        return new Promise((resolve, reject) => {
            statusNow().then(res => {
                resolve(res)
            }).catch((err) => {
                reject(err);
            })
        })
    },
    upcomingMatch({ }) {
        return new Promise((resolve, reject) => {
            upcomingMatch().then(res => {
                resolve(res)
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