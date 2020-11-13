package homework.fanaout;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PdfGenerator {
    //Макет для всех pdf файлов
    final static String SRC = "C:\\StudyProjects\\JavaLab\\1. RabbitMQ\\src\\main\\resources\\templates\\form_template.pdf";

    public static void generatePdf(User user, Application application) {
        if (application.getTitle().equals("Отпуск")) {
            try {
                //читаем макет и создаем по примеру pdf
                PdfDocument pdfDoc = new PdfDocument(new PdfReader(PdfGenerator.SRC), new PdfWriter("results/holiday/" + application.getId() + ".pdf"));
                pdfDoc.addNewPage();
                Document document = new Document(pdfDoc);
                PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
                //Получаем поля
                Map<String, PdfFormField> fields = form.getFormFields();
                //Инициализируем поля
                PdfFormField name = fields.get("user.info");
                name.setJustification(PdfFormField.ALIGN_CENTER);
                name.setValue(user.getName() + " " + user.getSurname());
                PdfFormField age = fields.get("user.age");
                age.setJustification(PdfFormField.ALIGN_CENTER);
                age.setValue(user.getAge().toString());

                PdfFormField formId = fields.get("form.id");
                formId.setJustification(PdfFormField.ALIGN_CENTER);
                formId.setValue(application.getId().substring(0, 5));

                PdfFormField formDestination = fields.get("form.destination");
                formDestination.setJustification(PdfFormField.ALIGN_CENTER);
                formDestination.setValue(application.getTitle());

                PdfFormField formDate = fields.get("form.date");
                formDate.setJustification(PdfFormField.ALIGN_CENTER);
                formDate.setValue(application.getDate());

                pdfDoc.close();
                System.out.println("Распечатал " + user + " " + application.getId());


            } catch (IOException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }
        } else {
            try {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setDirectoryForTemplateLoading(new File("C:\\StudyProjects\\JavaLab\\1. RabbitMQ\\src\\main\\resources\\templates"));
                Map<String, Object> root = new HashMap<>();
                root.put("user_name", user.getName());
                root.put("user_surname", user.getSurname());
                root.put("user_age", user.getAge());
                root.put("form_id", application.getId().substring(0, 5));
                root.put("form_date", application.getDate());
                Template temp = cfg.getTemplate("leave_template.ftl");
                Writer writer = new FileWriter("results/leave_html/" + application.getId() + ".html");
                temp.process(root, writer);
                System.out.println("Распечатал");
                File htmlSource = new File("results/leave_html/" + application.getId() + ".html");
                File pdfDest = new File("results/leave/"+ UUID.randomUUID().toString()+".pdf");
                // pdfHTML specific code
                ConverterProperties converterProperties = new ConverterProperties();
                HtmlConverter.convertToPdf(new FileInputStream(htmlSource),
                        new FileOutputStream(pdfDest), converterProperties);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
