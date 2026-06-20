import { useState } from "react"
import { login } from '../services/authService'
import { useNavigate } from 'react-router-dom'


function LoginPage() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [erro, setErro] = useState('')
    const navigate = useNavigate()
    
    async function handleLogin() {
        try {
            const data = await login(username, password)
            localStorage.setItem('accessToken', data.accessToken)
            localStorage.setItem('refreshToken', data.refreshToken)
            navigate('/artistas')
        } catch (error: any) {
            setErro(error.response?.data?.erro || 'Erro ao fazer login')
        }
    }

    return (
        <div className="min-h-screen bg-gray-100 flex items-center justify-center">
            <div className="bg-white p-8 rounded shadow-md w-full max-w-sm">
                <h1 className="text-2xl font-bold text-center mb-6" >Login</h1>
                <div className="mb-4">
                    <label className="block text-sm font-medium mb-1">Usuário</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        placeholder="Digite seu usuário"
                    />
                </div>
                <div className="mb-6">
                    <label className="block text-sm font-medium mb-1">Senha</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        placeholder="Digite sua senha"
                    />
                    {erro && <p className="text-red-500 text-sm mb-4">{erro}</p>}
                </div>
                <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700" onClick={handleLogin} >Entrar</button>
            </div>
        </div>
    )
}

export default LoginPage