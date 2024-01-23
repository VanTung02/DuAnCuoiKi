package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

import Model.PhongTroModel;
import quanliphongtro.JDBCUtil;
import quanliphongtro.QuanLiPhongTro;

public class PhongTroDAO extends QuanLiPhongTro {
	public ArrayList<PhongTroModel> selectAll(){
		
		ArrayList<PhongTroModel> ketqua = new ArrayList<PhongTroModel>();
		try {
			Connection connection =JDBCUtil.getConnection();
			java.sql.Statement st = connection.createStatement();
			String sql = "select * from quanliphongtro";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int socccd = rs.getInt(1);
				String hoten =  rs.getString("hoten");
				int sophong = rs.getInt("sophong");
				int giaphong = rs.getInt("giaphong");
				String gioitinh = rs.getString("Gioitinh");
				
				PhongTroModel phongtromodel = new PhongTroModel(socccd,hoten,sophong,giaphong,gioitinh);
				
				ketqua.add(phongtromodel);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ketqua;
	}
//public int luu(PhongTroModel p) {
//	int ketqua = 0;
//	Connection conn = JDBCUtil.getConnection();
//	try {
//		java.sql.Statement st = conn.createStatement();
//		String Sql = "Insert into quanliphongtro(Socccd,hoten,sophong,giaphong,Gioitinh) VALUES(\"" + p.getSocccd()+"\",\""+ p.getHoten()+"\",\""+p.getSophong()+"\",\""+p.getGiaphong()+"\",\""+p.getGioitinh()+"\")";
//		ketqua = st.executeUpdate(Sql);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return ketqua;
//}
}
