package ru.kpfu.annotations;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "PATH " + out.toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotationForm = element.getAnnotation(HtmlForm.class);
                writer.write("<form action=\"" + annotationForm.action() +
                        "\" method=\"" + annotationForm.method() + "\">\n");

                for (Element element1 :
                        annotatedFields) {
                    try {
                        HtmlInput annotation = element1.getAnnotation(HtmlInput.class);
                        writer.write("\t<input type=\"" + annotation.type() +
                                "\" name=\"" + annotation.name() +
                                "\" placeholder=\"" + annotation.placeholder() +
                                "\">\n");
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                writer.write("\t<input type=\"submit\" value=\"submit\"/>\n" +
                        "</form>");
                writer.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}
