import {searchUser} from '@/api/UserApi'
import { getById } from '../api/UserApi';

const actions={
    searchUser({}, params) {
        return new Promise((resolve, reject) => {
            searchUser(params).then(res => {
                resolve(res);
            }).catch((err) => {
                    reject(err);
            })
        })
    },
    getById({},id){
        return new Promise((resolve,reject)=>{
            getById(id).then(res=>{
                resolve(res);
            }).catch((err)=>{
                reject(err);
            })
        })
    }
}
export default {
    namespaced: true,
    actions,
  }