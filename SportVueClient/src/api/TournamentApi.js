import Api from './BaseApi';

export function getAll() {
    return Api.get('/tournament/getAll')
}
export function deleteTour(id) {
    return Api.delete('/tournament/delete/'+id)
}
export function addTour(tournament){
    return Api.post('/tournament/addTournament',tournament)
}
export function getById(id){
    return Api.get('/tournament/getById',{params:{idTour:id}})
}
export function deleteTeam(id){
    return Api.delete('/tournament/deleteTeam',{params:{idTeam:id}})
}
export function addTeam(idAdd){
    return Api.post('/tournament/addTeam/'+idAdd.idTour+'/'+idAdd.idTeam);
}
export function getAction(){
    return Api.get('/tournament/getTourAction')
}
export function updateStatus(){
    return Api.get('/tournament/updateStatus')
}
export function edit(tournament){
    return Api.post('/tournament/edit',tournament)
}

export function getByStatus(status){
    return Api.get('/tournament/getByStatus',{params:{status:status.status,type:status.type}})
}
export function getByType(type){
    return Api.get('/tournament/getByType',{params:{type:type}})
}
export function getRank(type){
    return Api.get('/tournament/rank',{params:{type:type}})
}

export function getRankByTour(idTour){
    return Api.get('/tournament/rankByTour',{params:{idTour:idTour}})


export function rankAll(){
    return Api.get('/tournament/rankAll')
}

export function getToursByType(){
    return Api.get('/tournament/getToursByType')

}
