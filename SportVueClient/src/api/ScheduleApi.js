import Api from './BaseApi';

export function getAll() {
  return Api.get('/schedule/getAll')
}
export function getByIdTour(id) {
  return Api.get('/schedule/getByIdTour', {
    params: {
      idTour: id
    }
  });
}
export function create(schedule) {
  return Api.post('/schedule/create', schedule);
}
export function getById(id) {
  return Api.get('/schedule/getById/' + id)
}
export function deleteSchedule(id) {
  return Api.delete('/schedule/delete/' + id);
}
export function statusNow() {
  return Api.get('/schedule/status');
}
export function edit(schedule) {
  return Api.post('/schedule/editShedule', schedule)
}
export function updateShedule(bodyFormData) {
  return Api.post('/schedule/updateShedule', bodyFormData)
}

export function upcomingMatch(){
  return Api.get('/schedule/upcomingMatch')
}
export function relate(data){
  return Api.get('/schedule/related',{params:{idTour:data.idTour,idTeam1:data.idTeam1,idTeam2:data.idTeam2}})
}

