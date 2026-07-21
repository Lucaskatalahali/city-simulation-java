/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, ilçelerden oluşan bir şehri temsil eder. Şehir nüfusu,
* içerdiği ilçelerin nüfusuna göre hesaplanır ve yönetilir.
* </p>
*/

package citysimulation;

import java.util.List;
import java.util.ArrayList;

public class Sehir extends Yer {
	private List<Ilce> ilceler = new ArrayList<>();	// Şehre bağlı olan ilçelerin listesini tutar.

	
	public Sehir(String adi) {
		super(adi);
	}
	
	public List<Ilce> getIlceListesi(){
		return ilceler;
	}
	//Şehre yeni bir ilçe ekler.
	public void addIlce(Ilce yeniIlce) {
		ilceler.add(yeniIlce);
	}
	
	public void addIlceListesi(List<Ilce> yeniIlceler) {
	    this.ilceler.addAll(yeniIlceler); // Şehre toplu olarak bir ilçe listesi ekler.
	}
	
	//Şehrin hiyerarşik yapısını (İlçe -> Mahalle -> Kişi) konsola yazdırır.
	public void detayliYazdir() {
	    System.out.println("Sehir: " + getAdi() + " - Nufus: " + getNufus());
	    for (Ilce ilce : ilceler) {
	        System.out.println(ilce);
	        for (Mahalle mahalle : ilce.getMahalleListesi()) {
	            System.out.println(mahalle);
	            System.out.println("Kisiler:");
	            for (Kisi kisi : mahalle.getKisiler()) {
	                System.out.println("\t" + kisi);
	            }
	        }
	    }
	}
	
	//Şehrin toplam nüfusunu, ilçe sayısı ve ilk ilçenin nüfusu üzerinden hesaplar
	@Override
	public void setNufus() {
	 updateNufus(ilceler.size()*ilceler.getFirst().getNufus());
	}

	
	@Override
	public String toString() {
		return "Sehir: " + getAdi() + " - Nufus: " + getNufus();
	}
}
