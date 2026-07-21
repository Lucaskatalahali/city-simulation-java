/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, kişilerden oluşan bir mahalleyi temsil eder. Mahalle nüfusu,
* içerdiği kişi sayısına göre hesaplanır.
* </p>
*/

package citysimulation;

import java.util.List;
import java.util.ArrayList;

public class Mahalle extends Yer{
	// Mahallede yaşayan kişilerin listesini tutar.
	private List<Kisi> kisiler = new ArrayList<>();
	
	public Mahalle(String adi) {
		super(adi);
	}
	
	@Override 
	public void setNufus() {
		//Mahallenin nüfusunu, listedeki kişi sayısına göre günceller
		//Kişi listesinin boyutu toplam nüfusu belirler.
		updateNufus(kisiler.size());
	}
	
	public List<Kisi> getKisiler(){
		return kisiler;
	}
	
	public void addKisiler(Kisi kisi) { //Mahalleye yeni bir kişi ekler
		kisiler.add(kisi);
	}
	
	public void addKisiListesi(List<Kisi> yeniKisiler) {
	    this.kisiler.addAll(yeniKisiler); //Mahalleye toplu olarak bir kişi listesi ekler
	}
	
	@Override
	public String toString() {
		return "Mahalle: " + getAdi() + " - Nufus: " + getNufus();
	}
}
