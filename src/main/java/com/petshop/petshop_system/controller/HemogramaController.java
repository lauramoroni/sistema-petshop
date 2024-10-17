package com.petshop.petshop_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lowagie.text.DocumentException;
import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.services.AnimalService;
import com.petshop.petshop_system.services.HemogramaService;
import com.petshop.petshop_system.services.MedVetService;
import com.petshop.petshop_system.services.PdfService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/exame")
public class HemogramaController {

    @Autowired
    HemogramaService hemogramaService;
    @Autowired
    AnimalService animalService;
    @Autowired
    MedVetService medVetService;
    @Autowired
    PdfService pdfService;

    // Página de adicionar um hemograma
    @GetMapping("/{crmv}/{id_animal}")
    public String formularioCadastro(@PathVariable Long id_animal, @PathVariable String crmv, Model model) {

        Animal animal = animalService.findById(id_animal);
        MedVet medVet = medVetService.FindByCRMV(crmv);

        model.addAttribute("animal", animal);
        model.addAttribute("medVet", medVet);
        model.addAttribute("hemograma", new Hemograma());

        return "animais/exame_form";
    }

    // Método para processar o formulário do hemograma
    @PostMapping("/{crmv}/{id_animal}")
    public String salvarExame(@PathVariable Long id_animal, @ModelAttribute("hemograma") Hemograma hemograma,
            @PathVariable String crmv, Model model) {

        Animal animal = animalService.findById(id_animal);
        MedVet medvet = medVetService.FindByCRMV(crmv);

        if (animal != null && medvet != null) {
            // Associa o hemograma ao animal e ao veterinário
            hemograma.setAnimal(animal);
            hemograma.setMedVet(medvet);

            hemogramaService.insert(hemograma);

            // Adiciona o hemograma à lista de hemogramas do animal
            animal.getHemogramas().add(hemograma);

            // Redireciona para a página de detalhes do animal
            return "redirect:/animal/" + medvet.getCrmv() + "/" + id_animal;
        }

        // Caso o animal ou veterinário não sejam encontrados, volta ao formulário com
        // uma mensagem de erro
        model.addAttribute("error", "Animal ou Veterinário não encontrados.");
        return "animais/exame_form";
    }

    @GetMapping("/download-pdf/{id}")
    public void downloadPDFResource(@PathVariable("id") Long id_hemograma, HttpServletResponse response) {
        try {
            // Gera o arquivo PDF com base no hemograma solicitado
            File pdfFile = pdfService.generatePdf(id_hemograma);

            // Configura o cabeçalho da resposta para download
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=hemograma_" + id_hemograma + ".pdf");

            // Envia o arquivo PDF no fluxo de resposta
            try (InputStream inputStream = new FileInputStream(pdfFile);
                    OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.flush();
            }

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            // Tratamento de erro (opcional, ex: redirecionar para uma página de erro)
        }
    }

    @GetMapping("/teste/{id}")
    public String teste(@PathVariable("id") Long id_hemograma, Model model) {

        Hemograma hemograma = hemogramaService.findById(id_hemograma);

        model.addAttribute("hemograma", hemograma);

        return "animais/pdf_exame";
    }
}
