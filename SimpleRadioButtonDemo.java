import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

class SimpleRadioButtonDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Radio Button Demo");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create radio buttons
        JRadioButton bird = new JRadioButton("Bird");
        JRadioButton cat = new JRadioButton("Cat");
        JRadioButton dog = new JRadioButton("Dog");
        JRadioButton rabbit = new JRadioButton("Rabbit");
        JRadioButton pig = new JRadioButton("Pig");

        // Group them so only one can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(bird);
        group.add(cat);
        group.add(dog);
        group.add(rabbit);
        group.add(pig);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(bird);
        panel.add(cat);
        panel.add(dog);
        panel.add(rabbit);
        panel.add(pig);

        // Label for showing images
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        // Add components to frame
        frame.add(panel, BorderLayout.WEST);
        frame.add(label, BorderLayout.CENTER);

        // Function to show image
        ActionListener showImage = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get button text and convert to lowercase to match file names
                String pet = ((JRadioButton) e.getSource()).getText().toLowerCase();

                // Define possible image extensions
                String[] exts = {".jpg", ".jpeg", ".png"};

                ImageIcon foundIcon = null;

                // Check inside images/ folder
                for (String ext : exts) {
                    String path = "images/" + pet + ext;
                    File file = new File(path);
                    if (file.exists()) {
                        foundIcon = new ImageIcon(file.getAbsolutePath());
                        break;
                    }
                }

                if (foundIcon == null || foundIcon.getIconWidth() == -1) {
                    JOptionPane.showMessageDialog(frame, "Image not found for: " + pet);
                    label.setIcon(null);
                    return;
                }

                // Scale image nicely
                Image img = foundIcon.getImage();
                Image scaled = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));

                JOptionPane.showMessageDialog(frame, "You selected " + pet.substring(0, 1).toUpperCase() + pet.substring(1));
            }
        };

        // Add listeners
        bird.addActionListener(showImage);
        cat.addActionListener(showImage);
        dog.addActionListener(showImage);
        rabbit.addActionListener(showImage);
        pig.addActionListener(showImage);

        frame.setVisible(true);
    }
}
