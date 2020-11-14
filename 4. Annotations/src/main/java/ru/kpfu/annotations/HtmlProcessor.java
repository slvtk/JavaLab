package ru.kpfu.annotations;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"*"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        // получить типы с аннотацией HtmlField
        Set<? extends Element> annotatedFields = roundEnv.getElementsAnnotatedWith(HtmlInput.class);
//        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Count of classes: " + annotatedElements.size());
        for (Element element : annotatedElements) {
            // получаем путь с class-файлам
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // создадим путь к html-файлу
            // User.class -> User.ftlh
            path = path.substring(1) + element.getSimpleName().toString() + ".ftlh";
            // формируем путь для записи данных
            Path out = Paths.get(path);

            try {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
                Template temp = cfg.getTemplate("template.ftl");
                Map<String, Object> root = new HashMap<>();
                Writer writer1 = new FileWriter("src/main/resources/result.html");
                HtmlForm annotationForm = element.getAnnotation(HtmlForm.class);
                root.put("action", annotationForm.action());
                root.put("method", annotationForm.method());
                List<String> annotation = new ArrayList<>();
                for (Element element1 :
                        annotatedFields) {
                    HtmlInput annotationElem = element1.getAnnotation(HtmlInput.class);
                    annotation.add(annotationElem.toString().substring(annotationElem.toString().indexOf("(") + 1, annotationElem.toString().indexOf(")")).replaceAll(",",""));
                }
                root.put("annotation", annotation);
                temp.process(root, writer1);
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}
