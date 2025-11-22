'use client';

import React, { useState, useEffect } from 'react';
import { perguntasApi } from '../../services/api';
import { Pergunta, RespostaDTO } from '../../types/tipoPerguntas';
import QuestionCard from '../components/QuestionCard';

const Questionnaire: React.FC = () => {
  const [perguntaAtual, setPerguntaAtual] = useState<Pergunta | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [finalizado, setFinalizado] = useState<boolean>(false);
  const [nomeEmpresa, setNomeEmpresa] = useState<string>('');
  const [gerandoPdf, setGerandoPdf] = useState<boolean>(false);

  // Carrega a primeira pergunta ao montar o componente
  useEffect(() => {
    carregarPrimeiraPergunta();
  }, []);

  const carregarPrimeiraPergunta = async () => {
    try {
      setLoading(true);
      setError(null);
      const pergunta = await perguntasApi.getPergunta(1);
      setPerguntaAtual(pergunta);
    } catch (err: any) {
      setError('Erro ao carregar a primeira pergunta. Tente novamente.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleResposta = async (resposta: string | boolean | number) => {
    if (!perguntaAtual) return;

    try {
      setLoading(true);
      setError(null);

      // Monta o DTO baseado no tipo da resposta
      const dto: RespostaDTO = {
        idPergunta: perguntaAtual.id,
      };

      if (typeof resposta === 'number') {
        dto.respostaDouble = resposta;
      } else if (typeof resposta === 'boolean') {
        dto.respostaBoolean = resposta;
      } else {
        dto.respostaString = resposta;
      }

      // Envia a resposta e recebe a próxima pergunta
      const proximaPergunta = await perguntasApi.enviarResposta(dto);

      // Se o backend retornar a pergunta 13 e já respondemos ela, considera finalizado
      if (perguntaAtual.id === 13) {
        setFinalizado(true);
      } else {
        setPerguntaAtual(proximaPergunta);
      }
    } catch (err: any) {
      setError('Erro ao enviar resposta. Tente novamente.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const reiniciarQuestionario = () => {
    setFinalizado(false);
    setPerguntaAtual(null);
    setError(null);
    setNomeEmpresa('');
    carregarPrimeiraPergunta();
  };

  const handleGerarPdf = async () => {
    if (!nomeEmpresa.trim()) {
      setError('Por favor, digite o nome da empresa');
      return;
    }

    try {
      setGerandoPdf(true);
      setError(null);
      const pdfBlob = await perguntasApi.gerarPdf(nomeEmpresa);

      const url = window.URL.createObjectURL(pdfBlob);
      const link = document.createElement('a');
      link.href = url;
      link.download = `relatorio-${nomeEmpresa}.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);
    } catch (err: any) {
      setError('Erro ao gerar PDF. Tente novamente.');
      console.error(err);
    } finally {
      setGerandoPdf(false);
    }
  };

  if (finalizado) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-white to-green-700 p-10 flex items-center justify-center">
        <div className="bg-white rounded-xl p-12 shadow-lg text-center max-w-md w-full">
          <h1 className="text-green-500 text-3xl font-bold mb-4">
            ✓ Questionário Concluído!
          </h1>
          <p className="text-gray-600 text-lg mb-6">
            Todas as suas respostas foram salvas com sucesso.
          </p>

          {error && (
            <div className="bg-red-50 text-red-700 p-3 rounded-lg mb-4 text-sm">
              {error}
            </div>
          )}

          <div className="mb-6">
            <label className="block text-gray-700 font-medium mb-2 text-left">
              Nome da Empresa:
            </label>
            <input
              type="text"
              value={nomeEmpresa}
              onChange={(e) => setNomeEmpresa(e.target.value)}
              placeholder="Digite o nome da empresa"
              className="w-full px-4 py-3 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-green-500 transition-colors text-black"
              disabled={gerandoPdf}
            />
          </div>

          <button
            onClick={handleGerarPdf}
            disabled={gerandoPdf || !nomeEmpresa.trim()}
            className="w-full mb-3 px-8 py-3 bg-green-500 text-white rounded-lg font-semibold hover:bg-green-600 disabled:bg-gray-300 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
          >
            {gerandoPdf ? 'Gerando PDF...' : '📄 Gerar Relatório PDF'}
          </button>

          <button
            onClick={reiniciarQuestionario}
            disabled={gerandoPdf}
            className="w-full px-8 py-3 bg-red-600 text-white rounded-lg font-semibold hover:bg-green-700 disabled:opacity-50 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
          >
            Iniciar Novo Questionário
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-white to-green-600 p-10 flex flex-col items-center">
      <div className="text-center text-white mb-10">
        <h1 className="text-4xl text-black font-bold mb-2">Questionário Contábil</h1>
        <p className="text-lg text-gray-700 opacity-90">
          Responda as perguntas para obter orientações sobre enquadramento tributário
        </p>
      </div>

      {error && (
        <div className="bg-red-50 text-red-700 p-5 rounded-lg mb-6 flex flex-col gap-3 items-center max-w-2xl shadow-md">
          <p>{error}</p>
          <button
            onClick={carregarPrimeiraPergunta}
            className="bg-red-700 text-white px-5 py-2 rounded-md font-semibold hover:bg-red-800 transition-colors"
          >
            Tentar Novamente
          </button>
        </div>
      )}

      {loading && !perguntaAtual && (
        <div className="bg-white p-8 rounded-xl shadow-lg text-gray-600 text-xl">
          Carregando pergunta...
        </div>
      )}

      {perguntaAtual && (
        <QuestionCard
          pergunta={perguntaAtual}
          onSubmit={handleResposta}
          loading={loading}
        />
      )}
    </div>
  );
};

export default Questionnaire;