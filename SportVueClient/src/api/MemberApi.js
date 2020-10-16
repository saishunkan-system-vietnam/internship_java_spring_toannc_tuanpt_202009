import Api from './BaseApi';

export function getListMember(){
    return Api.get('/members/findAll');
}

export function historyMemberMatchs(id){
    return Api.get('/history/' + id)
}

export function upcommingMemberMatchs(id){
    return Api.get('/history/upcomming/' + id)
}

export function getPlayerId(id){
    return Api.get('/profiles/' + id)
}
export function findByEmail(email){
    return Api.get('/profiles/findByEmail',{params:{email:email}})
}