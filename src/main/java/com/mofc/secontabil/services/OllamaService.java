package com.mofc.secontabil.services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class OllamaService {

    public String gerarResumo(String promptOriginal) {

        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // ESCAPA \n, " e \\
            String prompt = promptOriginal
                    .replace("\\", "\\\\")   // barras
                    .replace("\"", "\\\"")   // aspas
                    .replace("\n", "\\n");   // quebras de linha

            // JSON correto
            String jsonInput = "{"
                    + "\"model\":\"llama3\","
                    + "\"prompt\":\"" + prompt + "\","
                    + "\"stream\": false"
                    + "}";

            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

            int status = con.getResponseCode();

            if (status != 200) {
                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(con.getErrorStream())
                );
                StringBuilder errorText = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorText.append(line);
                }
                throw new RuntimeException("Erro no Ollama: HTTP " + status + " - " + errorText);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            // Pega o campo "response"
            String resp = response.toString();

            int start = resp.indexOf("\"response\":\"");
            if (start != -1) {
                start += 12;
                int end = resp.indexOf("\"", start);
                return resp.substring(start, end).replace("\\n", "\n");
            }

            return resp;

        } catch (Exception e) {
            return "Erro ao chamar Ollama: " + e.getMessage();
        }
    }
}
