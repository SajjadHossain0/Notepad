import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class NotePad {
    public static void main(String[] args) {
        new NotePad();
    }
    JFrame frame;
    JMenuBar menuBar;
    JMenu File,Edit,Help;
    JMenuItem New,Open,Save,Print,Exit,Cut,Copy,Paste,Del,SelectAll,About;
    JTextArea textArea;
    JScrollPane scrollPane;
    JScrollBar vertical,horizontal;
    ImageIcon imageIcon;
    NotePad(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.getContentPane().setBackground(Color.white);
        frame.setResizable(true);
        frame.setTitle("Notepad");
        frame.setLocationRelativeTo(null);
        imageIcon = new ImageIcon(getClass().getResource("notebook.png"));
        frame.setIconImage(imageIcon.getImage());

//=========================== Menu Bar ===========================================
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setVisible(true);
        menuBar.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.setJMenuBar(menuBar);
//================== File ==========================
        File = new JMenu("File");
        New = new JMenuItem("New");
        File.add(New);
        Open = new JMenuItem("Open");
        File.add(Open);
        Save = new JMenuItem("Save");
        File.add(Save);
        Print = new JMenuItem("Print");
        File.add(Print);
        Exit = new JMenuItem("Exit");
        File.add(Exit);
        menuBar.add(File);
//================== Edit ==========================
        Edit = new JMenu("Edit");
        Cut = new JMenuItem("Cut");
        Edit.add(Cut);
        Copy = new JMenuItem("Copy");
        Edit.add(Copy);
        Paste = new JMenuItem("Paste");
        Edit.add(Paste);
        Del = new JMenuItem("Delete");
        Edit.add(Del);
        SelectAll = new JMenuItem("Select All");
        Edit.add(SelectAll);
        menuBar.add(Edit);
//================== Help ==========================
        Help = new JMenu("Help");
        About = new JMenuItem("About");
        Help.add(About);
        menuBar.add(Help);
//==================text Area=============================
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        vertical = scrollPane.getVerticalScrollBar();
        vertical.setUI(new MyScrollBarUI());
        vertical.setPreferredSize(new Dimension(5, 0));
        horizontal = scrollPane.getHorizontalScrollBar();
        horizontal.setUI(new MyScrollBarUI());
        horizontal.setPreferredSize(new Dimension(0, 5));
        scrollPane.setBounds(0,50,485,612);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frame.add(scrollPane);
//========================Action Listener=====================
        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==New){
                    int choose = JOptionPane.showConfirmDialog(null,"Do you want to save this file?","Notepad",JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.YES_OPTION){
                        SaveFile();
                    }else {
                        textArea.setText(null);
                    }
                }
            }
        });New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        Open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Open) {
                    OpenFile();
                }
            }
        });Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Save) {
                    SaveFile();
                }
            }
        });Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Exit){
                    System.exit(0);
                }
            }
        });Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
        Cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Cut){
                    textArea.cut();
                }
            }
        });Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Copy){
                    textArea.copy();
                }
            }
        });Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        Paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Paste){
                    textArea.paste();
                }
            }
        });Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        Del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==Del){
                    textArea.setText(null);
                }
            }
        });
        SelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==SelectAll){
                    textArea.selectAll();
                }
            }
        });SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        About.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==About){
                    new about();
                }
            }
        });
        frame.setVisible(true);
    }
    class MyScrollBarUI extends BasicScrollBarUI {
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }
        private JButton createZeroButton() {
            JButton jbutton = new JButton();
            jbutton.setPreferredSize(new Dimension(0, 0));
            jbutton.setMinimumSize(new Dimension(0, 0));
            jbutton.setMaximumSize(new Dimension(0, 0));
            return jbutton;
        }
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            g.setColor(Color.GRAY);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }
    protected void OpenFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only text files(.txt)", "txt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(textfilter);
        int action = fileChooser.showOpenDialog(null);
        if (action != JFileChooser.APPROVE_OPTION){
            return;
        }
        else {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
            if (!fileName.contains(".txt")){
                fileName = fileName+".txt";
            }
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                textArea.read(reader,null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    protected void SaveFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only text files(.txt)", "txt");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(textfilter);
        int action = fileChooser.showOpenDialog(null);
        if (action != JFileChooser.APPROVE_OPTION){
            return;
        }
        else {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
            if (!fileName.contains(".txt")){
                fileName = fileName+".txt";
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                textArea.write(writer);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
class about {
    JFrame frame;
    JLabel img_label,label;
    ImageIcon image;
    about(){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(400,300);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("About Notepad");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        image = new ImageIcon(getClass().getResource("notebook.png"));
        frame.setIconImage(image.getImage());

        image = new ImageIcon(getClass().getResource("rsz_1notebook.png"));
        img_label = new JLabel(image);
        img_label.setBounds(45,30,112,112);
        frame.add(img_label);

        label = new JLabel();
        String txt = "<html>Welcome to Notepad<br>" +
                "Notepad is a simple text editor for Microsoft Windows. It is a<br>" +
                "basic text editor.<br>" +
                "All rights reserved@2023</html>";
        label.setText(txt);
        label.setBounds(20,70,350,250);
        frame.add(label);
    }
}
