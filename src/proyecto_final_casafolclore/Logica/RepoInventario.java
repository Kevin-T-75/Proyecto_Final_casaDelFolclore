/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final_casafolclore.Logica;

import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.itextpdf.text.Document;

/**
 *
 * @author OS
 */
public class RepoInventario {
    
    public void GenerarReporte(JTable j){
        
             
    try {
    Document documento = new Document();
    JFileChooser guardar = new JFileChooser();
    guardar.setSelectedFile(new File("ReporteInventario.pdf"));

    if (guardar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

        PdfWriter.getInstance(documento,
                new FileOutputStream(guardar.getSelectedFile()));

        documento.open();

        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

        Paragraph p = new Paragraph("REPORTE DE INVENTARIO\n\n", titulo);
        p.setAlignment(Element.ALIGN_CENTER);

        documento.add(p);
        
        Font empresa = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
        Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 11);

        // Título de la empresa
        Paragraph p1 = new Paragraph("CASA FOLCLORE", empresa);
        p1.setAlignment(Element.ALIGN_CENTER);
        documento.add(p1);

        // Subtítulo
        Paragraph p2 = new Paragraph("Reporte de Inventario", subtitulo);
        p2.setAlignment(Element.ALIGN_CENTER);
        documento.add(p2);

        // Fecha
        Paragraph p3 = new Paragraph("Fecha: " + java.time.LocalDate.now(), normal);
        p3.setAlignment(Element.ALIGN_RIGHT);
        documento.add(p3);

        // Espacio
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(j.getColumnCount());

        // Encabezados
        for (int i = 0; i < j.getColumnCount(); i++) {

            tabla.addCell(j.getColumnName(i));

        }

        // Datos
        for (int fila = 0; fila < j.getRowCount(); fila++) {

            for (int columna = 0; columna < j.getColumnCount(); columna++) {

                Object valor = j.getValueAt(fila, columna);

                tabla.addCell(valor.toString());

            }

        }

        documento.add(tabla);

        documento.close();

        JOptionPane.showMessageDialog(null,"Reporte generado correctamente.");

    }

} catch (Exception e) {

    JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());

}
    }
    
}
    
