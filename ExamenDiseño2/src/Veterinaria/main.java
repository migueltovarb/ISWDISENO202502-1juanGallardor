package Veterinaria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<dueño> duenos = new ArrayList<>();
        boolean running = true;
        while (running) {
            System.out.println("1. Registrar dueño");
            System.out.println("2. Registrar mascota");
            System.out.println("3. Registrar control veterinario a mascota");
            System.out.println("4. Listar dueños y mascotas");
            System.out.println("5. Listar controles de una mascota");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("Nombre completo: ");
                    String nombre = sc.nextLine();
                    System.out.print("Documento: ");
                    String doc = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();
                    duenos.add(new dueño(nombre, doc, tel));
                    System.out.println("Dueño registrado correctamente.");
                    break;
                case "2":
                    if (duenos.isEmpty()) {
                        System.out.println("Primero registre un dueño.");
                        break;
                    }
                    System.out.print("Documento del dueño: ");
                    String docBuscar = sc.nextLine();
                    dueño duenoMascota = null;
                    for (dueño d : duenos) {
                        if (d.getDocumento().equals(docBuscar)) {
                            duenoMascota = d;
                            break;
                        }
                    }
                    if (duenoMascota == null) {
                        System.out.println("Dueño no encontrado.");
                        break;
                    }
                    System.out.print("Nombre de la mascota: ");
                    String nomMascota = sc.nextLine();
                    System.out.print("Especie: ");
                    String especie = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = 0;
                    try {
                        edad = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Edad inválida.");
                    }
                    mascota nuevaMascota = new mascota(nomMascota, especie, edad, duenoMascota);
                    duenoMascota.agregarMascota(nuevaMascota);
                    System.out.println("Mascota registrada correctamente.");
                    break;
                case "3":
                    if (duenos.isEmpty()) {
                        System.out.println("Primero registre un dueño y una mascota.");
                        break;
                    }
                    System.out.print("Documento del dueño: ");
                    String docDue = sc.nextLine();
                    dueño duenoCtrl = null;
                    for (dueño d : duenos) {
                        if (d.getDocumento().equals(docDue)) {
                            duenoCtrl = d;
                            break;
                        }
                    }
                    if (duenoCtrl == null || duenoCtrl.getMascotas().isEmpty()) {
                        System.out.println("Dueño no encontrado o no tiene mascotas.");
                        break;
                    }
                    System.out.print("Nombre de la mascota: ");
                    String nomMasc = sc.nextLine();
                    mascota mascCtrl = null;
                    for (mascota m : duenoCtrl.getMascotas()) {
                        if (m.getNombre().equalsIgnoreCase(nomMasc)) {
                            mascCtrl = m;
                            break;
                        }
                    }
                    if (mascCtrl == null) {
                        System.out.println("Mascota no encontrada.");
                        break;
                    }
                    System.out.print("Observación: ");
                    String obs = sc.nextLine();
                    System.out.println("Tipo de control: 1.VACUNA 2.CHEQUEO 3.DESPARACITACION");
                    String tipo = sc.nextLine();
                    ControlVeterinario.TipoControl tipoCtrl;
                    switch (tipo) {
                        case "1": tipoCtrl = ControlVeterinario.TipoControl.VACUNA; break;
                        case "2": tipoCtrl = ControlVeterinario.TipoControl.CHEQUEO; break;
                        case "3": tipoCtrl = ControlVeterinario.TipoControl.DESPARACITACION; break;
                        default: tipoCtrl = ControlVeterinario.TipoControl.CHEQUEO;
                    }
                    ControlVeterinario ctrl = new ControlVeterinario(new Date(), obs, tipoCtrl);
                    mascCtrl.agregarControl(ctrl);
                    System.out.println("Control veterinario registrado correctamente.");
                    break;
                case "4":
                    if (duenos.isEmpty()) {
                        System.out.println("No hay dueños registrados.");
                        break;
                    }
                    for (dueño d : duenos) {
                        System.out.println("Dueño: " + d.getNombreCompleto() + " (" + d.getDocumento() + ")");
                        if (d.getMascotas().isEmpty()) {
                            System.out.println("  Sin mascotas.");
                        } else {
                            for (mascota m : d.getMascotas()) {
                                System.out.println("  Mascota: " + m.getNombre() + " - " + m.getEspecie() + ", edad: " + m.getEdad());
                            }
                        }
                    }
                    break;
                case "5":
                    System.out.print("Documento del dueño: ");
                    String docDueHist = sc.nextLine();
                    dueño duenoHist = null;
                    for (dueño d : duenos) {
                        if (d.getDocumento().equals(docDueHist)) {
                            duenoHist = d;
                            break;
                        }
                    }
                    if (duenoHist == null || duenoHist.getMascotas().isEmpty()) {
                        System.out.println("Dueño no encontrado o no tiene mascotas.");
                        break;
                    }
                    System.out.print("Nombre de la mascota: ");
                    String nomMascHist = sc.nextLine();
                    mascota mascHist = null;
                    for (mascota m : duenoHist.getMascotas()) {
                        if (m.getNombre().equalsIgnoreCase(nomMascHist)) {
                            mascHist = m;
                            break;
                        }
                    }
                    if (mascHist == null) {
                        System.out.println("Mascota no encontrada.");
                        break;
                    }
                    System.out.println("Controles de " + mascHist.getNombre() + ":");
                    System.out.println(mascHist.getHistorialControles());
                    break;
                case "6":
                    running = false;
                  
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }
}