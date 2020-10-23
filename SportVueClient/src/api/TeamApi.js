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
export function getById(id){
    return Api.get('/team/findDetail/'+id);
}

export function findTeamAndMembers(id){
    return Api.get('/team/findDetail/' + id)
}

export function updateMembersInTeam(bodyFormData){
    return Api.post('/team/updateMembersInTeam', bodyFormData)
}

export function updateTeam(id, bodyFormData){
    return Api.post(`/team/updateInfo/${id}`, bodyFormData)
}

export function getTeam(id){
    return Api.get(`/team/`+id)
}

export function createTeam(teamForm){
    return Api.post('/team/createTeam', teamForm)
}
export function getAll(){
    return Api.get('/team/getAll')
}