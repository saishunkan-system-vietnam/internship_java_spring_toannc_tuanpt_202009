import { addComment, getAll } from "../api/Comment";

const actions={
    getAll({ },id) {
        return new Promise((resolve, reject) => {
            getAll(id).then(res => {
                resolve(res);
            }).catch((err) => {
                reject(err);
            })
        })
    },
    addComment({ },data) {
        // console.log(data)
        return new Promise((resolve, reject) => {
            addComment(data).then(res => {
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