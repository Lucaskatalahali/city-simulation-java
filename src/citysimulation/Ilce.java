/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Bu sınıf, mahallelerden oluşan bir ilçeyi temsil eder. İlçe nüfusu,
* içerdiği mahallelerin nüfusuna göre hesaplanır.
* </p>
*/

package citysimulation;

import java.util.ArrayList;
import java.util.List;

public class Ilce extends Yer {
	private List<Mahalle> mahalleler = new ArrayList<>();
	
	public Ilce(String name) {
		super(name);
	}
	
	public List<Mahalle> getMahalleListesi(){
		return mahalleler;
	}
	
	//İlçeye yeni bir mahalle nesnesi ekler.
	public void addMahalle(Mahalle newMahalle) {
		mahalleler.add(newMahalle);
	}
	public void addMahalleListesi(List<Mahalle> yeniMahalleler) {
		mahalleler.addAll(yeniMahalleler); // Mevcut listeye bir mahalle listesini toplu olarak ekler.
	}
	@Override
	public void setNufus() {
		//İlçenin toplam nüfusunu, mahalle sayısı ve ilk mahallenin nüfusu üzerinden hesaplayarak günceller.
		updateNufus(mahalleler.size() * mahalleler.getFirst().getNufus());
	}
	
	@Override
	public String toString() {
		return "Ilce: " + getAdi() + " - Nufus: " + getNufus();
	}
}
