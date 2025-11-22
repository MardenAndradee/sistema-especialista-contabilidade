package com.mofc.secontabil.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.mofc.secontabil.models.Respostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    RespostasService respostasService;

    public byte[] gerarRelatorio(String nome) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // -----------------------------------------------------------
            // TÍTULO
            // -----------------------------------------------------------
            Paragraph titulo = new Paragraph("Relatório de Diagnóstico Tributário")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();
            document.add(titulo);
            document.add(new Paragraph("\n"));

            // Nome do usuário
            document.add(new Paragraph("Nome da empresa: " + nome)
                    .setFontSize(12)
                    .setBold());
            document.add(new Paragraph("\n"));


            // -----------------------------------------------------------
            // TABELA DE PERGUNTAS E RESPOSTAS
            // -----------------------------------------------------------
            List<Respostas> listarRespostas = respostasService.getAllRespostas();

            float[] colunas = {260F, 260F};
            Table tabela = new Table(colunas);
            tabela.setWidth(520);

            // Cabeçalho
            tabela.addHeaderCell(
                    new Cell().add(new Paragraph("Pergunta").setBold())
                            .setTextAlignment(TextAlignment.CENTER)
            );

            tabela.addHeaderCell(
                    new Cell().add(new Paragraph("Resposta").setBold())
                            .setTextAlignment(TextAlignment.CENTER)
            );

            DecimalFormat df = new DecimalFormat("#,##0.##");

            // Loop de respostas
            for (Respostas r : listarRespostas) {

                String pergunta = r.getPergunta() != null
                        ? r.getPergunta().getPergunta()
                        : "Pergunta não encontrada";

                String respostaBruta = r.getResposta();
                String resposta = respostaBruta;

                // -----------------------------------------------------------
                // TRATAMENTO DE BOOLEAN
                // -----------------------------------------------------------
                if ("true".equalsIgnoreCase(respostaBruta)) {
                    resposta = "Sim";
                } else if ("false".equalsIgnoreCase(respostaBruta)) {
                    resposta = "Não";
                }

                // -----------------------------------------------------------
                // TRATAMENTO DE NOTAÇÃO CIENTÍFICA (Double em String)
                // -----------------------------------------------------------
                else if (respostaBruta.matches("^-?\\d+\\.\\d+E[+-]?\\d+$")) {
                    try {
                        Double valor = Double.valueOf(respostaBruta);
                        resposta = df.format(valor);
                    } catch (Exception ignored) {
                        resposta = respostaBruta;
                    }
                }

                // -----------------------------------------------------------
                // INSERIR LINHA NA TABELA
                // -----------------------------------------------------------
                tabela.addCell(new Cell().add(new Paragraph(pergunta)));
                tabela.addCell(new Cell().add(new Paragraph(resposta)));
            }

            // Adiciona tabela ao documento
            document.add(tabela);

            document.close();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
