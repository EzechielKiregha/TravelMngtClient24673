package com.mirrorcompany.view;

import com.mirrorcompany.events.EventMenuSelected;
import com.mirrorcompany.view.comp.MenuLayout;
import com.mirrorcompany.view.form.Form_1;
import com.mirrorcompany.view.form.Form_2;
import com.mirrorcompany.view.form.MainForm;
import com.mirrorcompany.view.form.dash.Dashboard;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicButtonUI;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class OverviewFrame extends javax.swing.JFrame {

    private final MigLayout layout;
//    private final MainForm main;
    private final MenuLayout menu;
    private final Animator animator;
    private final Dashboard main;
    
    public OverviewFrame() {
        initComponents();
        
        layout = new MigLayout("fill", "0[fill]0", "0[fill]0");
        main = new Dashboard();
        menu = new MenuLayout();
        menu.getMenu().initMoving(OverviewFrame.this);
//        main.initMoving(OverviewFrame.this);
        bodyContainer.setLayer(menu, JLayeredPane.POPUP_LAYER);
        bodyContainer.setLayout(layout);
        bodyContainer.add(main);
        bodyContainer.add(menu, "pos -215 0 100% 100%", 0);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float x = (fraction * 215);
                float alpha;
                if (menu.isShow()) {
                    x = -x;
                    alpha = 0.5f - (fraction / 2);
                } else {
                    x -= 215;
                    alpha = fraction / 2;
                }
                layout.setComponentConstraints(menu, "pos " + (int) x + " 0 100% 100%");
                if (alpha < 0) {
                    alpha = 0;
                }
                menu.setAlpha(alpha);
                bodyContainer.revalidate();
            }

            @Override
            public void end() {
                menu.setShow(!menu.isShow());
                if (!menu.isShow()) {
                    menu.setVisible(false);
                }
            }

        };
        animator = new Animator(200, target);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator.isRunning()) {
                        if (menu.isShow()) {
                            animator.start();
                        }
                    }
                }
            }
        });
//        main.addEventMenu(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                if (!animator.isRunning()) {
//                    if (!menu.isShow()) {
//                        menu.setVisible(true);
//                        animator.start();
//                    }
//                }
//            }
//        });
//        menu.getMenu().addEventMenuSelected(new EventMenuSelected() {
//            @Override
//            public void selected(int index) {
//                if (index == 0){
//                    main.show(new Form_1());
//                } else if (index == 1) {
//                    main.show(new Form_2());
//                }
//            }
//        });
        
        JButton [] btns = {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6};
        for (JButton btn : btns) {
            btn.setBackground(new Color(6,7,29));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
               }

                @Override
                public void mousePressed(MouseEvent me) {
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                    btn.setBackground(new Color(54, 81, 207));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    btn.setBackground(new Color(6,7,29));
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        mainMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        bodyContainer = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1120, 750));

        container.setLayout(new java.awt.BorderLayout());

        mainMenu.setBackground(new java.awt.Color(6, 7, 29));
        mainMenu.setPreferredSize(new java.awt.Dimension(80, 0));

        jPanel1.setBackground(new java.awt.Color(6, 7, 29));
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 250));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/4.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel1.add(jButton1);

        mainMenu.add(jPanel1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/7.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(40, 40));
        mainMenu.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/8.png"))); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(40, 40));
        mainMenu.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/2.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(40, 40));
        mainMenu.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/3.png"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(40, 40));
        mainMenu.add(jButton5);

        jPanel2.setBackground(new java.awt.Color(6, 7, 29));
        jPanel2.setPreferredSize(new java.awt.Dimension(50, 200));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 110));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mirrorcompany/view/icon/4.png"))); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel2.add(jButton6);

        mainMenu.add(jPanel2);

        container.add(mainMenu, java.awt.BorderLayout.WEST);

        bodyContainer.setBackground(new java.awt.Color(18, 20, 46));

        javax.swing.GroupLayout bodyContainerLayout = new javax.swing.GroupLayout(bodyContainer);
        bodyContainer.setLayout(bodyContainerLayout);
        bodyContainerLayout.setHorizontalGroup(
            bodyContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        bodyContainerLayout.setVerticalGroup(
            bodyContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        container.add(bodyContainer, java.awt.BorderLayout.CENTER);

        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverviewFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OverviewFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bodyContainer;
    private javax.swing.JPanel container;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mainMenu;
    // End of variables declaration//GEN-END:variables
}
