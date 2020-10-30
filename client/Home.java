import java.awt.*;
import javax.swing.*;

public class Home extends JFrame {

    private String connection_info;
    private JLabel jl_title;
    private JButton jb_get_connected, jb_start_talk;
    private JList jlist;
    private JScrollPane scroll;

    public Home(String connection_info) {
        super("Bate-Papo: Home");
        this.connection_info = connection_info;
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
        start();
    }

    private void initComponents() {
        jl_title = new JLabel("< Usuario : " + connection_info.split(":")[0] + " >");
        jb_get_connected = new JButton("Atualizar Contatos");
        jb_start_talk = new JButton("Iniciar Conversa");
        jlist = new JList();
        scroll = new JScrollPane(jlist);
    }
    
    private void configComponents() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(600, 480));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        jl_title.setBounds(10, 10, 370, 40);
        jl_title.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jb_get_connected.setBounds(400, 10, 180, 40);
        jb_get_connected.setFocusable(false);

        jb_start_talk.setBounds(10, 400, 575, 40);
        jb_start_talk.setFocusable(false);

        jlist.setBorder(BorderFactory.createTitledBorder("Usuarios Online"));
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll.setBounds(10, 60, 575, 335);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
    }

    private void insertComponents() {
        this.add(jl_title);
        this.add(jb_get_connected);
        this.add(scroll);
        this.add(jb_start_talk);
    }
    
    private void insertActions() {
        
    }
    
    private void start() {
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Home home = new Home("Gabriel:127.0.0.1:3000");
    }
}