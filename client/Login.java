package client;

import common.Utils;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import server.Server;

class Login extends JFrame {
    
    private JButton jb_login;
    private JLabel jl_user, jl_port, jl_title;
    private JTextField jt_user, jt_port;
    
    public Login() {    
        super("Login");
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
        start();
    }
    
    private void initComponents() {
        jb_login = new JButton("Entrar");
        jl_user = new JLabel("Usuario", SwingConstants.CENTER);
        jl_port = new JLabel("Porta", SwingConstants.CENTER);
        jl_title = new JLabel();
        jt_user = new JTextField();
        jt_port =  new JTextField();
    }
    
    private void configComponents() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(400, 300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        
        jl_title.setBounds(10, 10, 375, 100);
        ImageIcon icon = new ImageIcon("../logo.png");
        jl_title.setIcon(new ImageIcon(icon.getImage().getScaledInstance(375, 100, Image.SCALE_SMOOTH)));
        
        jb_login.setBounds(10, 220, 375, 40);
        
        jl_user.setBounds(10, 120, 100, 40);
        jl_user.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        jl_port.setBounds(10, 170, 100, 40);
        jl_port.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        jt_user.setBounds(120, 120, 265, 40);
        jt_port.setBounds(120, 170, 265, 40);
    }
    
    private void insertComponents() {
        this.add(jb_login);
        this.add(jl_user);
        this.add(jl_port);
        this.add(jl_title);
        this.add(jt_user);
        this.add(jt_port);
    }
    
    private void insertActions() {
        jb_login.addActionListener(event -> {
            try {
                String usuario = jt_user.getText();
                jt_user.setText("");
                
                int port = jt_parseInt(jt_port.getText());
                jt_port.setText("");

                Socket connection = new Socket(Server.HOST, Server.PORT);
                
                String connection_info = (usuario + ":" + connection.getLocalAddress().getHostAddress() + ":" + port);
                Utils.sendMessage(connection, connection_info);

                if(Utils.receiveMessage(connection).equals("SUCCESS!!!")) {
                    new Home(connection, connection_info);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Esse usuário/porta já está em uso!");
                }
            } catch(IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar. Verifique se o SERVER está em execução!");
            }
        }); 
    }
    
    private void start() {
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Login login = new Login();
    }
}
