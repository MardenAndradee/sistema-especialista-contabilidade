'use client';

import React, { useState } from 'react';
import { Pergunta, TipoPergunta, TIPOS_PERGUNTAS, OPCOES_ATIVIDADE, OPCOES_JURIDICO } from '../../types/tipoPerguntas';

interface QuestionCardProps {
  pergunta: Pergunta;
  onSubmit: (resposta: string | boolean | number) => void;
  loading: boolean;
}

const QuestionCard: React.FC<QuestionCardProps> = ({ pergunta, onSubmit, loading }) => {
  const [valor, setValor] = useState<string>('');
  const tipoPergunta: TipoPergunta = TIPOS_PERGUNTAS[pergunta.id];

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (tipoPergunta === 'number') {
      onSubmit(parseFloat(valor));
    } else if (tipoPergunta === 'boolean') {
      // Para boolean, o valor já vem do botão clicado
    } else {
      onSubmit(valor);
    }
  };

  const handleBooleanClick = (respostaBoolean: boolean) => {
    onSubmit(respostaBoolean);
  };

  const renderInput = () => {
    switch (tipoPergunta) {
      case 'number':
        return (
          <div className="flex flex-col gap-3">
            <label className="text-gray-600 font-medium text-sm">Valor em R$:</label>
            <input
              type="number"
              value={valor}
              onChange={(e) => setValor(e.target.value)}
              placeholder="Digite o valor"
              required
              min="0"
              step="0.01"
              className="px-4 py-3 border-2 border-gray-200 rounded-lg text-base text-black focus:outline-none focus:border-green-500 transition-colors"
            />
            <button 
              type="submit" 
              disabled={loading || !valor}
              className="mt-2 px-6 py-3 bg-green-500 text-white rounded-lg font-semibold hover:bg-green-600 disabled:bg-gray-300 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
            >
              {loading ? 'Enviando...' : 'Próxima'}
            </button>
          </div>
        );

      case 'boolean':
        return (
          <div className="flex gap-4 mt-2">
            <button
              type="button"
              onClick={() => handleBooleanClick(true)}
              disabled={loading}
              className="flex-1 px-6 py-3 bg-green-500 text-white rounded-lg font-semibold hover:bg-green-600 disabled:opacity-60 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
            >
              Sim
            </button>
            <button
              type="button"
              onClick={() => handleBooleanClick(false)}
              disabled={loading}
              className="flex-1 px-6 py-3 bg-red-500 text-white rounded-lg font-semibold hover:bg-red-600 disabled:opacity-60 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
            >
              Não
            </button>
          </div>
        );

      case 'select-atividade':
        return (
          <div className="flex flex-col gap-3">
            <label className="text-gray-600 font-medium text-sm">Selecione a atividade:</label>
              <select
              value={valor}
              onChange={(e) => setValor(e.target.value)}
              required
              className="px-4 py-3 border-2 border-gray-200 rounded-lg text-base focus:outline-none focus:border-green-500 transition-colors"
            >
              <option value="">-- Selecione --</option>
              {OPCOES_ATIVIDADE.map((opcao: string) => (
                <option key={opcao} value={opcao}>
                  {opcao}
                </option>
              ))}
            </select>
            <button 
              type="submit" 
              disabled={loading || !valor}
              className="mt-2 px-6 py-3 bg-green-500 text-white rounded-lg font-semibold hover:bg-green-600 disabled:bg-gray-300 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
            >
              {loading ? 'Enviando...' : 'Próxima'}
            </button>
          </div>
        );

      case 'select-juridico':
        return (
          <div className="flex flex-col gap-3">
            <label className="text-gray-600 font-medium text-sm">Selecione o tipo jurídico:</label>
              <select
              value={valor}
              onChange={(e) => setValor(e.target.value)}
              required
              className="px-4 py-3 border-2 border-gray-200 rounded-lg text-base focus:outline-none focus:border-green-500 transition-colors"
            >
              <option value="">-- Selecione --</option>
              {OPCOES_JURIDICO.map((opcao: string) => (
                <option key={opcao} value={opcao}>
                  {opcao}
                </option>
              ))}
            </select>
            <button 
              type="submit" 
              disabled={loading || !valor}
              className="mt-2 px-6 py-3 bg-green-500 text-white rounded-lg font-semibold hover:bg-green-600 disabled:bg-gray-300 disabled:cursor-not-allowed transition-all hover:shadow-lg hover:-translate-y-0.5"
            >
              {loading ? 'Enviando...' : 'Próxima'}
            </button>
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <div className="bg-white rounded-xl p-8 shadow-lg max-w-2xl w-full mx-auto">
      <h2 className="text-gray-800 text-xl font-semibold mb-4">
        Pergunta {pergunta.id}
      </h2>
      <p className="text-gray-600 text-lg mb-6 leading-relaxed">
        {pergunta.pergunta}
      </p>
      
      <form onSubmit={handleSubmit}>
        {renderInput()}
      </form>
    </div>
  );
};

export default QuestionCard;