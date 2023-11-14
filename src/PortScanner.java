import javax.sound.sampled.Port;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScanner {


    public static void main(String[] args) {

        System.out.println("Enter the IP Address  to scan: ");

        Scanner IPScanner = new Scanner(System.in);
        String host = IPScanner.next();
        int timeout = 1000;
        int threads = 256;
        scanPorts(host, timeout, threads);

        List<PortScanTask> portScanTaskList = new ArrayList<>();
        PortScanTask portScanTask= new PortScanTask(host,timeout,threads);
        System.out.println();
        portScanTaskList.add(portScanTask);
        saveListFile(portScanTaskList);

    }
    public static void scanPorts(String host, int timeout, int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);


        for (int port = 1; port <= 256; port++) {
            executor.execute(new PortScanTask(host, port, timeout));
        }

        executor.shutdown();
    }

    public static void saveListFile(List<PortScanTask> PortScanTaskList) {


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Text.txt"))) {
            String text;
            while (!(text = bufferedReader.readLine()).equals("esc")) {
                bufferedWriter.write(text + "\n");
                bufferedWriter.flush();

            }
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }


    }



}
