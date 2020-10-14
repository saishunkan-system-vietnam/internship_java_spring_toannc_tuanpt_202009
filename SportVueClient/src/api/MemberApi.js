import Api from './BaseApi';

export function getListMember(){
    return Api.get('/members/findAll');
}

export function historyMemberMatchs(id){
    return Api.get('/history/' + id)
}
