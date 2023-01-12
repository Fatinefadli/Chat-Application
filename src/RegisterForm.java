import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm extends JDialog {

    private JTextField tfName;
    private JTextField tfPassword;
    private JTextField tfConfirmPassword;
    private JButton registrerButton;
    private JButton cancelButton;
    private JPanel RegistrePanel;

    public RegisterForm(JFrame parent){
        super(parent);
        setTitle("Create new account");
        setContentPane(RegistrePanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        registrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registreClient();
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

    private void registreClient() {
        String login = tfName.getText();
        String password = String.valueOf(tfPassword.getText());
        String Confirmpassword = String.valueOf(tfConfirmPassword.getText());
        if (login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,"please enter all fields",
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            return;

        }

       client= addClientToDatabase(login,password);


    }

    public clientInf client;
    private clientInf addClientToDatabase(String login, String password) {
        clientInf client=null;

        final String BDD = "chat";
        final String url = "jdbc:mysql://localhost:3306/" + BDD;
        final String user = "root";
        final String passwd = "";
        try {
            Connection conn = DriverManager.getConnection(url, user, passwd);
            Statement stmt = (Statement) conn.createStatement();
            String sql = "insert into client (login,password)"+"values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows>0){
                client = new clientInf();
                client.login=login;
                client.password=password;
            }
            stmt.close();
            conn.close();



    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;

    }

    public static void main(String[] args){
        RegisterForm registerForm = new RegisterForm(null);

        clientInf client = registerForm.client;
        if(client != null){
            System.out.println("successful registration of : "+client.login);
        }
        else{
            System.out.println("registration canceled");
        }
    }
}
