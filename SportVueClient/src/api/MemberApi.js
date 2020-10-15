import Api from './BaseApi';

export function getListMember(){
    return Api.get('/members/findAll');
}

export function historyMemberMatchs(id){
    return Api.get('/history/' + id)
}

export function getPlayerId(id){
    return Api.get('/profiles/' + id)
}