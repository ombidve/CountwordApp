import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class textWordCounter extends JFrame {
    private JTextArea textArea;
    private JLabel wordCountLabel;
    private JLabel charCountLabel;

    public textWordCounter() {
        setTitle("Word & Character Counter");
        int windowWidth = 400;
        int windowHeight = 300;
        setSize(windowWidth, windowHeight);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        wordCountLabel = new JLabel("Word Count: 0", SwingConstants.CENTER);
        charCountLabel = new JLabel("Character Count: 0", SwingConstants.CENTER);

        // Add DocumentListener to detect changes in the text
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCounts();
            }
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        // Panel to hold both labels
        JPanel countPanel = new JPanel(new GridLayout(2, 1));
        countPanel.add(wordCountLabel);
        countPanel.add(charCountLabel);
        add(countPanel, BorderLayout.NORTH);
    }

    // Method to update both word and character count dynamically
    private void updateCounts() {
        String text = textArea.getText().trim(); // Remove leading & trailing spaces
        
        // Character count (including spaces)
        int charCount = text.length();
        
        // Word count logic
        int wordCount = 0;
        if (!text.isEmpty()) {
            String[] words = text.split("\\s+"); // Split words by spaces
            wordCount = words.length;
        }

        // Update labels
        wordCountLabel.setText("Word Count: " + wordCount);
        charCountLabel.setText("Character Count: " + charCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            textWordCounter counter = new textWordCounter();
            counter.setVisible(true);
        });
    }
}

