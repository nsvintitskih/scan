import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScanTask implements Runnable{
    private String host;
    private int port;
    private int timeout;

    public PortScanTask(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.close();
            System.out.println("Port " + port + " is open");
        } catch (Exception e) {

        }
    }
}

