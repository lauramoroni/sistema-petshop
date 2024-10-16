package com.petshop.petshop_system.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.petshop.petshop_system.services.PdfService;

import java.io.ByteArrayInputStream;

@Controller("/relatorio")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/hemograma/{id_hemograma}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id_hemograma) {
        ByteArrayInputStream pdfStream = pdfService.gerarPdf(id_hemograma);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=hemograma.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
}
