import { getByIdTour } from "../api/ScheduleApi";
import { detail, teamTourHistory, teamWait } from "../api/TeamApi";

const actions={
    teamTourHistory({},data){
        return new Promise((resolve,reject)=>{
            teamTourHistory(data).then(res=>{
                resolve(res);
            }).catch((err)=>{
                reject(err);
            })
        })
    },
    getDetail({},params){
        return new Promise((resolve,reject)=>{
            detail(params).then(res=>{
                resolve(res);
            }).catch((err)=>{
                reject(err);
            })
        })
    },
    teamWait({},type){
        return new Promise((resolve,reject)=>{
            teamWait(type).then(res=>{
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