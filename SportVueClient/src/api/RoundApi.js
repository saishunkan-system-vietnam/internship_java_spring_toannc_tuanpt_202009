import Api from './BaseApi';

export function uploadSchedule(round) {
    return Api.post('/round/uploadSchedule',round)
  }