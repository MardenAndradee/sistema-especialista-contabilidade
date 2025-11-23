# 📊 Sistema Especialista Contabilidade

## 📌 Descrição e Objetivo
O **sistema-especialista-contabilidade** tem como objetivo auxiliar empresas a identificarem seu enquadramento tributário e obrigações fiscais.  
Através de uma série de **perguntas especialistas**, o sistema coleta informações e gera um **relatório completo**, com recomendações e alertas importantes.  

A interface é **moderna e interativa**, adaptando o fluxo das perguntas conforme as respostas fornecidas pelo usuário.

---

## 🔄 Fluxo de Funcionamento

1. 📂 Banco de dados inicial criado via **script JSON**, contendo perguntas e condições.  
2. 🖥️ O **Frontend** exibe as perguntas para o usuário, uma de cada vez.  
3. 📡 O usuário envia a resposta ao **Backend** via requisição **REST**.  
4. ⚙️ O **Backend (Spring Boot)** processa a resposta, aplica as regras do sistema especialista e retorna a próxima pergunta.  
5. 🔁 Esse ciclo se repete até que não haja mais perguntas.  
6. 📝 O **relatório final** é gerado com todas as informações coletadas, sugestões fiscais e alertas importantes.  
7. 📑 O relatório pode ser **exportado em PDF** pelo usuário.  

---

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java Spring Boot  
- **Frontend:** Next.js + React + TypeScript  
- **Banco de Dados Inicial:** H2 em memória
- **Exportação de Relatórios:** PDF  

---

## 📥 Instalação

Clone o repositório:
```bash
git clone https://github.com/MardenAndradee/sistema-especialista-contabilidade
cd sistema-especialista-contabilidade
```

### Backend (Java Spring Boot)
O backend está na pasta:
```
src/main/java
```
Abra o projeto no **IntelliJ IDEA** (ou sua IDE favorita) e inicie a aplicação Spring Boot.  

### Frontend (Next.js + React + TS)
O frontend está na pasta:
```
src/main/frontend
```

Instale as dependências:
```bash
cd src/main/frontend
npm install
```

---

## ⚙️ Requisitos

- [Node.js](https://nodejs.org/) instalado (para rodar o frontend)  
- Java 17+ (para rodar o backend Spring Boot)  
- IDE recomendada: IntelliJ IDEA (para backend)  

---

## 🚀 Como Usar

### Iniciar Backend
Pela sua IDE, execute o projeto Spring Boot. Isso irá disponibilizar a **API REST**.  

### Iniciar Frontend
No terminal:
```bash
cd src/main/frontend
npm run dev
```
O frontend será iniciado em `http://localhost:3000` 🎉  

---

##  🧠 Instalação do Ollama (para uso da IA local)

Para gerar resumos e análises contábeis no sistema, utilizamos o Ollama, que permite rodar modelos de IA localmente, sem custos e sem necessidade de internet.

A instalação é simples e compatível com Linux, macOS e Windows.

##  📥 Instalação no Linux/macOS (via script .sh)

Execute o comando abaixo no terminal:

curl -fsSL https://ollama.com/install.sh | sh


Após a instalação, verifique se o Ollama está funcionando:

ollama --version

##  🧩 Baixar o modelo necessário

Este projeto utiliza o modelo Llama 3, que é leve e funciona muito bem para resumos e processamento de texto.

Para baixar:

ollama pull llama3


Depois teste:

ollama run llama3


Se aparecer um prompt aguardando interação → está funcionando ✔️

##  🪟 Instalação no Windows

No Windows, basta baixar o instalador oficial:

🔗 https://ollama.com/download/windows

Após a instalação, confirme que está funcionando:

ollama --version


E baixe o modelo:

ollama pull llama3

## 🤝 Como Contribuir

1. Crie uma branch para sua feature ou correção:  
   ```bash
   git checkout -b minha-branch
   ```
2. Faça suas alterações e commit:  
   ```bash
   git commit -m "Minha contribuição"
   ```
3. Envie sua branch para o repositório remoto:  
   ```bash
   git push origin minha-branch
   ```
4. Abra um **Pull Request (PR)** no GitHub e aguarde a revisão 🚀  

---

## 📄 Licença
Este projeto está sob a licença MIT.  
Sinta-se livre para usar, modificar e contribuir!  
