package com.edhydev.clasesabstractas.form;

import com.edhydev.clasesabstractas.form.elementos.*;
import com.edhydev.clasesabstractas.form.elementos.select.Opcion;
import com.edhydev.clasesabstractas.form.validador.*;

import java.util.Arrays;
import java.util.List;

public class EjemploForm {

    public static void main(String[] args) {

        InputForm username = new InputForm("usuario");
        username.addValidador(new RequeridoValidador());

        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador())
            .addValidador(new LargoValidador(6, 12));

        InputForm email = new InputForm("correo-e", "email");
        email.addValidador(new RequeridoValidador())
            .addValidador(new EmailValidador());

        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());

        TextareaForm experiencia = new TextareaForm("exp", 5, 9);

        SelectForm lenguaje = new SelectForm("lenguaje");
        lenguaje.addValidador(new NoNuloValidador());

        lenguaje.addOpcion(new Opcion("1", "Java"))
            .addOpcion(new Opcion("2", "JavaScript"))
            .addOpcion(new Opcion("3", "Python"))
            .addOpcion(new Opcion("4", "SQL"))
            .addOpcion(new Opcion("5", "PHP"))
            .addOpcion(new Opcion("6", "TypeScript").setSelected());

        ElementoForm saludar = new ElementoForm("saludo") {
            @Override
            public String dibujarHtml() {
                return "<input disabled name=\"" + this.nombre + "\" value=\"" + this.valor + "\">";
            }
        };

        saludar.setValor("Hola que tal este campo está deshabilitado!");

        username.setValor("edhydev");
        password.setValor("");
        email.setValor("edhy.mxmgmail.com");
        edad.setValor("d22");
        experiencia.setValor(" .... más de 10 de experiencia ....");

        List<ElementoForm> elementos = Arrays.asList(
                username,
                password,
                email,
                edad,
                experiencia,
                lenguaje,
                saludar);

        elementos.forEach(e -> {
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        });

        elementos.forEach(e -> {
            if(!e.esValido()) {
                e.getErrores().forEach(System.out::println);
            }
        });


    }

}
