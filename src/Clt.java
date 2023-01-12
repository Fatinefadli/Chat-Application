import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Vector;

public class Clt {
    private static Vector<Clt> clients;

    static {
        clients = new Vector<Clt>();
    }

    private int portClient;
    private InetAddress ipClient;
    private String nomClient;

    public Clt(int portClient, InetAddress ipClient, String nomClient) {
        this.portClient = portClient;
        this.ipClient = ipClient;
        this.nomClient = nomClient;
        System.out.println("New Client => " + this.portClient + ", " +  this.ipClient + ", " + this.nomClient);
    }

    public InetAddress getIpClient() {
        return ipClient;
    }

    public void setIpClient(InetAddress ipClient) {
        this.ipClient = ipClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getPortClient() {
        return portClient;
    }

    public void setPortClient(int portClient) {
        this.portClient = portClient;
    }

    public static void addClient(Clt client) {
        clients.add(client);
    }

    public static Clt getClients(String nom) {
        Enumeration<Clt> E = clients.elements();
        while (E.hasMoreElements()) {
            Clt tmp = E.nextElement();
            if (tmp.getNomClient().equals(nom))
                return tmp;
        }
        return null;
    }

    public static boolean isClient(String nom) {
        Enumeration<Clt> E = clients.elements();
        while (E.hasMoreElements()) {
            if (E.nextElement().getNomClient().equals(nom))
                return true;
        }
        return false;
    }
}
