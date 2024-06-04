import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class FlowLOut {
    private JTextField nameField;
    private JButton sendButton;

    public static void main(String[] args){
        FlowLOut flowLOut = new FlowLOut();
        flowLOut.createWindow();

    }

    public void createWindow(){
        JFrame window = new JFrame();
        JPanel panel = new JPanel();
        JPanel panelBox = new JPanel();
        String[] stringList = {"Hi", "Hello", "I "};
        JList<String> stringJList = new JList<>(stringList);
        JScrollPane jScrollPane2 = new JScrollPane(stringJList);
        jScrollPane2.createVerticalScrollBar();
        stringJList.setVisibleRowCount(1);
        stringJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        window.add(jScrollPane2);


        window.add(BorderLayout.CENTER, stringJList);


        panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea(10,20);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        JButton button  = new JButton("SEND");
        button.addActionListener(event -> textArea.append(textArea.getText()));

        textArea.setLineWrap(true);
        jScrollPane.createVerticalScrollBar();
        panelBox.add(jScrollPane);
        panelBox.add(button);
        window.getContentPane().add(BorderLayout.WEST, panelBox);

        panel.setBackground(Color.GRAY);
        sendButton = new JButton("Send");
        JLabel nameLabel = new JLabel("Dog's first name: ");
        nameField = new JTextField(20);
        sendButton.addActionListener(new nameButtonListener());
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(sendButton);
        window.getContentPane().add(BorderLayout.EAST, panel);



        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setSize(600,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class nameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(nameField.getText().isBlank()){
                System.out.println("Empty Field!");
                nameField.requestFocus();
            }else{
                System.out.println(nameField.getText());
                nameField.selectAll();
                nameField.setText("");

            }


        }
    }
}
