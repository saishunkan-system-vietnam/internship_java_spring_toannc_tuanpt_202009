import axios from 'axios';
import store from '@/store';

const instance = axios.create({
    baseURL: 'http://localhost:8090/api/v1'
});

instance.interceptors.request.use(request => {
    request.headers.Authorization = store.state.auth != undefined ? store.state.auth.token : '';
    return request;
}, error => {
    // console.log(error);
    return Promise.reject(error);
});

instance.interceptors.response.use(response => {
    return response;
}, error => {
    return Promise.reject(error);
});
export default instance;