import Api from './BaseApi';


export function getAll(id){
    return Api.get('/comment/getBySchedule/'+id);
}
export function addComment(data){
    return Api.post('/comment/addComment',data);
}


