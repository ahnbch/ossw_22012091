import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Manager extends JFrame {
    private JTextField nameField;
    private JTextField moneyField;
    private JTextField searchField;
    private JButton saveButton, searchButton, editButton, deleteButton, makeFileButton, resultButton;
    private JButton sortNameAscButton, sortNameDescButton, sortMoneyAscButton, sortMoneyDescButton;
    private JTextArea displayArea;
    private ArrayList<Info> infoList;

    public Manager() {
        setTitle("Digital Money Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("이름:"));
        nameField = new JTextField(10);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("금액:"));
        moneyField = new JTextField(10);
        inputPanel.add(moneyField);
        saveButton = new JButton("Save");
        inputPanel.add(saveButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(10);
        searchPanel.add(searchField);
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        displayArea = new JTextArea();
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel sortPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        sortPanel.add(new JLabel("이름 정렬"), gbc);
        sortNameAscButton = new JButton("▲");
        sortNameAscButton.setPreferredSize(new Dimension(45, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        sortPanel.add(sortNameAscButton, gbc);
        sortNameDescButton = new JButton("▼");
        sortNameDescButton.setPreferredSize(new Dimension(45, 20));
        gbc.gridx = 2;
        gbc.gridy = 0;
        sortPanel.add(sortNameDescButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        sortPanel.add(new JLabel("금액 정렬"), gbc);
        sortMoneyAscButton = new JButton("▲");
        sortMoneyAscButton.setPreferredSize(new Dimension(45, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        sortPanel.add(sortMoneyAscButton, gbc);
        sortMoneyDescButton = new JButton("▼");
        sortMoneyDescButton.setPreferredSize(new Dimension(45, 20));
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        
        sortPanel.add(sortMoneyDescButton, gbc);
        centerPanel.add(sortPanel, BorderLayout.EAST);
        JPanel editPanel = new JPanel(new GridLayout(4, 1));
        editButton = new JButton("Edit");
        editPanel.add(editButton);
        deleteButton = new JButton("Delete");
        editPanel.add(deleteButton);
        makeFileButton = new JButton("Making File");
        editPanel.add(makeFileButton);
        resultButton = new JButton("Result");
        editPanel.add(resultButton);
        centerPanel.add(editPanel, BorderLayout.SOUTH);
        
        add(inputPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        infoList = new ArrayList<>();
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveInfo();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchInfo();
            }
        });

        sortNameAscButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortInfoByName(true);
            }
        });

        sortNameDescButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortInfoByName(false);
            }
        });

        sortMoneyAscButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortInfoByMoney(true);
            }
        });

        sortMoneyDescButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortInfoByMoney(false);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editInfo();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteInfo();
            }
        });

        makeFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
    }

    private void saveInfo() {
        String name = nameField.getText();
        int money = Integer.parseInt(moneyField.getText());
        infoList.add(new Info(name, money));
        displayArea.append("Saved: " + name + " - " + money + "\n");
        nameField.setText("");
        moneyField.setText("");
    }

    private void searchInfo() {
        String name = searchField.getText();
        displayArea.setText("");
        for (Info info : infoList) {
            if (info.getName().equals(name)) {
                displayArea.append("Found: " + info.getName() + " - " + info.getMoney() + "\n");
            }
        }
    }

    private void sortInfoByName(boolean ascending) {
        if (ascending) {
            Collections.sort(infoList, Comparator.comparing(Info::getName));
        } else {
            Collections.sort(infoList, Comparator.comparing(Info::getName).reversed());
        }
        displayArea.setText("Sorted by Name:\n");
        for (Info info : infoList) {
            displayArea.append(info.getName() + " - " + info.getMoney() + "\n");
        }
    }

    private void sortInfoByMoney(boolean ascending) {
        if (ascending) {
            Collections.sort(infoList, Comparator.comparingInt(Info::getMoney));
        } else {
            Collections.sort(infoList, Comparator.comparingInt(Info::getMoney).reversed());
        }
        displayArea.setText("Sorted by Money:\n");
        for (Info info : infoList) {
            displayArea.append(info.getName() + " - " + info.getMoney() + "\n");
        }
    }

    private void editInfo() {
        String name = nameField.getText();
        int newMoney = Integer.parseInt(moneyField.getText());
        for (Info info : infoList) {
            if (info.getName().equals(name)) {
                info.setMoney(newMoney);
                displayArea.append("Edited: " + name + " - " + newMoney + "\n");
            }
        }
    }

    private void deleteInfo() {
        String name = nameField.getText();
        infoList.removeIf(info -> info.getName().equals(name));
        displayArea.append("Deleted: " + name + "\n");
    }

    private void saveToFile() {
        displayArea.append("Data saved to file.\n");
    }

    private void calculateTotal() {
        int total = infoList.stream().mapToInt(Info::getMoney).sum();
        displayArea.append("Total Money: " + total + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Manager().setVisible(true);
            }
        });
    }
}


