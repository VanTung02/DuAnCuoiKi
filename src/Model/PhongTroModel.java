package Model;

public class PhongTroModel {
	private int socccd;
	private String hoten;
	private int sophong;
	private int giaphong;
	private String gioitinh;
	public PhongTroModel(int socccd, String hoten, int sophong, int giaphong, String gioitinh) {
		super();
		this.socccd = socccd;
		this.hoten = hoten;
		this.sophong = sophong;
		this.giaphong = giaphong;
		this.gioitinh = gioitinh;
	}
	public int getSocccd() {
		return socccd;
	}
	public void setSocccd(int socccd) {
		this.socccd = socccd;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public PhongTroModel() {
		super();
	}
	public int getSophong() {
		return sophong;
	}
	public void setSophong(int sophong) {
		this.sophong = sophong;
	}
	public int getGiaphong() {
		return giaphong;
	}
	public void setGiaphong(int giaphong) {
		this.giaphong = giaphong;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

}
