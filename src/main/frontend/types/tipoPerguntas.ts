export interface Pergunta {
  id: number;
  pergunta: string;
}

export interface RespostaDTO {
  idPergunta: number;
  respostaString?: string;
  respostaBoolean?: boolean;
  respostaDouble?: number;
}

export type TipoPergunta = 
  | 'number'    // Pergunta 1
  | 'boolean'   // Perguntas 2, 4, 5, 6, 7, 8, 9, 10, 11, 13
  | 'select-atividade'  // Pergunta 3
  | 'select-juridico';  // Pergunta 12

export const TIPOS_PERGUNTAS: Record<number, TipoPergunta> = {
  1: 'number',
  2: 'boolean',
  3: 'select-atividade',
  4: 'boolean',
  5: 'boolean',
  6: 'boolean',
  7: 'boolean',
  8: 'boolean',
  9: 'boolean',
  10: 'boolean',
  11: 'boolean',
  12: 'select-juridico',
  13: 'boolean'
};

export const OPCOES_ATIVIDADE = [
  'Comércio',
  'Serviços',
  'Industria',
  'Misto'
];

export const OPCOES_JURIDICO = [
  'MEI',
  'EIRELI',
  'LTDA',
  'S/A',
  'Outros'
];