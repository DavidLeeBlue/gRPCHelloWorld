package com.smarthotel.gui;

import com.smarthotel.client.ControllerClient;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ControllerClientGUI extends JFrame {
    private ControllerClient client;
    private JLabel lightStatusLabel;
    private JLabel feedbackStatusLabel;
    private JTextArea roomServiceContentArea;
    private JTextArea roomServiceServerResponsesArea;

    public ControllerClientGUI(ControllerClient client) {
        this.client = client;

        setTitle("Controller Client GUI");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // LightSwitch section
        JPanel lightSwitchPanel = new JPanel();
        lightSwitchPanel.setBorder(new TitledBorder("LightSwitch"));
        lightSwitchPanel.setLayout(new GridLayout(3, 1, 5, 5));
        lightSwitchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton turnOnButton = new JButton("Turn On Light");
        turnOnButton.addActionListener(e -> {
            client.turnOn();
            lightStatusLabel.setText("Light is ON");
        });
        JButton turnOffButton = new JButton("Turn Off Light");
        turnOffButton.addActionListener(e -> {
            client.turnOff();
            lightStatusLabel.setText("Light is OFF");
        });
        lightStatusLabel = new JLabel("Light status will be shown here");
        lightSwitchPanel.add(turnOnButton);
        lightSwitchPanel.add(turnOffButton);
        lightSwitchPanel.add(lightStatusLabel);

        // Feedback section
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBorder(new TitledBorder("Feedback"));
        feedbackPanel.setLayout(new GridLayout(3, 1, 5, 5));
        feedbackPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextField feedbackField = new JTextField();
        JButton submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.addActionListener(e -> {
            String feedback = feedbackField.getText();
            client.submitFeedback("Guest1", feedback);
            feedbackStatusLabel.setText("Feedback submitted");
        });
        feedbackStatusLabel = new JLabel("Feedback status will be shown here");
        feedbackPanel.add(feedbackField);
        feedbackPanel.add(submitFeedbackButton);
        feedbackPanel.add(feedbackStatusLabel);

        // Room Service section
        JPanel roomServicePanel = new JPanel();
        roomServicePanel.setBorder(new TitledBorder("Room Service"));
        roomServicePanel.setLayout(new GridLayout(7, 2, 5, 5));
        roomServicePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextField guestNameField = new JTextField();
        JTextField roomNumberField = new JTextField();
        JTextField requestField = new JTextField();
        roomServiceContentArea = new JTextArea();
        roomServiceContentArea.setEditable(false);
        roomServiceServerResponsesArea = new JTextArea();
        roomServiceServerResponsesArea.setEditable(false);
        JButton roomServiceButton = new JButton("Request Room Service");
        roomServiceButton.addActionListener(e -> {
            String guestName = guestNameField.getText();
            String roomNumber = roomNumberField.getText();
            String request = requestField.getText();
            client.roomServiceStream(guestName, roomNumber, request, roomServiceServerResponsesArea::append);
            roomServiceContentArea.setText("Guest Name: " + guestName + "\nRoom Number: " + roomNumber + "\nRequest: " + request);
            roomServicePanel.revalidate();
            roomServicePanel.repaint();
        });
        JLabel roomServiceStatusLabel = new JLabel("Room service status will be shown here");
        roomServicePanel.add(new JLabel("Guest Name:"));
        roomServicePanel.add(guestNameField);
        roomServicePanel.add(new JLabel("Room Number:"));
        roomServicePanel.add(roomNumberField);
        roomServicePanel.add(new JLabel("Request:"));
        roomServicePanel.add(requestField);
        roomServicePanel.add(roomServiceButton);
        roomServicePanel.add(roomServiceStatusLabel);
        roomServicePanel.add(new JLabel("Room Service Content:"));
        roomServicePanel.add(roomServiceContentArea);
        roomServicePanel.add(new JLabel("Server Responses:"));
        roomServicePanel.add(roomServiceServerResponsesArea);

        add(lightSwitchPanel);
        add(feedbackPanel);
        add(roomServicePanel);
    }

    public static void main(String[] args) throws InterruptedException {
        String consulHost = "localhost";
        int consulPort = 8500;
        String guestFeedbackServiceName = "GuestFeedbackService-service";
        String roomServiceName = "RoomService-service";
        String lightSwitchServiceName = "LightSwitch-service";

        ControllerClient client = new ControllerClient(consulHost, consulPort, guestFeedbackServiceName, roomServiceName, lightSwitchServiceName);
        ControllerClientGUI gui = new ControllerClientGUI(client);
        gui.setVisible(true);
    }
}