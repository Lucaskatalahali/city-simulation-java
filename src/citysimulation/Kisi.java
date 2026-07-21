/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, simülasyondaki bireyleri temsil eder. Her kişi için kimlik numarası,
* isim, soyisim ve yaş bilgileri tutulur. Oyunun tamamında aynı ID’ye sahip iki kişi olmayacak.
* </p>
*/

package citysimulation;

public class Kisi {
	private String isim;
	private String soyisim;
	private int id;
	private int yas;
	private static int IdBaslangic = 1; //Benzersiz ID ataması için kullanılan statik sayaç. ID 1'den başlıyor
	
	public Kisi(String isim, String soyisim, int yas) {
		this.isim = isim;
		this.soyisim = soyisim;
		// Her yeni Kişi oluşturulduğunda ID otomatik atanır ve sayaç artırılır.
		this.id = IdBaslangic;
		IdBaslangic++;
		this.yas = yas;
	}
	
	public String getIsim() {
		return isim; 
	}
	
	public String getSoyisim() {
		return soyisim;
	}
	
	public int getId() {
		return id;
	}
	
	public int getYas() {
		return yas;
	}
	//Simülasyon ilerledikçe kişinin yaşını bir birim artırır.
	public void yaslan() {
		yas++;
	}
	
	@Override
	public String toString() {
		return id + " - " + isim + " " + soyisim + " - " + yas;
	}

}
