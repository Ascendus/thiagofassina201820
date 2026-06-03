import { useState } from "react"

function LoginPage() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    function handleLogin() {
        console.log('usuario:', username)
        console.log('senha:', password)
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
                </div>
                <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700" onClick={handleLogin} >Entrar</button>
            </div>
        </div>
    )
}

export default LoginPage