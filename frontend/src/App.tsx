import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom"
import LoginPage from "./pages/LoginPage"
import ArtistasPage from "./pages/ArtistasPage"

function RotaProtegida({ children }: { children: React.ReactNode }) {
  const token = localStorage.getItem('accessToken')
  if (!token) {
    return <Navigate to="/login" replace />
  }
  return <>{children}</>
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/artistas" element={
          <RotaProtegida>
            <ArtistasPage />
          </RotaProtegida>
        } />
      </Routes>
    </BrowserRouter>
  )
}

export default App