import { uploadSchedule } from "../api/RoundApi";

const actions={
    uploadSchedule({ },round) {
        return new Promise((resolve, reject) => {
            uploadSchedule(round).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    
    }
export default {
    namespaced: true,
    actions,
  }