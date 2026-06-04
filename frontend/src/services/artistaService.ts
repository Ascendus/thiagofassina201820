import api from './api'

export interface ArtistaResponse {
  id: number
  nome: string
  tipo: string
  totalAlbuns: number
}

export interface Page<T> {
  content: T[]
  totalPages: number
  totalElements: number
  number: number
}

export async function listarArtistas(
  nome: string,
  page: number,
  sort: string
): Promise<Page<ArtistaResponse>> {
  const response = await api.get('/v1/artistas', {
    params: { nome, page, size: 10, sort },
  })
  return response.data
}