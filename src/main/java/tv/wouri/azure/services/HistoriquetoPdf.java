package tv.wouri.azure.services;

import tv.wouri.azure.models.THistoriqueMutualiste;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.jsonwebtoken.io.IOException;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;

public class HistoriquetoPdf {

    private List<THistoriqueMutualiste> listData;

    public HistoriquetoPdf(List<THistoriqueMutualiste> listData) {
        this.listData = listData;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Numéro", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Libellé", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Débit", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Crédit", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Solde", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (THistoriqueMutualiste data : listData) {
            table.addCell(String.valueOf(data.getTMNUMERO()));
            table.addCell(data.getTMDATE() != null ? data.getTMDATE().toString() : "");
            table.addCell(data.getTMLIBELLE() != null ? data.getTMLIBELLE() : "");
            table.addCell(data.getTMCREDIT() != null ? String.valueOf(data.getTMDEBIT()) : "");
            table.addCell(data.getTMCREDIT() != null ? String.valueOf(data.getTMCREDIT()) : "");
            table.addCell(data.getSOLDE() != null ? String.valueOf(data.getSOLDE()) : "");
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException, java.io.IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.GRAY);

        Paragraph p = new Paragraph("Historique des mouvements", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 1.0f, 3.5f, 1.5f, 1.5f,1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
