import { useEffect, useState } from 'react'
import { listarArtistas, type ArtistaResponse, type Page } from '../services/artistaService'

function ArtistasPage() {
  const [artistas, setArtistas] = useState<ArtistaResponse[]>([])
  const [totalPages, setTotalPages] = useState(0)
  const [page, setPage] = useState(0)
  const [nome, setNome] = useState('')
  const [sort, setSort] = useState('nome,asc')

  useEffect(() => {
    buscar()
  }, [page, sort])

  async function buscar() {
    const data: Page<ArtistaResponse> = await listarArtistas(nome, page, sort)
    setArtistas(data.content)
    setTotalPages(data.totalPages)
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <h1 className="text-3xl font-bold mb-6">Artistas</h1>

      {/* Busca e ordenação */}
      <div className="flex gap-4 mb-6">
        <input
          type="text"
          placeholder="Buscar por nome..."
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          onKeyDown={(e) => e.key === 'Enter' && buscar()}
          className="border rounded px-3 py-2 w-full max-w-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <select
          value={sort}
          onChange={(e) => { setSort(e.target.value); setPage(0) }}
          className="border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          <option value="nome,asc">Nome A-Z</option>
          <option value="nome,desc">Nome Z-A</option>
        </select>
      </div>

      {/* Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 mb-6">
        {artistas.map((artista) => (
          <div key={artista.id} className="bg-white rounded shadow p-4">
            <h2 className="text-lg font-semibold">{artista.nome}</h2>
            <p className="text-sm text-gray-500">{artista.tipo}</p>
            <p className="text-sm text-gray-700 mt-2">{artista.totalAlbuns} álbum(ns)</p>
          </div>
        ))}
      </div>

      {/* Paginação */}
      <div className="flex gap-2 justify-center">
        <button
          onClick={() => setPage(page - 1)}
          disabled={page === 0}
          className="px-4 py-2 bg-white border rounded disabled:opacity-50"
        >
          Anterior
        </button>
        <span className="px-4 py-2">Página {page + 1} de {totalPages}</span>
        <button
          onClick={() => setPage(page + 1)}
          disabled={page + 1 >= totalPages}
          className="px-4 py-2 bg-white border rounded disabled:opacity-50"
        >
          Próxima
        </button>
      </div>
    </div>
  )
}

export default ArtistasPage