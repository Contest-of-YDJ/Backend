package com.example.demo.api.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pdf")
public class AccidentController {
    ResourceLoader resourceLoader;
    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> showPdfAsImage(@PathVariable String filename) {
        try {
            // Load the PDF file from the classpath
            ClassPathResource resource = new ClassPathResource("/accident.pdf");
            PDDocument document = PDDocument.load(resource.getFile());

            // Render the first page of the PDF as an image
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 100, ImageType.RGB);

            // Convert the image to bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            // Construct the URL for redirection
            URI redirectUri = new URI("http://localhost:8080/pdf/download/accident.pdf");

            // Create an HTML response with an image tag and redirection script
            String htmlResponse = "<html><body><a href=\"" + redirectUri + "\">";
            htmlResponse += "<img src=\"data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes) + "\" />";
            htmlResponse += "</a><script>window.location.href = \"" + redirectUri + "\";</script></body></html>";

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);

            // Return the HTML response
            return new ResponseEntity<>(htmlResponse.getBytes(), headers, HttpStatus.OK);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }

    @Autowired
    public AccidentController (ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable String filename) {
        // PDF 파일 로드
        Resource resource = new ClassPathResource(filename);

        // 파일이 존재하는지 확인
        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

            // PDF 파일을 응답으로 반환
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



//    @GetMapping("/translate/{pdfFileName}/{targetLanguage}")
//    public ResponseEntity<byte[]> translatePdf
//            (
//            @PathVariable String pdfFileName,
//            @PathVariable String targetLanguage
//    ) throws IOException {
//        String pdfFilePath = "src/main/resources/" + pdfFileName + ".pdf";
//
//        byte[] translatepdf = translationService.translatePdf(pdfFilePath, targetLanguage);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("attachment","translated_"+pdfFileName+".pdf");
//        headers.setContentLength(translatepdf.length);
//
//        return new ResponseEntity<>(translatepdf,headers, HttpStatus.OK);
//    }


}
