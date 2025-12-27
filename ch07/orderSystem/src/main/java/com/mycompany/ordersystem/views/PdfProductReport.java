package com.mycompany.ordersystem.views;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.ordersystem.domain.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.Base64;
import java.util.List;
import java.util.Map;

public class PdfProductReport extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> products = (List<Product>) model.get("products");
        String gothic = "font/NanumGothic.otf";
        String myeongjo = "font/NanumMyeongjo.otf";
        BaseFont tf = BaseFont.createFont(gothic, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf = BaseFont.createFont(myeongjo, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font titleFont = new Font(tf, 16);
        Font headerFont = new Font(tf, 12);
        Font bodyFont = new Font(bf, 12);
        Paragraph title = new Paragraph("제품 목록", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        Table table = new Table(3);
        table.addCell(new Paragraph("제품명", headerFont));
        table.addCell(new Paragraph("가격", headerFont));
        table.addCell(new Paragraph("제품 설명", headerFont));

        for (Product product : products) {
            table.addCell(new Paragraph(product.getName(), bodyFont));
            table.addCell(new Paragraph(String.valueOf(product.getPrice()), bodyFont));
            table.addCell(new Paragraph(product.getDescription(), bodyFont));
        }
        document.add(table);
    }
}
