/*
Copyright 2008-2011 Gephi
Authors : Vojtech Bardiovsky <vojtech.bardiovsky@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.gephi.desktop.visualization.components;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.gephi.graph.api.NodeShape;
import org.gephi.visualization.api.ImageManager;
import org.gephi.visualization.api.ImageNodeShape;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

/**
 * @author Vojtech Bardiovsky
 */
public class NodeShapeSelectionPanel extends javax.swing.JPanel {

    private ButtonGroup group;
    
    /** Creates new form ImageSelectionPanel */
    public NodeShapeSelectionPanel() {
        initComponents();
        
        refreshPanel();
        
        selectImageButton.addActionListener(new ActionListener() {
            private final String LAST_PATH = "NodeShapeSelectionPanel.lastPath";
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageManager imageManager = Lookup.getDefault().lookup(ImageManager.class);
                final String path = NbPreferences.forModule(BackgroundSettingsPanel.class).get(LAST_PATH, null);
                JFileChooser fileChooser = new JFileChooser(path);
                if (fileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow()) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    NbPreferences.forModule(BackgroundSettingsPanel.class).put(LAST_PATH, selectedFile.getAbsolutePath());
                    try {
                        imageManager.createNodeShape(selectedFile.getAbsolutePath());
                    } catch (Exception ex) {
                        Logger.getLogger("").log(Level.WARNING, "", ex);
                    }
                }
            }
        });
    }

    public NodeShape getSelectedNodeShape() {
        return ((NodeShapeRadioButton) group.getSelection().getSelectedObjects()[0]).getNodeShape();
    }

    private void refreshPanel() {
        ImageManager imageManager = Lookup.getDefault().lookup(ImageManager.class);
        ImageNodeShape[] nodeShapes = imageManager.getCreatedShapes();
        innerPanel.removeAll();
        innerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        group = new ButtonGroup();
        for (int i = 0; i < nodeShapes.length; i++) {
            BufferedImage image = imageManager.getImage(nodeShapes[i].id);
           
            constraints.gridy = i;
            constraints.weightx = 0.5;
            constraints.weighty = 1.0d / nodeShapes.length;
            constraints.insets = new Insets(2, 2, 2, 2);
            constraints.gridx = 0;
            add(new ImagePanel(image));
            
            constraints.gridx = 1;
            JRadioButton button = new NodeShapeRadioButton(nodeShapes[i]);
            button.setSelected(i == 0);
            add(button);
            group.add(button);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagesScrollPane = new javax.swing.JScrollPane();
        innerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selectImageButton = new javax.swing.JButton();

        javax.swing.GroupLayout innerPanelLayout = new javax.swing.GroupLayout(innerPanel);
        innerPanel.setLayout(innerPanelLayout);
        innerPanelLayout.setHorizontalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        innerPanelLayout.setVerticalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        imagesScrollPane.setViewportView(innerPanel);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(NodeShapeSelectionPanel.class, "ImageSelectionPanel.availableImages")); // NOI18N

        selectImageButton.setText(org.openide.util.NbBundle.getMessage(NodeShapeSelectionPanel.class, "ImageSelectionPanel.newImage")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(imagesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectImageButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectImageButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane imagesScrollPane;
    private javax.swing.JPanel innerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton selectImageButton;
    // End of variables declaration//GEN-END:variables

    class NodeShapeRadioButton extends JRadioButton {
        private final NodeShape nodeShape;

        public NodeShapeRadioButton(NodeShape nodeShape) {
            this.nodeShape = nodeShape;
        }

        public NodeShape getNodeShape() {
            return nodeShape;
        }
    }
    
    class ImagePanel extends JPanel {
        private final BufferedImage image;
        
        public ImagePanel(BufferedImage image) {
            this.image = image;
            this.setSize(image.getWidth(), image.getHeight());
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
        
    }
}
