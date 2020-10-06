import Api from './BaseApi';

export function teamTourHistory(data){
    return Api.get('/team/teamTourHistory',{params:{idTour:data.idTour,idTeam:data.idTeam}});
}
export function detail(params){
    return Api.get('/team/detail',params);
}
export function teamWait(type){
    return Api.get('/team/teamWait',{params:{type:type}});
}

