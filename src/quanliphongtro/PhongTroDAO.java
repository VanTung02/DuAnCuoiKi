package quanliphongtro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;
import com.mysql.cj.xdevapi.Table;

import Model.PhongTroModel;
import quanliphongtro.JDBCUtil;
import quanliphongtro.QuanLiPhongTro;

public class PhongTroDAO  {
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
	public List<PhongTroModel> findCustomerByName(String hoten){
		ResultSet rs = null;
		java.sql.Statement st = null;
		List<PhongTroModel> ls = new ArrayList<>();
		try {
			String sql = "select * from quanliphongtro where hoten like '%"+hoten+"%'";
			Connection conn =JDBCUtil.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
					PhongTroModel pt = new PhongTroModel();
					pt.setSocccd(rs.getInt(1));
					pt.setHoten(rs.getString(2));
					pt.setSophong(rs.getInt(3));
					pt.setGiaphong(rs.getInt(4));
					pt.setGioitinh(rs.getString(5));
					ls.add(pt);
			}
			
		} catch (Exception e) {
			System.out.println("Error:"+e.toString());
		}
		finally {
			try {
				rs.close();st.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return ls;
	}

}



