import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class LoginForm extends JDialog {

    private JTextField tfNom;
    private JTextField tfPassword;
    private JButton sIncrireButton;
    private JButton connecterButton;
    private JPanel LoginPanel;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        connecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfNom.getText();
                String password = String.valueOf(tfPassword.getText());

                try {
                    client = getAuthentification(login, password);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (client != null ){
                    dispose();

                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,"" +
                            "login or password invalid","try again",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        sIncrireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(parent);

            }
        });
        setVisible(true);
    }

     public clientInf client ;
    private clientInf getAuthentification(String login, String password) throws IOException {

        clientInf client = null ;

        final String BDD = "chat";
        final String url = "jdbc:mysql://localhost:3306/" + BDD;
        final String user = "root";
        final String passwd = "";
        try{


            Connection conn = DriverManager.getConnection(url, user, passwd);
            Statement stmt = (Statement) conn.createStatement();
            String sql = "select * from client where login=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                client = new clientInf();
                client.login=resultSet.getString("login");
                client.password=resultSet.getString("password");


            }
            stmt.close();
            conn.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;

    }



    public static void main(String[] args){
        LoginForm loginForm = new LoginForm(null);
        clientInf client=loginForm.client;
        if(client != null ){
            System.out.println("success");
            ConnecteForm connecteForm = new ConnecteForm(null);

        }

        else {
            System.out.println("failed");
        }


    }

}
