package com.petshop.petshop_system.services;

import com.lowagie.text.DocumentException;
import com.petshop.petshop_system.entities.Hemograma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    @Autowired
    private HemogramaService hemogramaService;
    @Autowired
    private TemplateEngine templateEngine;

    public PdfService(HemogramaService hemogramaService, SpringTemplateEngine templateEngine) {
        this.hemogramaService = hemogramaService;
        this.templateEngine = templateEngine;
    }

    public File generatePdf(Long id_hemograma) throws IOException, DocumentException {
        Context context = getContext(id_hemograma);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private Context getContext(Long id_hemograma) {
        Context context = new Context();
        // Busca o hemograma específico
        Hemograma hemograma = hemogramaService.findById(id_hemograma);
        context.setVariable("hemograma", hemograma);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        // Certifique-se de que você tenha um template para o PDF na pasta correta
        return templateEngine.process("animais/pdf_exame", context);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("hemograma", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            ITextRenderer renderer = new ITextRenderer();

            // Definir a base URL para os recursos (CSS, imagens)
            String baseUrl = new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm();

            // Definir o documento HTML e a base URL para os recursos
            renderer.setDocumentFromString(html, baseUrl);

            // Renderizar o layout e criar o PDF
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        file.deleteOnExit();
        return file;
    }

}
