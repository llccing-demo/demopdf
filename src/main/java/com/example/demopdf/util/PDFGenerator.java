package com.example.demopdf.util;

import com.example.demopdf.entity.Student;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class PDFGenerator {
    private List<Student> studentList;

    Logger log = LoggerFactory.getLogger(PDFGenerator.class);

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PDFWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Crating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("List of Student", fontTitle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);

        // Creating a table of 3 columns
        PdfPTable table = new PdfPTable(3);

        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3});
        table.setSpacingBefore(5);

        // Create table Cells for table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding
        cell.setBackgroundColor(CMYKColor.MAGENTA);
        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell / header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Student Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Section", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (Student student : studentList) {
            // Adding student id
            table.addCell(String.valueOf(student.getId()));
            // Adding student name
            table.addCell(student.getName());
            // Adding student section
            table.addCell(student.getSection());
        }

        // Adding the created table to document
        document.add(table);

        document.close();
    }

    public void generateByTxt() {

        FileInputStream fis =null;
        DataInputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        File sourceFile = new File("C:\\Users\\cliu3\\Downloads\\sample-list.txt");
        File destFile = new File("C:\\Users\\cliu3\\Downloads\\sample-list.pdf");

        try {
            Document document = new Document(PageSize.A4.rotate());

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(destFile));
            document.open();

            BaseFont courier = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);
            Font myFont = new Font(courier);

            Font boldFont = new Font();
            boldFont.setStyle(Font.BOLD);
            boldFont.setSize(10);

            myFont.setStyle(Font.NORMAL);
            myFont.setSize(9);

            document.add(new Paragraph("\n"));

            if (sourceFile.exists()) {
                fis = new FileInputStream(sourceFile);
                in = new DataInputStream(fis);
                isr = new InputStreamReader(in);
                br = new BufferedReader(isr);
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    Paragraph paragraph = new Paragraph(strLine + "\n", myFont);
                    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                    document.add(paragraph);
                }
                log.debug("Txt file converted to pdf successfully!");
            } else {
                log.error("No file exist!");
            }
            document.close();
        } catch (DocumentException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fis !=null) {
                    fis.close();
                }
                if (in != null) {
                    in.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
    }
}
