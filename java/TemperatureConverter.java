import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {

    private JFrame frame;
    private JTextField inputField;
    private JComboBox<String> conversionTypeComboBox;

    public TemperatureConverter() {
        // Create the main frame
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 180);
        frame.setLayout(null);

        // Create components
        JLabel titleLabel = new JLabel("Temperature Converter");
        titleLabel.setBounds(90, 10, 200, 20);

        inputField = new JTextField();
        inputField.setBounds(30, 40, 100, 25);

        JLabel conversionTypeLabel = new JLabel("Select Conversion:");
        conversionTypeLabel.setBounds(150, 40, 150, 25);

        String[] conversionTypes = {"Celsius to Fahrenheit", "Celsius to Kelvin", "Fahrenheit to Celsius", "Kelvin to Celsius"};
        conversionTypeComboBox = new JComboBox<>(conversionTypes);
        conversionTypeComboBox.setBounds(150, 65, 150, 25);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(30, 90, 100, 30);

        // Add components to the frame
        frame.add(titleLabel);
        frame.add(inputField);
        frame.add(conversionTypeLabel);
        frame.add(conversionTypeComboBox);
        frame.add(convertButton);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        // Set the frame visible
        frame.setVisible(true);
    }

    private void convertTemperature() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String selectedConversionType = (String) conversionTypeComboBox.getSelectedItem();

            // Perform the temperature conversion based on the selected conversion type
            double result;

            switch (selectedConversionType) {
                case "Celsius to Fahrenheit":
                    result = (inputValue * 9 / 5) + 32;
                    break;
                case "Celsius to Kelvin":
                    result = inputValue + 273.15;
                    break;
                case "Fahrenheit to Celsius":
                    result = (inputValue - 32) * 5 / 9;
                    break;
                case "Kelvin to Celsius":
                    result = inputValue - 273.15;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedConversionType);
            }

            // Display the result in a dialog
            JOptionPane.showMessageDialog(frame, "Converted Temperature: " + result + " " + getTargetScale(selectedConversionType));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a numeric value.");
        }
    }

    private String getTargetScale(String conversionType) {
        // Extract the target scale from the conversion type
        String[] parts = conversionType.split(" to ");
        return parts[1];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
