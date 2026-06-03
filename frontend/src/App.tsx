import { BrowserRouter, Routes, Route } from "react-router-dom"
import LoginPage from "./pages/LoginPage"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/artistas" element={<h1>Tela de Artistas</h1>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App