import Api from './BaseApi';

export function login(user) {
    return Api.post('/account/login', user);
}

export function userRegister(register) {
    return Api.post('/account/signup', register);
}

export function searchUser(params) {
    return Api.get('/account/search', {
        params: {
            page: params.page,
            pageSize: params.pageSize,
            nameSearch: params.nameSearch,
            type: params.type,
        },
    })
}

export function getAll() {
    return Api.get('/account/getAll')
}

export function getById(id) {
    return Api.get('/account/getById/' + id);
}

export function autoLogin() {
    return Api.post('/account/autoLogin');
}

export function updateProfileUser(bodyFormData) {
    return Api.post('/profiles/update', bodyFormData)
}

export function findByEmailUser(email) {
    return Api.get('/account/getByMail', { params: { email: email } })
}

export function forgetEmail(email) {
    return Api.post(`/account/forget/${email}`)
}

