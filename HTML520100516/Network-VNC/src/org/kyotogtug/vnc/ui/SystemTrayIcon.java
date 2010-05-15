package org.kyotogtug.vnc.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class SystemTrayIcon {
    
    private File selectedFile;

    public SystemTrayIcon() {
        if (SystemTray.isSupported()) {
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showFileChooseDialogbox();
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem menuItem = new MenuItem("ひらけ！ポン○ッキ...");
            menuItem.addActionListener(listener);
            popup.add(menuItem);

            Image image = Toolkit.getDefaultToolkit().createImage("html/gtug210x85.png");
            TrayIcon trayIcon = new TrayIcon(image, "Menu", popup);
            trayIcon.addActionListener(listener);

            trayIcon.displayMessage("GTUG Remote Desctop", 
                        "Hello! How are you doing?", TrayIcon.MessageType.INFO);
            trayIcon.setToolTip("GTUG Remote Desctop");
            
            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public File selectedFile() {
        return selectedFile;
    }
    
    private void showFileChooseDialogbox() {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
    }
    
}
