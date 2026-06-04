import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080',
})

// Interceptor de REQUISIÇÃO — injeta o token em toda chamada
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('accessToken')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

// Interceptor de RESPOSTA — trata o 401 (token expirado)
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config

    // Se veio 401 e ainda não tentamos o refresh
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true

      const refreshToken = localStorage.getItem('refreshToken')

      if (!refreshToken) {
        window.location.href = '/login'
        return Promise.reject(error)
      }

      try {
        const { data } = await api.post('/v1/auth/refresh', { refreshToken })
        localStorage.setItem('accessToken', data.accessToken)
        originalRequest.headers.Authorization = `Bearer ${data.accessToken}`
        return api(originalRequest)
      } catch {
        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
        window.location.href = '/login'
        return Promise.reject(error)
      }
    }

    return Promise.reject(error)
  }
)

export default api