import axios from 'axios';
import { Pergunta, RespostaDTO } from '../types/tipoPerguntas';

const api = axios.create({
  baseURL: typeof window === 'undefined'
    ? 'http://localhost:8080'
    : 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: false,
  timeout: 10000 // 10 segundos de timeout
});

// Interceptor para logs (ajuda no debug)
api.interceptors.request.use(
  (config) => {
    console.log('📤 Request:', config.method?.toUpperCase(), config.url);
    return config;
  },
  (error) => {
    console.error('❌ Request Error:', error);
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => {
    console.log('✅ Response:', response.status, response.data);
    return response;
  },
  (error) => {
    console.error('❌ Response Error:', error.message);
    if (error.response) {
      console.error('Response data:', error.response.data);
      console.error('Response status:', error.response.status);
    }
    return Promise.reject(error);
  }
);

export const perguntasApi = {
  // Busca uma pergunta específica pelo ID
  getPergunta: async (id: number): Promise<Pergunta> => {
    const response = await api.get<Pergunta>(`/perguntas/${id}`);
    return response.data;
  },

  // Envia resposta e recebe a próxima pergunta
  enviarResposta: async (respostaDTO: RespostaDTO): Promise<Pergunta> => {
    const response = await api.post<Pergunta>('/resposta', respostaDTO);
    return response.data;
  },

  // Busca todas as respostas salvas (opcional)
  getRespostas: async () => {
    const response = await api.get('/respostas');
    return response.data;
  },

  gerarPdf: async (nome: string): Promise<Blob> => {
    const response = await api.get<Blob>('/pdf/gerar', {
      params: { nome },
      responseType: 'blob'
    });
    return response.data;
  },

};

export default api;