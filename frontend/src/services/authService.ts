import api from "./api";

export async function login(username: string, password: string) {
    const response = await api.post('/v1/auth/login', {
        username,
        password,
    })
    return response.data

}