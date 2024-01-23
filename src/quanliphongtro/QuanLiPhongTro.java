package quanliphongtro;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import Model.PhongTroModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class QuanLiPhongTro extends JFrame implements ActionListener {
	private JTextField txtHoTen, txtSoPhong, txtGiaPhong, txtcccd;
	private JButton btnAdd, btnDelete, btnUpdate, btnDisplay,btnFind;
	private JRadioButton maleRadioButton, femaleRadioButton;
	private JTable table;
	private Color background;
	private DefaultTableModel model;
	int current = 0;
	ResultSet rs;

	public QuanLiPhongTro() {
		setTitle("Quản Lý Phòng Trọ");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel txt = new JPanel(new GridLayout(3, 3));
		txt.add(new JLabel("Số CCCD"));
		txtcccd = new JTextField();
		txt.add(txtcccd);
		txt.add(new JLabel("Họ Tên:"));
		txtHoTen = new JTextField();
		txt.add(txtHoTen);
		txt.add(new JLabel("Số Phòng:"));
		txtSoPhong = new JTextField();
		txt.add(txtSoPhong);
		txt.add(new JLabel("Giá Phòng:"));
		txtGiaPhong = new JTextField();
		txt.add(txtGiaPhong);
		txt.add(new JLabel("Giới Tính"));
		maleRadioButton = new JRadioButton("Nam");
		femaleRadioButton = new JRadioButton("Nữ");
		ButtonGroup btg = new ButtonGroup();
		btg.add(maleRadioButton);
		btg.add(femaleRadioButton);
		txt.add(maleRadioButton);
		txt.add(femaleRadioButton);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu mnEdit = new JMenu("Menu");
		menubar.add(mnEdit);

		JPanel panel = new JPanel(new GridLayout(1, 4));
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		btnUpdate = new JButton("Cập Nhật");
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(this);
		panel.add(btnDelete);
		btnDisplay = new JButton("Hiển Thị");
		btnDisplay.addActionListener(this);
		panel.add(btnDisplay);
		btnFind = new JButton("Tìm kiếm");
		btnFind.addActionListener(this);
		panel.add(btnFind);

		model = new DefaultTableModel();
		model.addColumn("Số CCCD");
		model.addColumn("Họ Tên");
		model.addColumn("Số Phòng");
		model.addColumn("Giá Phòng");
		model.addColumn("Giới Tính");
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);
		this.add(txt, BorderLayout.NORTH);
		this.pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new QuanLiPhongTro();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			String hoTen = txtHoTen.getText();
			int soPhong = Integer.valueOf(txtSoPhong.getText());
			int giaPhong = Integer.valueOf(txtGiaPhong.getText());
			int cccd = Integer.valueOf(txtcccd.getText());
			String gioitinh = "";
			if (maleRadioButton.isSelected()) {
				gioitinh = "Nam";
			} else if (femaleRadioButton.isSelected()) {
				gioitinh = "Nữ";
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
			}
			model.addRow(new Object[] { hoTen, soPhong, giaPhong, cccd, gioitinh });
			PhongTroModel model = new PhongTroModel(cccd, hoTen, soPhong, giaPhong, gioitinh);
			PhongTroDAO dao = new PhongTroDAO();
			luu(model);
			txtHoTen.setText("");
			txtSoPhong.setText("");
			txtGiaPhong.setText("");
			txtcccd.setText("");
		}

		else if (e.getSource() == btnUpdate) {
			try {
				Connection conn = JDBCUtil.getConnection();
		        Statement st = conn.createStatement();
		        
		        PreparedStatement comm = conn.prepareStatement("Update quanliphongtro set hoten=?,sophong=?,giaphong=?,Gioitinh=? where Socccd=?");
		        comm.setString(5, txtcccd.getText());
		        comm.setString(1, txtHoTen.getText());
		        comm.setString(2, txtSoPhong.getText());
		        comm.setString(3, txtGiaPhong.getText());
		        String gioiTinh;
		        if (maleRadioButton.isSelected()) {
		            gioiTinh = "Nam";
		        } else {
		            gioiTinh = "Nữ";
		        }
		        comm.setString(4, gioiTinh);
		        comm.executeUpdate();
		        model.setRowCount(0);
		        HienThi();
		        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
			
			} 
		else if (e.getSource() == btnDisplay) {
			HienThi();

		}
		else if(e.getSource() == btnFind) {
			FindJframe fd = new FindJframe();
			fd.setVisible(true);
			dispose();
		}

		else if (e.getSource() == btnDelete) {
			try {
				Connection conn = JDBCUtil.getConnection();
		       String sql = "delete from quanliphongtro where Socccd ='"+txtcccd.getText()+"'";
		       Statement st = conn.createStatement();
		       int chk = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xóa chứ!", "Thông báo!",JOptionPane.YES_NO_OPTION);
		       if(chk == JOptionPane.YES_OPTION) {
		    	   st.executeUpdate(sql);
		    	    HienThi();
		    	    txtHoTen.setText("");
					txtSoPhong.setText("");
					txtGiaPhong.setText("");
					txtcccd.setText("");
		       }
		        
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
	}

	public void HienThi() {
		PhongTroDAO phongtrodao = new PhongTroDAO();

		ArrayList<PhongTroModel> list = phongtrodao.selectAll();
		model.setRowCount(0);
		for (PhongTroModel phongTroModel : list) {
			model.addRow(new Object[] { phongTroModel.getSocccd(), phongTroModel.getHoten(), phongTroModel.getSophong(),
					phongTroModel.getGiaphong(), phongTroModel.getGioitinh() });
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					txtcccd.setText(table.getValueAt(table.getSelectedRow(), 0) + "");
					txtHoTen.setText(table.getValueAt(table.getSelectedRow(), 1) + "");
					txtSoPhong.setText(table.getValueAt(table.getSelectedRow(), 2) + "");
					txtGiaPhong.setText(table.getValueAt(table.getSelectedRow(), 3) + "");

					if (table.getValueAt(table.getSelectedRow(), 4).equals("Nữ")) {
						femaleRadioButton.setSelected(true);
					} else {
						maleRadioButton.setSelected(true);
					}

				}
			}
		});
	}
	
	public void capnhat(PhongTroModel p) {
		 String hoTen = txtHoTen.getText();
		    int soPhong = Integer.valueOf(txtSoPhong.getText());
		    int giaPhong = Integer.valueOf(txtGiaPhong.getText());
		    int cccd = Integer.valueOf(txtcccd.getText());
		    String gioitinh = "";

		    if (maleRadioButton.isSelected()) {
		        gioitinh = "Nam";
		    } else if (femaleRadioButton.isSelected()) {
		        gioitinh = "Nữ";
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính");
		        return; // Dừng thực hiện tiếp nếu không chọn giới tính
		    }

		    if (hoTen.equals("") || txtSoPhong.getText().equals("") || txtGiaPhong.getText().equals("") || txtcccd.getText().equals("")) {
		        JOptionPane.showMessageDialog(this, "Bạn cần nhập đầy đủ thông tin");
		        return; // Dừng thực hiện tiếp nếu không nhập đủ thông tin
		    }

		    try {
		        Connection conn = JDBCUtil.getConnection();
		        Statement st = conn.createStatement();

		        String sql = "UPDATE quanliphongtro SET Socccd = '" + cccd + "', hoten ='" + hoTen + "', sophong='" + soPhong + "', giaphong='" + giaPhong + "', Gioitinh='" + gioitinh + "'";

		        int ketqua = st.executeUpdate(sql);
		        if (ketqua > 0) {
		            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
		        }

		        st.close();
		        conn.close();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
	
	public int luu(PhongTroModel p) {
		int ketqua = 0;
		Connection conn = JDBCUtil.getConnection();
		try {
			if (txtcccd.getText().equals("") || txtHoTen.getText().equals("") || txtSoPhong.getText().equals("")
					|| txtGiaPhong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ dữ liệu");
			} else {
				StringBuffer sb = new StringBuffer();
				String sql_check_pk = "select * from quanliphongtro where sophong ='" + txtSoPhong.getText() + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql_check_pk);
				if (rs.next()) {
					sb.append("Khách hàng này đã tồn tại");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(this, sb.toString());

				}
				else {
					String Sql = "Insert into quanliphongtro(Socccd,hoten,sophong,giaphong,Gioitinh) VALUES(\"" + p.getSocccd()
							+ "\",\"" + p.getHoten() + "\",\"" + p.getSophong() + "\",\"" + p.getGiaphong() + "\",\""
							+ p.getGioitinh() + "\")";
					st = conn.createStatement();
					ketqua = st.executeUpdate(Sql);}
				if(ketqua >0) {
					JOptionPane.showMessageDialog(this, "Thêm mới thành công");
				}
				st.close();

			} 
			conn.close();
			rs.close();
			
		} catch (Exception e) {
		}
		return ketqua;
	}

}
