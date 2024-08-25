package controller;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RedesController
{
    public RedesController()
    {
        super();
    }

    private String os()
    {
        return System.getProperty("os.name");
    }

    public void ip()
    {
        String proc;
        if (os().contains("Windows"))
            proc = "IPCONFIG";
        else
            proc = "ip addr";
        String[] procArr = proc.split(" ");
        try {
            Process p = Runtime.getRuntime().exec(procArr);
            InputStreamReader procOut = new InputStreamReader(p.getInputStream());
            BufferedReader buffer = new BufferedReader(procOut);
            String line = buffer.readLine();
            String name = null;
            System.out.println("---------- Adaptadores de Rede com IPv4 (inet6) ---------");
            if (os().contains("Windows")) {
                while (line != null) {
                    if (line.length() > 0 && line.charAt(0) >= 33 && line.charAt(0) <= 126)
                        name = line;
                    if (line.contains("IPv4")) {
                        System.out.println(name);
                        System.out.println(line + "\n");
                    }
                    line = buffer.readLine();                    
                }
            } else {
                while (line != null) {
                    if (line.charAt(0) >= 33 && line.charAt(0) <= 126)
                        name = line;
                    if (line.contains("inet6")) {
                        System.out.println(name);
                        System.out.println(line + "\n");
                    }
                    line = buffer.readLine();                    
                }
            }
            System.out.println("---------------------------------------------------------");
            buffer.close();
            procOut.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void ping()
    {
        String proc;
        if (os().contains("Windows"))
            proc = "ping -4 -n 10 www.google.com.br";
        else
            proc = "ping -4 -c 10 www.google.com.br";
        String[] procArr = proc.split(" ");
        try {
            Process p = Runtime.getRuntime().exec(procArr);
            InputStreamReader procOut = new InputStreamReader(p.getInputStream());
            BufferedReader buffer = new BufferedReader(procOut);
            String line = buffer.readLine();
            int bar = 0;
            System.out.println("---------- Ping em www.gooogle.com.br ----------");
            if (os().contains("Windows")) {
                while (line != null) {
                    if (line.contains("ms")) {
                        if (!line.contains("TTL"))
                            System.out.println("\nPing médio: " + line.split(",")[2].split(" = ")[1]);
                        else {
                            System.out.print("\r[");
                            for (int i = 0; i < 10; i++) {
                                if (i <= bar)
                                    System.out.print("#");
                                else
                                    System.out.print("-");
                            }
                            System.out.print("]");
                            bar++;
                        }
                    }
                    line = buffer.readLine();
                }
            } else {
                while (line != null) {
                    if (line.contains("ms")) {
                        if (line.contains("rtt"))
                            System.out.println("\nPing médio: " + line.split("/")[4] + "ms");
                        else {
                            System.out.print("\r[");
                            for (int i = 0; i < 10; i++) {
                                if (i <= bar)
                                    System.out.print("#");
                                else
                                    System.out.print("-");
                            }
                            System.out.print("]");
                            bar++;
                        }
                    }
                    line = buffer.readLine();
                }
            }
            System.out.println("------------------------------------------------");
            procOut.close();
            buffer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}