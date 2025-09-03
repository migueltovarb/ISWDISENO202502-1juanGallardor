
package packageExamenDiseno;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ExamenDiseno {
    final static int DIAS_SEMANA = 5;
    final static int NUM_ESTUDIANTES = 3;

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        char[][] asistencia = new char[NUM_ESTUDIANTES][DIAS_SEMANA];

        boolean validar = false;
        
        while (!validar) {
            System.out.println("         MENU        ");
            System.out.println("1. Registrar asistencia");
            System.out.println("2. Ver asistencia individual");
            System.out.println("3. Ver resumen general");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = 0;
            
            try {
                opcion = scanner.nextInt();   
            } 
            
            catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
            
                case 1:
                	
                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        System.out.println("Estudiante " + (i + 1) + ":");
                        
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                            boolean entradaValida = false;
                            
                            while (!entradaValida) {
                            	
                                try {
                                    System.out.print("  Día " + (j + 1) + " (P/A): ");
                                    char valor = scanner.next().toUpperCase().charAt(0);
                                    
                                    if (valor == 'P' || valor == 'A') {
                                        asistencia[i][j] = valor;
                                        entradaValida = true;
                                    } 
                                    
                                    else {
                                        System.out.println("Por favor ingrese solo 'P' o 'A'.");   
                                    }
                                    
                                } 
                                catch (Exception e) {
                                System.out.println("Error de entrada. Intente de nuevo.");
                                scanner.nextLine();
                                }     
                                
                            }   
                        }       
                    }
                    break;
                    
	                case 2:
	                    System.out.print("Ingrese el número de estudiante (1-" + NUM_ESTUDIANTES + "): ");
	                    int est = 0;
	                    try {
	                        est = scanner.nextInt();
	                        if (est >= 1 && est <= NUM_ESTUDIANTES) {
	                            System.out.print("Asistencia del estudiante " + est + ": ");
	                            
	                            for (int j = 0; j < DIAS_SEMANA; j++) {
	                                System.out.print(asistencia[est - 1][j] + " ");
	                            }
	                            System.out.println();
	                        }
	                        else {
	                            System.out.println("Estudiante no válido.");
	                        }
	                    } 
	                    catch (InputMismatchException e) {
	                        System.out.println("Número inválido.");
	                        scanner.nextLine();
	                    }
	                break;
                    
                case 3:
                	
                    System.out.println("      Total de asistencias por estudiante:     ");
                    
                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        int total = 0;
                        
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                        	
                            if (asistencia[i][j] == 'P') total++;
                            
                        }
                        System.out.println("Estudiante " + (i + 1) + ": " + total);
                    }
                    
                    System.out.print("Estudiantes que asistieron todos los días: ");
                    boolean alguno = false;
                    
                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        int total = 0;
                        
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                        	
                            if (asistencia[i][j] == 'P') { 
                            	total++;
                            }
                            
                        }
                        if (total == DIAS_SEMANA) {
                            System.out.print((i + 1) + " ");
                            alguno = true;
                        }
                    }
                    
                    if (!alguno) {
                    	
                    	System.out.print("Ninguno");
                    	
                    }
                    
                    System.out.println();
                    
                    int[] ausenciasPorDia = new int[DIAS_SEMANA];
                    int maxAusencias = 0;
                    
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        int count = 0;
                        
                        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                            if (asistencia[i][j] == 'A') {
                            	count++;
                            }
                        }
                        ausenciasPorDia[j] = count;
                        
                        if (count > maxAusencias) {
                        	maxAusencias = count;
                        }
                    }
                    
                    System.out.print("Día(s) con mayor número de ausencias: ");
                    boolean diaAusente = false;
                    
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        if (ausenciasPorDia[j] == maxAusencias && maxAusencias > 0) {
                            System.out.print((j + 1) + " ");
                            diaAusente = true;
                        }
                    }
                    if (!diaAusente) {
                    	System.out.print("Ninguno");
                    }
                    
                    System.out.println();
                    break;
                    
                case 4:
                    validar = true;
                    System.out.println("finalizado...");
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
