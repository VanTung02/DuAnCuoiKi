package quanliphongtro;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import Model.PhongTroModel;

public class FindJframe extends JFrame implements ActionListener {
	PhongTroDAO dao = new PhongTroDAO();
	String strFind ="";
	JTextField txtFind;
	JLabel lbFind;
	JButton btnFind;
	JPanel txt;
	private JTable tb;

	public FindJframe() {
		setTitle("Tìm kiếm");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel txt = new JPanel(new GridLayout(1, 1));
		JLabel lblTitle = new JLabel("TÌM KIẾM THÔNG TIN KHÁCH HÀNG");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 19));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txt.add(lblTitle);

		txt.add(new JLabel("                                                                      "+"Tìm kiếm theo tên"));
		txtFind = new JTextField();
		txt.add(txtFind);

		btnFind = new JButton("Tìm kiếm");
		txt.add(btnFind);
		btnFind.addActionListener(this);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Số CCCD");
		model.addColumn("Họ Tên");
		model.addColumn("Số Phòng");
		model.addColumn("Giá Phòng");
		model.addColumn("Giới Tính");
		this.tb = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tb);
		
        fillltable();
		getContentPane().add(txt, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.add(txt, BorderLayout.NORTH);
		this.pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public void fillltable()  {
//		try {
//			String [] arr = {"Số cccd","họ tên","số phòng","giá phòng","giới tính"};
//			DefaultTableModel model = new DefaultTableModel(arr, 0);
//			
//			Connection conn = JDBCUtil.getConnection();
//			String query ="Select * from quanliphongtro";
//			PreparedStatement ps = conn.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				Vector vector =new Vector();
//				vector.add(rs.getString("Socccd"));
//				vector.add(rs.getString("hoten"));
//				vector.add(rs.getString("sophong"));
//				vector.add(rs.getString("giaphong"));
//				vector.add(rs.getString("gioitinh"));
//				model.addRow(vector);
//		}
//			tb.setModel(model);
//			}catch (Exception e) {
//				e.printStackTrace();
//		}
			
		DefaultTableModel tbmodel = (DefaultTableModel) tb.getModel();
		tbmodel.setRowCount(0);
		for(PhongTroModel p: dao.findCustomerByName(strFind)) {
			Object dataRow[] = new Object[5];
			dataRow[0] =p.getSocccd();
			dataRow[1] =p.getHoten();
			dataRow[2] =p.getSophong();
			dataRow[3] =p.getGiaphong();
			dataRow[4] =p.getGioitinh();
			tbmodel.addRow(dataRow);
		}
		}

	public static void main(String[] args) {
		new FindJframe();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnFind) {
			strFind = txtFind.getText();
			fillltable();
		}

	}

}
