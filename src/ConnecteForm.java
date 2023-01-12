import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnecteForm extends JDialog {
    private JPanel panel1;
    private JTextField tfportClient;
    private JTextField tfportServeur;
    private JTextField tfUsername;
    private JTextField tfAdresse;
    private JButton connecterButton;
    private JButton cancelButton;
    private JPanel ConnecterPanel;

    public ConnecteForm(JFrame parent) {
        super(parent);
        setTitle("se connecter");
        setContentPane(ConnecterPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        connecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String addressServer = tfAdresse.getText();
                int portServer = Integer.parseInt(tfportServeur.getText());

                int portLocal = Integer.parseInt(tfportClient.getText());
                String nomLocal = tfUsername.getText();

                try {
                    Sender sender = new Sender(portServer, addressServer);
                    sender.sayHallo(portLocal, nomLocal);
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                }  catch (SocketException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                ClientThread thread = new ClientThread(portLocal);
                thread.start();
                ChatForm chatForm = new ChatForm(null);
            }

        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {
        ConnecteForm connecteForm=new ConnecteForm(null);


    }


}
