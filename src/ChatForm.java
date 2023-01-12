import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

public class ChatForm extends JDialog {
    private JPanel ChatPanel;
    private JTextField tfUsername;
    private JTextArea taMsg;
    private JTextField tfTo;
    private JTextField tfMessage;
    private JButton sendButton;
    private JList lConnecter;
    private JButton cancelButton;

    public ChatForm(JFrame parent) {
        super(parent);
        setTitle("Discution");
        setContentPane(ChatPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String toClient = tfTo.getText();
                String message = taMsg.getText();
                taMsg.setText("");

                String nomLocal = tfUsername.getText();
                try {
                    int portServer=1234;
                    String addressServer="127.0.0.1";
                    Sender sender = new Sender(portServer, addressServer);
                    sender.sendMessage(message, nomLocal, toClient);
                } catch (SocketException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
}
