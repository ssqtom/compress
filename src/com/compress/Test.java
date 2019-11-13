package com.compress;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;


public class Test {

    public Test() {
        //动态获取保存文件路径
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showSaveDialog(null);
        if(chooser.getSelectedFile()!=null){
            String path = chooser.getSelectedFile().getPath();
            File file = new File(path);
            Main.compress(file);
            JOptionPane.showMessageDialog(null, "压缩成功!");
        }

    }

}
