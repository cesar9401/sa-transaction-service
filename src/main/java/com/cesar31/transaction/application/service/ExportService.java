package com.cesar31.transaction.application.service;

import com.cesar31.transaction.application.exception.ApplicationException;
import com.cesar31.transaction.application.ports.input.ExportUseCase;
import com.cesar31.transaction.application.ports.input.SaleUseCase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExportService implements ExportUseCase {

    private final SaleUseCase saleUseCase;

    public ExportService(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @Override
    public byte[] getTopOrganizationByIncome(LocalDateTime start, LocalDateTime end) throws ApplicationException {
        var topOrgs = saleUseCase.getTopOrganizationByIncome(start, end);
        var document = new Document();
        var baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            for(var org : topOrgs) {
                exportObjectToPdf(document, org);
                document.add(new Paragraph("\n"));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new ApplicationException(e.getMessage());
        }

        return baos.toByteArray();
    }

    @Override
    public byte[] getTransactionByClient(UUID clientId, UUID organizationId, LocalDateTime start, LocalDateTime end) throws ApplicationException {
        var transactions = saleUseCase.getTransactionByClient(clientId, organizationId, start, end);
        var document = new Document();
        var baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            for (var transaction : transactions) {
                exportObjectToPdf(document, transaction);
                document.add(new Paragraph("\n"));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new ApplicationException(e.getMessage());
        }
        return baos.toByteArray();
    }

    private void exportObjectToPdf(Document document, Object obj) throws DocumentException {
        var fields = obj.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                document.add(new Paragraph(field.getName() + ": " + value));
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
