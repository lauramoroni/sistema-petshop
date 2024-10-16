package com.petshop.petshop_system.services;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.petshop.petshop_system.entities.Animal;
import com.petshop.petshop_system.entities.Cliente;
import com.petshop.petshop_system.entities.Hemograma;
import com.petshop.petshop_system.entities.MedVet;
import com.petshop.petshop_system.repositories.HemogramaRepository;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class PdfService {

    private final HemogramaRepository hemogramaRepository;

    public PdfService(HemogramaRepository hemogramaRepository) {
        this.hemogramaRepository = hemogramaRepository;
    }

    public ByteArrayInputStream gerarPdf(Long hemogramaId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Buscar o hemograma e os dados relacionados
        Optional<Hemograma> hemogramaOptional = hemogramaRepository.findById(hemogramaId);
        if (!hemogramaOptional.isPresent()) {
            throw new RuntimeException("Hemograma não encontrado.");
        }

        Hemograma hemograma = hemogramaOptional.get();
        Animal animal = hemograma.getAnimal();
        Cliente cliente = animal.getCliente();
        MedVet medVet = hemograma.getMedVet();

        // Criar o PDF
        PdfWriter writer = new PdfWriter(out);
        Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

        // Adicionar o título do PDF
        document.add(new Paragraph("Relatório de Hemograma"));

        // Informações do Hemograma
        document.add(new Paragraph("Dados do Hemograma:"));
        Table tableHemograma = new Table(2);
        tableHemograma.addCell("Hemácias");
        tableHemograma.addCell(String.valueOf(hemograma.getHemacias()));
        tableHemograma.addCell("Hematócrito");
        tableHemograma.addCell(String.valueOf(hemograma.getHematocrito()));
        tableHemograma.addCell("Hemoglobina");
        tableHemograma.addCell(String.valueOf(hemograma.getHemoglobina()));
        tableHemograma.addCell("Leucócitos");
        tableHemograma.addCell(String.valueOf(hemograma.getLeucocitos()));
        tableHemograma.addCell("Linfócitos");
        tableHemograma.addCell(String.valueOf(hemograma.getLinfocitos()));
        tableHemograma.addCell("Plaquetas");
        tableHemograma.addCell(String.valueOf(hemograma.getPlaquetas()));
        tableHemograma.addCell("Observações");
        tableHemograma.addCell(hemograma.getObservacoes() != null ? hemograma.getObservacoes() : "Nenhuma");
        document.add(tableHemograma);

        // Informações do Animal
        document.add(new Paragraph("Dados do Animal:"));
        Table tableAnimal = new Table(2);
        tableAnimal.addCell("Nome");
        tableAnimal.addCell(animal.getNome());
        tableAnimal.addCell("Idade");
        tableAnimal.addCell(animal.getIdade() + "");
        tableAnimal.addCell("Sexo");
        tableAnimal.addCell(animal.getSexo());
        tableAnimal.addCell("Esterilização");
        tableAnimal.addCell(animal.getEsterilizacao());
        document.add(tableAnimal);

        // Informações do Tutor (Cliente)
        document.add(new Paragraph("Dados do Tutor:"));
        Table tableCliente = new Table(2);
        tableCliente.addCell("Nome");
        tableCliente.addCell(cliente.getNome());
        tableCliente.addCell("CPF");
        tableCliente.addCell(cliente.getCpf());
        document.add(tableCliente);

        // Informações do Médico Veterinário
        document.add(new Paragraph("Dados do Médico Veterinário:"));
        Table tableVet = new Table(2);
        tableVet.addCell("Nome");
        tableVet.addCell(medVet.getNome());
        tableVet.addCell("CRMV");
        tableVet.addCell(medVet.getCrmv());
        document.add(tableVet);

        // Fechar o documento
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
