package com.resumeplatform.resume_service.service.pdf;


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PdfExtractionServiceImpl
        implements PdfExtractionService {

    @Override
    public String extractText(String filePath) {

        try (PDDocument document =
                     Loader.loadPDF(new File(filePath))) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to extract PDF text");
        }
    }
}